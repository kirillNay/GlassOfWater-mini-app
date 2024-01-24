package nay.kirill.glassOfWater

import android.app.Application
import di.appModule
import di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import setupNavigation

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