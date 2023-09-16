apply(from = "$rootDir/android-library-build.gradle")

configure<com.android.build.gradle.LibraryExtension> {
    namespace = "tvshow_presentation"
}

dependencies {
    "implementation" (project(Modules.core))
    "implementation" (project(Modules.tvShowDomain))
    "implementation" (project(Modules.coreUI))

    "implementation" (Coil.coilCompose)

    "implementation" (Koin.koinAndroid)
    "implementation" (Koin.koinCompose)

    "implementation" (Compose.materialIconsExtended)
    "implementation" (Compose.placeholderMaterial)
}