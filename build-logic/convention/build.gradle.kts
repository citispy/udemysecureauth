import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.example.udemysecureauth.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.tools.common)
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "udemysecureauth.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "udemysecureauth.android.library.compose"
            implementationClass = "AndroidComposeLibraryConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "udemysecureauth.android.application.compose"
            implementationClass = "AndroidComposeApplicationConventionPlugin"
        }
        register("hilt") {
            id = "udemysecureauth.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }
}