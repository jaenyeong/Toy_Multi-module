rootProject.name = "Toy"

include("module01_admin", "module02_main", "module03_worker")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    resolutionStrategy {
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
include("module03_worker")
