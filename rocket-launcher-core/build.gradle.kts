/**
 * plugins
 */
plugins {
    id("nebula.maven-publish") version "17.3.2"
}

/**
 * buildscript
 */
buildscript {
    dependencies {
    }
}

/**
 * repositories
 */
repositories {
    maven { setUrl("https://funczz.github.io/kotlin-fsm") }
}

/**
 * dependencies
 */
dependencies {
    api("com.github.funczz:fsm:0.1.0")
}

/**
 * plugin: nebula.maven-publish
 */
publishing {
    publications {
        group = "com.github.funczz"
    }

    repositories {
        maven {
            url = uri(
                    PublishMavenRepository.url(
                            version = version.toString(),
                            baseUrl = "$buildDir/mvn-repos"
                    )
            )
        }
    }
}
