plugins {
    kotlin("jvm") version "1.6.20" apply false
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("org.jetbrains.dokka") version "1.6.20"
}

allprojects {
    repositories {
        mavenCentral()
    }
    group = "ru.itmo.sd.rolik"
    version = "0.0.1-SNAPSHOT"
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}

detekt {
    buildUponDefaultConfig = true
    allRules = false // Activates all, even unstable rules
    config = files("$projectDir/config/detekt.yml")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
    }
}
