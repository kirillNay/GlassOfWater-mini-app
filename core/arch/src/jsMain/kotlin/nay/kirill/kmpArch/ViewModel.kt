package nay.kirill.kmpArch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class ViewModel {

    actual val viewModelScope: CoroutineScope = MainScope()

    protected actual open fun onCleared() {
        viewModelScope.cancel()
    }

}