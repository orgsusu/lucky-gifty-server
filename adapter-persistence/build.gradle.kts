plugins {
	alias(libs.plugins.ksp)
}

dependencies{
	implementation(project(":domain"))
	implementation(project(":common"))

	implementation(libs.bundles.spring.boot.persistence)
	implementation(libs.spring.session.redis)
	runtimeOnly(libs.mariadb)

	implementation(libs.bundles.konvert.implementation)
	ksp(libs.bundles.konvert.ksp)

	testImplementation(libs.bundles.test)
	testRuntimeOnly(libs.junit.platform)
}
