plugins {
    kotlin("jvm")
    application
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":rolik"))
    implementation(project(":rolik-lanterna"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
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
