// WITH_STDLIB
// WITH_REFLECT
// WITH_COROUTINES
// TARGET_BACKEND: JVM
// NO_CHECK_LAMBDA_INLINING
// FILE: inlined.kt
package flow

interface Flow {
    fun collect(collector: FlowCollector)
}

interface FlowCollector {
    fun emit(value: String)
}

inline fun flow(crossinline body: FlowCollector.() -> Unit): Flow =
    object : Flow {
        override fun collect(collector: FlowCollector) = collector.body()
    }

inline fun Flow.collect(crossinline body: (String) -> Unit) =
    collect(object : FlowCollector {
        override fun emit(value: String) = body(value)
    })

inline fun Flow.transform(crossinline transformer: FlowCollector.(value: String) -> Unit): Flow =
    flow { collect { value -> transformer(value) } }

inline fun Flow.map(crossinline transformer: (value: String) -> String): Flow =
    transform { value -> emit(transformer(value)) }

// FILE: box.kt
import flow.*
import helpers.*
import kotlin.reflect.*
import kotlin.reflect.jvm.*
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*

fun addK(x: String): String = x + "K"

var result = ""

fun box(): String {
    val source = flow { emit("O") }
    val reference: ((String) -> String) -> Flow = source::map
    ((reference as KFunction<*>).javaMethod!!.invoke(null, source, ::addK) as Flow).collect { result = it }
    return result
}
