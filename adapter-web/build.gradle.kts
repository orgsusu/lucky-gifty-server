plugins {
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":common"))

    implementation(libs.spring.boot.validation)
    implementation(libs.spring.session.core)

    implementation(libs.bundles.jackson)

    implementation(libs.bundles.konvert.implementation)
    ksp(libs.bundles.konvert.ksp)

    testImplementation(libs.spring.test.security)
    testImplementation(libs.spring.test.restdocs)
    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.junit.platform)
}
