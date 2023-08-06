@file:Suppress("PropertyName")
val ktor_version: String by project
val ktx_html_version: String by project

val kotlin_react_version: String by project
val kotlin_react_router_version: String by project

plugins {
    kotlin("multiplatform") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
    js {
        binaries.executable()
        browser {
            @Suppress("DEPRECATION")
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("io.ktor:ktor-server-netty:$ktor_version")
                implementation("io.ktor:ktor-server-html-builder-jvm:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$ktx_html_version")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.606")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:$kotlin_react_version")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:$kotlin_react_version")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.11.1-pre.606")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:$kotlin_react_router_version")
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("org.example.application.ServerKt")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}