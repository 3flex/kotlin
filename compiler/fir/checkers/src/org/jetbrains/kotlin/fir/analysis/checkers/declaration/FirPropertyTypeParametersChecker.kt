/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers.declaration

import org.jetbrains.kotlin.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors
import org.jetbrains.kotlin.fir.analysis.diagnostics.reportOnWithSuppression
import org.jetbrains.kotlin.fir.declarations.FirProperty
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType
import org.jetbrains.kotlin.fir.types.coneType
import org.jetbrains.kotlin.fir.types.type

object FirPropertyTypeParametersChecker : FirPropertyChecker() {

    override fun CheckerContext.check(declaration: FirProperty, reporter: DiagnosticReporter) {
        val boundsByName = declaration.typeParameters.associate { it.name to it.symbol.resolvedBounds }
        val usedTypes = HashSet<ConeKotlinType>()
        fun collectAllTypes(type: ConeKotlinType) {
            if (usedTypes.add(type)) {
                type.typeArguments.forEach { it.type?.let(::collectAllTypes) }
                if (type is ConeTypeParameterType) {
                    boundsByName[type.lookupTag.name]?.forEach { collectAllTypes(it.coneType) }
                }
            }
        }
        declaration.receiverTypeRef?.let { collectAllTypes(it.coneType) }
        declaration.contextReceivers.forEach { collectAllTypes(it.typeRef.coneType) }

        val usedNames = usedTypes.filterIsInstance<ConeTypeParameterType>().map { it.lookupTag.name }
        declaration.typeParameters.filterNot { usedNames.contains(it.name) }.forEach { danglingParam ->
            reporter.reportOnWithSuppression(danglingParam, FirErrors.TYPE_PARAMETER_OF_PROPERTY_NOT_USED_IN_RECEIVER, this)
        }
    }

}
