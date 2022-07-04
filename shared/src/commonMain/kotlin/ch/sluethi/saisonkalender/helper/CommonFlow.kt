package ch.sluethi.saisonkalender.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * source: https://stackoverflow.com/questions/64175099/listen-to-kotlin-coroutine-flow-from-ios
 */
fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun collectCommon(callback: (T) -> Unit) {
        onEach { callback(it) }.launchIn(CoroutineScope(Dispatchers.Main))
    }
}
