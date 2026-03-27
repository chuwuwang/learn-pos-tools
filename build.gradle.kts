import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.compose") version "2.1.21"
    id("org.jetbrains.compose") version "1.6.10"
}

group = "com.sea.pos.tools"
version = "1.1.1-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    val list = "include" to listOf("*.jar")
    val map = mapOf("dir" to "libs", list)
    val fileTree = fileTree(map)
    implementation(fileTree)
    implementation(compose.material3)
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.1")

    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")

    implementation("io.github.g0dkar:qrcode-kotlin:4.5.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "com.sea.pos.tools"
            packageVersion = "1.1.1"
            macOS {
                signing { bundleID = "com.sea.pos.tools" }
                // val launcher = project.file("icon.ic" + "ns")
                // iconFile.set(launcher)
            }
            includeAllModules = true
        }
    }
}