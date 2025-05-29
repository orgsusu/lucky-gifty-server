plugins {
	alias(libs.plugins.ksp)
}

dependencies{
	implementation(project(":domain"))
	implementation(project(":common"))

	implementation(libs.spring.webflux)
}
