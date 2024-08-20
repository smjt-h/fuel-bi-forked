pluginManagement {
    includeBuild("plugins")
}

// import the plugin
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.harness:gradle-cache:0.0.4")
    }
}

// apply the plugin
apply(plugin = "io.harness.gradle-cache")

rootProject.name = "Fuel-MPP"

include(":fuel")
include(":fuel-forge-jvm")
include(":fuel-jackson-jvm")
include(":fuel-kotlinx-serialization")
include(":fuel-moshi-jvm")

include(":samples:httpbin-wasm")
include(":samples:mockbin-native")

// build cache config
buildCache {
    local {
        // Local build cache is dangerous as it might produce inconsistent results
        // in case developer modifies files while the build is running
        isEnabled = false
    }
    remote(io.harness.Cache::class.java) {
        accountId = System.getenv("HARNESS_ACCOUNT_ID") // accountId should be populated in CI pipeline
        token = System.getenv("HARNESS_PAT")            // API token with account admin (or edit) permissions
        isPush = true
        endpoint = System.getenv("HARNESS_CACHE_SERVICE_ENDPOINT") // https://app.harness.io/gateway/cache-service
    }
}
