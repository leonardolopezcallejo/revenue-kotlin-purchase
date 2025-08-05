plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.revenuepoc"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.revenuepoc"
        minSdk = 24
        targetSdk = 36
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
        // Using JavaVersion.VERSION_11 is good.
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        // Setting jvmTarget to "11" is also correct.
        jvmTarget = "11"
    }
    buildFeatures {
        // Ensure Compose is enabled.
        compose = true
    }
}

dependencies {
    // Standard dependencies for a Compose app.
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Google Play Billing - Kotlin extensions.
    // The version here is correct.
    implementation("com.android.billingclient:billing-ktx:8.0.0")

    // Correctly specifying the RevenueCat dependency.
    // We are using the stable version 9.1.0 as you provided.
    // The syntax `implementation(...)` is correct.
    implementation("com.revenuecat.purchases:purchases:9.1.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
