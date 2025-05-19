dependencies {
    implementation(project(":adapter-web"))
    implementation(project(":adapter-persistence"))

    implementation(libs.spring.boot.core)
    implementation(libs.spring.data.jpa)
}
