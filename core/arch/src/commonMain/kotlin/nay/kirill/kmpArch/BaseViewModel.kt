package nay.kirill.kmpArch

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

expect abstract class ViewModel(): CoroutineScope {

    override val coroutineContext: CoroutineContext

    protected open fun onCleared()

    protected abstract fun onError(context: CoroutineContext, error: Throwable)
}