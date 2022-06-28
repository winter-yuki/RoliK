plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}

sourceSets {
    main {
        java.setSrcDirs(listOf("src"))
        resources.setSrcDirs(listOf("resources"))
    }
    test {
        java.setSrcDirs(listOf("test"))
        resources.setSrcDirs(listOf("testResources"))
    }
}

detekt {
    buildUponDefaultConfig = true
    allRules = false // Activates all, even unstable rules
    source.setFrom(listOf("src"))
    config = files("$projectDir/config/detekt.yml")
}
