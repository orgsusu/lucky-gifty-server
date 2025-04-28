dependencies{
	implementation(project(":domain"))
	implementation(project(":common"))

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.session:spring-session-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("jakarta.servlet:jakarta.servlet-api")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("io.mcarle:konvert-api:4.0.1")
	implementation("io.mcarle:konvert-spring-annotations:4.0.1")
	ksp("io.mcarle:konvert:4.0.1")
	ksp("io.mcarle:konvert-spring-injector:4.0.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

plugins {
	id("com.google.devtools.ksp")
}