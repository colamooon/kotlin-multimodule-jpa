import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val bootVer = "2.3.2.RELEASE"
    val kotlinVer = "1.3.72"
    val springMgmtVer = "1.0.9.RELEASE"

    java
    id("org.springframework.boot") version bootVer
    id("io.spring.dependency-management") version springMgmtVer
    kotlin("jvm") version kotlinVer
    kotlin("kapt") version kotlinVer
    kotlin("plugin.spring") version kotlinVer
    kotlin("plugin.jpa") version kotlinVer
    kotlin("plugin.allopen") version kotlinVer
    kotlin("plugin.noarg") version kotlinVer
}

buildscript {
    repositories {
        mavenCentral()
    }
}

allprojects {
    repositories {
        mavenCentral()
    }

}
subprojects {
    val kotestVer = "4.1.3"
    val queryDslVer = "4.3.1"
    val loggerVer = "1.7.10"
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-spring")

    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    group = "com.colamooon"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_14

    repositories {
        mavenCentral()
    }

    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.Embeddable")
        annotation("javax.persistence.MappedSuperclass")
    }

    noArg {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.Embeddable")
        annotation("javax.persistence.MappedSuperclass")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("com.auth0:java-jwt:3.10.3")
        implementation("org.modelmapper:modelmapper:2.3.8")

        api("com.querydsl:querydsl-jpa:$queryDslVer")
        kapt("com.querydsl:querydsl-apt:$queryDslVer:jpa")
        kapt("org.springframework.boot:spring-boot-configuration-processor")
        implementation("io.github.microutils:kotlin-logging:$loggerVer")
        implementation("org.apache.commons:commons-lang3")
        implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.6.2")

        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        runtimeOnly("mysql:mysql-connector-java")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        testImplementation("io.projectreactor:reactor-test")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVer")
        testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVer")
        testImplementation("io.kotest:kotest-property-jvm:$kotestVer")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "14"
        }
    }

    sourceSets {
        kotlin.sourceSets.register("$buildDir/generated/source/kapt/main")
    }
}


project(":application") {
    dependencies {
        api(project(":library"))
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        testImplementation("org.springframework.security:spring-security-test")
    }
    tasks.getByName<BootJar>("bootJar") { enabled = true }
}

project(":library") {
    dependencies {
    }
    tasks.getByName<Jar>("jar") { enabled = true }
    tasks.getByName<BootJar>("bootJar") { enabled = false }
}