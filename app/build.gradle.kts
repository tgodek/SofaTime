plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {

    namespace = ProjectConfig.appId
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        buildConfigField("String", ApiKeys.TMDB_API_KEY, ApiKeys.getTmdbApiKey)
        buildConfigField("String", ApiKeys.TRAKT_CLIENT_ID, ApiKeys.getTraktClientID)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
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
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {
    //implementation(project(Modules.core))
    //implementation(project(Modules.coreUI))
    //implementation(project(Modules.tvShowData))
    //implementation(project(Modules.tvShowDomain))
    //implementation(project(Modules.tvShowPresentation))
    implementation(platform(Compose.composeBom))
    implementation(Compose.ui)
    implementation(Compose.material3)
    implementation(Compose.materialIconsExtended)
    implementation(Compose.uiTooling)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.navigation)
    implementation(Compose.activityCompose)
    implementation(Compose.runtime)
    implementation(Compose.compiler)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleViewModel)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.splashScreen)

    // 3rd Party
    implementation(Coil.coilCompose)
    implementation(Koin.koinCompose)

    implementation(platform(Firebase.firebaseBoom))
    implementation(Firebase.firebaseCrashlytics)
    implementation(Firebase.fireBaseAnalytics)

    // test
    testImplementation(Junit.junit)
    androidTestImplementation(Junit.junitCore)
}