import java.io.ByteArrayOutputStream

plugins {
    java
    `maven-publish`
    alias(libs.plugins.lavalink)
}

group = "gay.vzt.oss.lavalink.plugin.trackEncoder"

val (versionStr, isSnapshot) = getGitVersion()
version = versionStr

println("Version: $versionStr")

base {
    archivesName = "track-encoder-plugin"
}

lavalinkPlugin {
    name = "track-encoder-plugin"
    apiVersion = libs.versions.lavalink.api
    serverVersion = libs.versions.lavalink.server
    configurePublishing = false
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifactId = base.archivesName.get()
        }
    }
}

fun getGitVersion(): Pair<String, Boolean> {
    var versionStr = ByteArrayOutputStream()
    val result = exec {
        standardOutput = versionStr
        errorOutput = versionStr
        isIgnoreExitValue = true
        commandLine = listOf("git", "describe", "--exact-match", "--tags")
    }

    if (result.exitValue == 0) {
        return versionStr.toString().trim() to false
    }

    versionStr = ByteArrayOutputStream()
    exec {
        standardOutput = versionStr
        errorOutput = versionStr
        commandLine = listOf("git", "rev-parse", "--short", "HEAD")
    }

    return versionStr.toString().trim() to true
}