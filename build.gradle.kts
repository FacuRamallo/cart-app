plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	java
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
	implementation ("org.springframework.boot:spring-boot-starter")
	developmentOnly ("org.springframework.boot:spring-boot-devtools")
	annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation ("org.springframework.boot:spring-boot-starter-test") {
		exclude(mapOf("group" to "org.junit.vintage", "module" to "junit-vintage-engine"))
	}
}

tasks.test {
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
	group = "verification"

	testClassesDirs = sourceSets["integrationTests"].output.classesDirs
	classpath = sourceSets["integrationTests"].runtimeClasspath
	filter {
		includeTestsMatching("ApplicationIntegrationTest")
	}
	shouldRunAfter("test")
}

tasks.check { dependsOn(integrationTests) }
