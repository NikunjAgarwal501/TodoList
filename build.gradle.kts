// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.android.library") version "8.1.4" apply false

}
buildscript {
    dependencies {
        // Add Safe Args plugin classpath directly
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.0")
        classpath ("com.android.tools.build:gradle:8.1.0")
    }
}
