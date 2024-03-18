package nay.kirill.glassOfWater.main

import android.app.Application
import nay.kirill.glassOfWater.main.di.appModule
import nay.kirill.glassOfWater.main.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import nay.kirill.glassOfWater.main.setupNavigation

class GlassOfWaterApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GlassOfWaterApp)

            modules(
                appModule,
                platformModule
            )
        }

        setupNavigation()
    }

    companion object {

        const val SHARED_PREFERENCE_NAME = "GLASS_OF_WATER_PREF"

    }

}