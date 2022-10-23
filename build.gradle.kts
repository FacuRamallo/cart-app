plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	java
	application
	id("idea")
}

group = "com.backend"
version = "0.0.1-SNAPSHOT"
java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter")
	compileOnly ("org.springframework.boot:spring-boot-devtools")
	annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")

	implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0-rc2")

	testImplementation ("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.rest-assured:spring-mock-mvc:5.2.0")
	testImplementation("org.testcontainers:junit-jupiter:1.17.4")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
	testImplementation("org.mockito:mockito-junit-jupiter:4.8.0")
	testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.9.0")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

sourceSets{
	create("integrationTests"){
		compileClasspath += sourceSets.main.get().output
		runtimeClasspath += sourceSets.main.get().output
	}

}

val integrationTestsImplementation: Configuration by configurations.getting {
	extendsFrom(configurations.implementation.get())
	extendsFrom(configurations.testImplementation.get())
}

configurations["integrationTestsRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())
configurations["integrationTestsRuntimeOnly"].extendsFrom(configurations.testRuntimeOnly.get())

val integrationTests = task<Test>("integrationTests") {
	description = "Runs integration tests."
	group = "com.backend"

	testClassesDirs = sourceSets["integrationTests"].output.classesDirs
	classpath = sourceSets["integrationTests"].runtimeClasspath

	shouldRunAfter("test")
}

tasks.check { dependsOn(integrationTests) }

application {
	mainClass.set("com.backend.cartapp.infrastructure.Application")
}