plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":rolik"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("com.googlecode.lanterna:lanterna:3.1.1")
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
