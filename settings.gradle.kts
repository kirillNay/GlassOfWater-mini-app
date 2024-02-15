dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev" )
        maven(url = "https://jitpack.io" )
    }
}

rootProject.name = "HealthCare"

include(
    ":app",

    ":features:waterCounter",
    ":features:settings",

    ":core:res",
    ":core:ui",
    ":core:navigation",

    ":domain",
    ":data",
    ":utils"
)