
import nay.kirill.kmpArch.navigation.NavigationStack

class AppState(
    private val navigationStack: NavigationStack
) {

    val currentRoute: String
        get() = navigationStack.lastWithIndex().value

}