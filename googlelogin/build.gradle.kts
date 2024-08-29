plugins {
    alias(libs.plugins.udemysecureauth.android.library)
    alias(libs.plugins.udemysecureauth.android.library.compose)
    alias(libs.plugins.udemysecureauth.hilt)
}

android {
    namespace = "com.lib.googlelogin"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Credentials Manager
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation (libs.googleid)
}