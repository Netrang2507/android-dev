plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "1.6.10"
    id("com.google.protobuf") version "0.9.4"



}

android {
    namespace = "com.example.protoda"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.protoda"
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

    protobuf {
        protoc {
            artifact = "com.google.protobuf:protoc:3.24.0"  // Specify Protobuf version
        }
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)


    implementation("androidx.datastore:datastore:1.0.0") // Latest DataStore version
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1") // Latest version of Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1") // Core serialization dependency
    implementation(libs.kotlinx.collections.immutable)
    debugImplementation(libs.androidx.ui.test.manifest)



}
