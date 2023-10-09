package nay.kirill.kmpArch.navigation

import androidx.compose.runtime.mutableStateListOf

class NavigationStack(initial: String) {
    private val stack = mutableStateListOf(initial)
    fun push(t: String) {
        stack.add(t)
    }

    fun back() {
        if(stack.size > 1) {
            // Always keep one element on the view stack
            stack.removeLast()
        }
    }

    fun lastWithIndex() = stack.withIndex().last()
}