plugins {
    kotlin("jvm") version "1.6.20" apply false
    kotlin("plugin.serialization") version "1.6.20" apply false
    id("io.gitlab.arturbosch.detekt") version "1.19.0" apply false
    id("org.jetbrains.dokka") version "1.6.20" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
    group = "ru.itmo.sd.rolik"
    version = "0.0.1-SNAPSHOT"
}
