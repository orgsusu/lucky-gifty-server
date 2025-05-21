dependencies {
    api(libs.spring.boot.core)
    api(libs.spring.boot.security)

    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.junit.platform)
}
