// build.gradle.kts (Nivel de Proyecto)
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}

buildscript {
    repositories {
        // Elimina las definiciones de repositorios aquí
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.google.gms:google-services:4.4.2")
    }
}

allprojects {
    repositories {
        // Elimina las definiciones de repositorios aquí
    }
}
