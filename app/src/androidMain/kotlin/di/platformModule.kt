package di

import android.content.Context
import nay.kirill.glassOfWater.GlassOfWaterApp.Companion.SHARED_PREFERENCE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { androidContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE) }
}