plugins {
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.session:spring-session-core")

    implementation("io.mcarle:konvert-api:4.0.1")
    implementation("io.mcarle:konvert-spring-annotations:4.0.1")
    ksp("io.mcarle:konvert:4.0.1")
    ksp("io.mcarle:konvert-spring-injector:4.0.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
