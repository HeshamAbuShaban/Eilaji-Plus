plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt") // Only if you still need kapt for some libraries
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "dev.training.eilaji_plus"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.training.eilaji_plus"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "API_KEY", "\"${project.property("API_KEY")}\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // View Model & Live Data
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Navigation Component
    /*val navVersion = "2.8.0"*/
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // SDP & SSP
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    // OTP Pin View
    implementation(libs.pinview)

    // Splash API
    implementation(libs.androidx.core.splashscreen)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    /*// Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")

    // FirebaseUI for Firebase Realtime Database (FirebaseRecyclerAdapter)
    implementation("com.firebaseui:firebase-ui-database:8.0.2") // Consider migrating away from FirebaseUI Database, as it's no longer actively maintained
    // Consider replacing with a newer approach if still needed
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
    */

    // Google Maps
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)

    /*// Horizontal Indicator (replace with actual dependency if available)
    implementation(project(":Horizontal-indicator-for-pager2-and-recycler"))*/

    // Glide
    implementation(libs.glide)
    ksp(libs.glide.compiler)

    // Rounded ImageView
    implementation(libs.roundedimageview)

    // Room Database
    /*val roomVersion = "2.6.1"*/
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    /*
    // Magic Indicator
    implementation("com.github.hackware1993:MagicIndicator:1.8.0")*/

    // Worker Service
    /*val workVersion = "2.9.1"*/
    implementation(libs.androidx.work.runtime.ktx)

    // Activity KTX
    /*val activityVersion = "1.9.2"*/
    implementation(libs.androidx.activity.ktx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.scalars)

    // OkHttp3
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Shimmer
    implementation(libs.shimmer)


    // AdvanceCore
    // RefreshSwiperDown
    implementation(libs.androidx.swiperefreshlayout)
    // i think encrypted sharedpref
    implementation(libs.androidx.security.crypto)
    // fingerprint
    implementation(libs.androidx.biometric)
}