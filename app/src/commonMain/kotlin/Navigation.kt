import cafe.adriel.voyager.core.registry.ScreenRegistry
import nay.kirill.glassOfWater.counter.counterScreenModule
import nay.kirill.glassOfWater.stat.statsScreenModule
import nay.kirill.settings.settingsScreenModule

internal fun setupNavigation() {
    ScreenRegistry {
        settingsScreenModule()
        counterScreenModule()
        statsScreenModule()
    }
}