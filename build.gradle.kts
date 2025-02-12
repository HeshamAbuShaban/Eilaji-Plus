buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.6")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    id("com.google.gms.google-services") version "4.4.2" apply false
    // Ksp
    id("com.google.devtools.ksp") version "1.9.25-1.0.20" apply false
    // Dagger Hilt
    id("com.google.dagger.hilt.android") version "2.48" apply false
}