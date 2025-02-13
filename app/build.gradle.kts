import com.android.tools.r8.J
import org.apache.tools.ant.util.JavaEnvUtils.VERSION_11

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Incluir para ROOM
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    //APT (Kotlin Annotation Processing Tool): KAPT es una herramienta de procesamiento de anotaciones
    // para Kotlin. Se utiliza para procesar anotaciones en tiempo de compilación
    // y generar código adicional.
    // En el contexto de Room y otras bibliotecas que hacen uso intensivo de anotaciones,
    // KAPT es esencial.

}

android {
    namespace = "com.example.roomv1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.roomv1"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Import the BoM for the Firebase platform
    implementation (platform("com.google.firebase:firebase-bom:33.8.0"))

    // Declare the dependencies for the desired Firebase products without specifying versions
    // For example, declare the dependencies for Firebase Authentication and Cloud Firestore
    implementation ("com.google.firebase:firebase-auth")
    implementation ("com.google.firebase:firebase-firestore")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-database:21.0.0")

    //INCLUIR NAVIGATION PARA PODER USAR NAVCONTROLER Y NAVEGABILIDAD ENTRE PANTALLAS
    val navigationVersion = "2.7.6"
    implementation("androidx.navigation:navigation-compose:$navigationVersion")

    //INCLUIR ROOM
    val roomVersion = "2.5.1"
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")


    // INLCUIR VIEWMODEL
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation(kotlin("script-runtime"))
}