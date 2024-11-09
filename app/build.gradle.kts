plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.todolist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.todolist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }
    viewBinding {
        enable = true
    }
    dataBinding {
        enable = true
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
        freeCompilerArgs = listOf("-Xjvm-default=all-compatibility")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
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
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.testing)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version") // Room with Kotlin coroutine support
    kapt("androidx.room:room-compiler:$room_version")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation (libs.androidx.recyclerview)
    implementation (libs.androidx.constraintlayout)

    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.0")
    testImplementation ("junit:junit:4.13.2")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("com.squareup.picasso:picasso:2.71828")
    testImplementation ("junit:junit:4.13.2")

    testImplementation("androidx.room:room-testing:2.6.1")
    androidTestImplementation("androidx.room:room-testing:2.6.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")

    testImplementation("com.squareup.okhttp3.mockwebserver:4.9.0")
    testImplementation("org.mockito:mockito-core:4.11.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:4.1.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0") // or the latest version

//    androidTestImplementation("androidx.test:core:1.5.0")
//    androidTestImplementation("androidx.test:core-ktx:1.5.0")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation("androidx.test:runner:1.5.2")
//    androidTestImplementation("androidx.test:rules:1.5.0")
//    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")

//    androidTestImplementation("androidx.test:core:1.5.0")
//    androidTestImplementation("androidx.test:core-ktx:1.5.0")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation("androidx.test:runner:1.5.2")
//    androidTestImplementation("androidx.test:rules:1.5.0")
//    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")


}