plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("io.gitlab.arturbosch.detekt")
    application
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":rolik"))
    implementation(project(":rolik-lanterna"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
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

application {
    mainClass.set("ru.itmo.sd.rolik.game.MainKt")
}

tasks.withType<JavaExec>().all {
    standardInput = System.`in`
}

detekt {
    buildUponDefaultConfig = true
    allRules = false // Activates all, even unstable rules
    source.setFrom(listOf("src"))
    config = files("$projectDir/config/detekt.yml")
}
