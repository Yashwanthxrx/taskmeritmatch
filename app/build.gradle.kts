        plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.meritmatch"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.meritmatch"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

        dependencies {
            // AndroidX dependencies
            implementation("androidx.constraintlayout:constraintlayout:2.1.4")
            implementation("androidx.core:core-ktx:1.12.0")
            implementation("androidx.appcompat:appcompat:1.6.1")
            implementation("com.google.android.material:material:1.8.0")

            // Compose dependencies
            implementation("androidx.compose.ui:ui:1.5.1")
            implementation("androidx.compose.material3:material3:1.1.0")
            implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
            implementation("androidx.activity:activity-compose:1.7.0")

            // Retrofit dependencies
            implementation("com.squareup.retrofit2:retrofit:2.9.0")
            implementation("com.squareup.retrofit2:converter-gson:2.9.0")


            // Testing dependencies
            testImplementation("junit:junit:4.13.2")
            testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
            androidTestImplementation("androidx.test.ext:junit:1.1.5")
            androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        }

