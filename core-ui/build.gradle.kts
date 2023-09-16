apply(from = "$rootDir/android-library-build.gradle")

configure<com.android.build.gradle.LibraryExtension> {
    namespace = "core_ui"
}

dependencies {
    "implementation" (project(Modules.core))

    "implementation" (Koin.koinAndroid)
    "implementation" (Koin.koinCompose)
    "implementation" (Compose.materialIconsExtended)
    "implementation" (AndroidX.browser)
}