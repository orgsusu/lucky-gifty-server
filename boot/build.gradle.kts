dependencies {
    implementation(project(":adapter-web"))
    implementation(project(":adapter-persistence"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

}