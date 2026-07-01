
plugins {
    kotlin("jvm") version "2.3.21"
    application
}

group = "banking.system"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("banking.system.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}