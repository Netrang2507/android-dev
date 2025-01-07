plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)


}

android {
    namespace = "com.example.apire"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.apire"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

}
dependencies {
// Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx) // Adding this line
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom)) // Using BOM for Compose
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

// Testing dependencies
    testImplementation(libs.junit) // Ensuring that JUnit is referenced
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core) // Ensuring Espresso core is referenced
    androidTestImplementation(platform(libs.androidx.compose.bom)) // Use BOM for tests as well
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

// Retrofit and Gson converter
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

// Logging interceptor
    implementation(libs.logging.interceptor)

// Dagger-Hilt and KSP for annotation processing
    implementation(libs.hilt.android) // Hilt Android dependency
    ksp(libs.hilt.compiler) // Hilt Compiler using KSP

// Hilt Lifecycle and Navigation Compose
    implementation(libs.androidx.hilt.lifecycle.viewmodel)
    implementation(libs.androidx.hilt.navigation.compose)
// Foundation and UI for Jetpack Compose
    implementation(libs.androidx.foundation)
}