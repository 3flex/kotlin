/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.lazy

import org.jetbrains.kotlin.fir.backend.Fir2IrComponents
import org.jetbrains.kotlin.fir.backend.contextReceiversForFunctionOrContainingProperty
import org.jetbrains.kotlin.fir.backend.generateOverriddenFunctionSymbols
import org.jetbrains.kotlin.fir.backend.toIrType
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.declarations.FirRegularClass
import org.jetbrains.kotlin.fir.declarations.FirSimpleFunction
import org.jetbrains.kotlin.fir.initialSignatureAttr
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.declarations.lazy.lazyVar
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource

class Fir2IrLazySimpleFunction(
    components: Fir2IrComponents,
    startOffset: Int,
    endOffset: Int,
    origin: IrDeclarationOrigin,
    override val fir: FirSimpleFunction,
    firParent: FirRegularClass?,
    symbol: IrSimpleFunctionSymbol,
    isFakeOverride: Boolean
) : AbstractFir2IrLazyFunction<FirSimpleFunction>(components, startOffset, endOffset, origin, symbol, isFakeOverride) {
    init {
        symbol.bind(this)
        classifierStorage.preCacheTypeParameters(fir, symbol)
    }

    override var annotations: List<IrConstructorCall> by createLazyAnnotations()

    override var name: Name
        get() = fir.name
        set(_) {
            throw UnsupportedOperationException()
        }

    override var returnType: IrType by lazyVar(lock) {
        fir.returnTypeRef.toIrType(typeConverter)
    }

    override var dispatchReceiverParameter: IrValueParameter? by lazyVar(lock) {
        val containingClass = parent as? IrClass
        if (containingClass != null && shouldHaveDispatchReceiver(containingClass, fir)) {
            createThisReceiverParameter(thisType = containingClass.thisReceiver?.type ?: error("No this receiver for containing class"))
        } else null
    }

    override var extensionReceiverParameter: IrValueParameter? by lazyVar(lock) {
        fir.receiverTypeRef?.let {
            createThisReceiverParameter(it.toIrType(typeConverter))
        }
    }

    override var contextReceiverParametersCount: Int = fir.contextReceiversForFunctionOrContainingProperty().size

    override var valueParameters: List<IrValueParameter> by lazyVar(lock) {
        declarationStorage.enterScope(this)

        buildList {
            declarationStorage.addContextReceiverParametersTo(
                fir.contextReceiversForFunctionOrContainingProperty(),
                this@Fir2IrLazySimpleFunction,
                this@buildList,
            )

            fir.valueParameters.mapIndexedTo(this) { index, valueParameter ->
                declarationStorage.createIrParameter(
                    valueParameter, index, skipDefaultParameter = isFakeOverride
                ).apply {
                    this.parent = this@Fir2IrLazySimpleFunction
                }
            }
        }.apply {
            declarationStorage.leaveScope(this@Fir2IrLazySimpleFunction)
        }
    }

    override var overriddenSymbols: List<IrSimpleFunctionSymbol> by lazyVar(lock) {
        if (firParent == null) return@lazyVar emptyList()
        val parent = parent
        if (isFakeOverride && parent is Fir2IrLazyClass) {
            fakeOverrideGenerator.calcBaseSymbolsForFakeOverrideFunction(
                firParent, this, fir.symbol
            )
            fakeOverrideGenerator.getOverriddenSymbolsForFakeOverride(this)?.let { return@lazyVar it }
        }
        fir.generateOverriddenFunctionSymbols(firParent)
    }

    override val initialSignatureFunction: IrFunction? by lazy {
        (fir.initialSignatureAttr as? FirFunction)?.symbol?.let { declarationStorage.getIrFunctionSymbol(it).owner }?.takeIf { it !== this }
    }

    override val containerSource: DeserializedContainerSource?
        get() = fir.containerSource
}
