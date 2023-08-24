plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.exalt.profile"
    compileSdk = AndroidOptions.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidOptions.MIN_SDK
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }

}

dependencies {

    implementation("androidx.core:core-ktx:${Versions.ANDROID_CORE}")
    implementation("androidx.appcompat:appcompat:${Versions.APP_COMPAT}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation(project(":domain"))
    implementation(project(":data"))

    // Hilt
    implementation("com.google.dagger:hilt-android:${Versions.HILT}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.HILT}")

    // Glide
    implementation("com.github.bumptech.glide:glide:${Versions.GLIDE}")
    annotationProcessor("com.github.bumptech.glide:compiler:${Versions.GLIDE}")

    // Navigation
    implementation("androidx.navigation:navigation-runtime-ktx:${Versions.NAVIGATION}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}")

    // Testing
    testImplementation("junit:junit:${Versions.JUNIT}")
    testImplementation("io.mockk:mockk:${Versions.MOCKK}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_TEST}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_TEST}")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")



}