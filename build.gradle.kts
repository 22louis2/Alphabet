// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // Hilt plugin for Dependency Injection
    // but not applied globally (apply false)
    // keeps it available for modules that explicitly need it
    id("com.google.dagger.hilt.android") version "2.57.2" apply false
    // Kotlin Symbol Processor (KSP) plugin
    // Applied immediately at the root with version "2.2.20-2.0.3"
    // Required to run annotation processors (e.g., Room, Moshi, Hilt codegen).
    id("com.google.devtools.ksp") version "2.2.20-2.0.3" apply false
}