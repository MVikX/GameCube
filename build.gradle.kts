plugins {
    kotlin("jvm") version "1.9.22"
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("com.github.johnrengelman.shadow") version "8.1.1" //shadow plugin
}

repositories {
    mavenCentral()
}

javafx {
    version = "23.0.2"
    modules = listOf(
        "javafx.controls",
    )
}

dependencies {
    implementation(kotlin("stdlib"))

    //kotlin-stdlib
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
}

//основной класс
application {
    mainClass.set("MainKt")
}

kotlin {
    jvmToolchain(21)
}
