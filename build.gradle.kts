import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.jpa)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.deps)
    alias(libs.plugins.ksp)
}

allprojects {
    group = "dev.orgsusu"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.3")
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_17)
            languageVersion.set(KotlinVersion.KOTLIN_2_1)
            apiVersion.set(KotlinVersion.KOTLIN_2_1)
        }
    }
}

dependencies {
    implementation(project(":adapter-web"))
    implementation(project(":adapter-persistence"))
    implementation(project(":adapter-toss"))
    implementation(project(":adapter-tosscrypto"))
    implementation(project(":adapter-kakao"))

    implementation(libs.spring.boot.core)
    implementation(libs.spring.data.jpa)
}
