package nay.kirill.kmpArch

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

actual abstract class ViewModel : CoroutineScope {

    actual override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main + CoroutineExceptionHandler(::onError)

    protected actual open fun onCleared() {
        coroutineContext.cancelChildren()
    }

    protected actual abstract fun onError(context: CoroutineContext, error: Throwable)

}