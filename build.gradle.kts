import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    id("maven-publish")
}

group = "net.mediusecho"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("org.spigotmc:spigot-api:1.21.10-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = groupId
            artifactId = "zones-bukkit"
            version = version
            from(components["kotlin"])
        }
    }
}