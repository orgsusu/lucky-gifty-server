plugins {
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))

    implementation(libs.spring.boot.validation)

    implementation(libs.bundles.konvert.implementation)
    ksp(libs.bundles.konvert.ksp)

    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.junit.platform)
}
