package di

import GlassOfWaterThemeHandler
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::GlassOfWaterThemeHandler)
}