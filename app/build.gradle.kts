plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kspPlugin)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mr.anonym.riskbook"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.mr.anonym.riskbook"
        minSdk = 26
        targetSdk = 36
        versionCode = 13
        versionName = "13"

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
    ksp {
        arg("option_name", "option_value")
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    //    Dagger Hilt
    implementation(libs.daggerHilt)
    implementation(libs.daggerHilt.navigation.compose)
    ksp(libs.daggerHiltCompiler)

//    Compose navigation
    implementation(libs.navigation)

//    Coroutines & lifecycle
    implementation(libs.kotlinCoroutines)
    implementation(libs.viewModel)
    implementation(libs.viewModelForCompose)
    implementation(libs.lifecycleCompose)
    implementation(libs.savedState)

    //    DataStore
    implementation(libs.androidx.dataStore)

    //    Room SQLite
    implementation(libs.androidx.room.common)
    implementation(libs.roomSqlite)
    implementation(libs.roomCoroutinesSupport)
    ksp(libs.roomCompiler)

//    Lottie animations
    implementation(libs.lottieanimations)

//    Ychart
    implementation(libs.yChart)

//    Vico charts
    implementation(libs.vicoCompose)
    implementation(libs.vicoComposeM3)
    implementation(libs.vico)
}