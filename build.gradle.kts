plugins {
    java
    alias(libs.plugins.lavalink)
}

group = "gay.vzt.oss.lavalink.plugin.trackEncoder"
version = "1.0.0"

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
