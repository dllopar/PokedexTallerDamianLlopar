pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PokedexTallerDamianLlopar"
include(":androidApp")
include(":shared")
include(":shared:kotlin")
