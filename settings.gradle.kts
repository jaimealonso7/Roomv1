// settings.gradle.kts
pluginManagement {
    repositories {
        google() // Repositorio de Google
        mavenCentral() // Repositorio Maven Central
    }
}

dependencyResolutionManagement {
    repositories {
        google() // Repositorio de Google
        mavenCentral() // Repositorio Maven Central
    }
}

include(":app")
