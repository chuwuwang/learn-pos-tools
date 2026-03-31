import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.compose") version "2.1.20"
    id("org.jetbrains.compose") version "1.9.0"
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

    // Source: https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk18on
    // implementation("org.bouncycastle:bcprov-jdk18on:1.80")

    // qrcode
    implementation("io.github.g0dkar:qrcode-kotlin:4.5.0")

    // file selector
    var fileKit = "0.12.0"
    implementation("io.github.vinceglb:filekit-coil:$fileKit")
    implementation("io.github.vinceglb:filekit-core:$fileKit")
    implementation("io.github.vinceglb:filekit-dialogs:$fileKit")
    implementation("io.github.vinceglb:filekit-dialogs-compose:$fileKit")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

compose.desktop {
    application {
        mainClass = "com.sea.pos.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "POS Tools"
            packageVersion = "1.1.22"
            macOS {
                signing { bundleID = "com.sea.pos.tools" }
                iconFile.set( project.file("src/main/resources/icons/app.icns") )
                runtimeEntitlementsFile.set( project.file("runtime-entitlements.plist") )
            }
            windows {
                iconFile.set( project.file("src/main/resources/icons/app.ico") )
            }
            includeAllModules = true
        }
    }
}