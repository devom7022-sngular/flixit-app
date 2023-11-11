

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    val lifecycleVersion: String ="2.6.2"

    versionCatalogs {
        create("libs") {
            library("lifecycle-viewmodel","androidx.lifecycle","lifecycle-viewmodel-ktx").version{
                strictly(lifecycleVersion)
            }
            library("lifecycle-livedata","androidx.lifecycle","lifecycle-livedata-ktx").version{
                strictly(lifecycleVersion)
            }


        }
    }
}

rootProject.name = "flixit-app"
include(":app")
