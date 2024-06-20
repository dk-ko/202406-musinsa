plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"

	kotlin("plugin.jpa") version "1.9.24"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"

	kotlin("kapt") version "1.9.24"
}

group = "com.musinsa"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	runtimeOnly("com.h2database:h2")
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt("jakarta.annotation:jakarta.annotation-api")
	kapt("jakarta.persistence:jakarta.persistence-api")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.mockito:mockito-core")
	testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val generated = file("src/main/generated")

tasks.withType<JavaCompile> {
	options.generatedSourceOutputDirectory.set(generated)
}

sourceSets {
	main {
		kotlin.srcDirs += generated
	}
}

tasks.named("clean") {
	doLast {
		generated.deleteRecursively()
	}
}

kapt {
	generateStubs = true
}
