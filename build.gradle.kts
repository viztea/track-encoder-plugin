plugins {
    java
    `maven-publish`
    alias(libs.plugins.lavalink)
}

group = "gay.vzt.oss.lavalink.plugin.trackEncoder"
version = "1.0.1"

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