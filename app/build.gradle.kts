import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.kotlin.kapt)
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.daon.onjung"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.daon.onjung"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            "String",
            "BASE_URL",
            properties["base.url"].toString()
        )
        buildConfigField(
            "String",
            "KAKAO_APP_KEY",
            properties["kakao.native.app.key"].toString()
        )
        resValue(
            "string",
            "KAKAO_APP_KEY_FULL",
            properties["kakao.native.app.key.full"].toString()
        )
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.util)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.material)

    implementation(libs.coroutines.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.data.preferences)
    implementation(libs.retrofit2)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp3)
    implementation(libs.chucker.library)

    implementation(libs.kakao.sdk.user)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.common)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging)

    implementation(libs.coil.compose)

    implementation(libs.youtube.player)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}