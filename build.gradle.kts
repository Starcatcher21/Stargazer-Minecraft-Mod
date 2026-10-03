import gg.meza.stonecraft.mod

plugins {
    id("gg.meza.stonecraft")
}

// build.gradle.kts
repositories {
    mavenLocal()
    mavenCentral()
    maven("https://cursemaven.com")
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    maven("https://modmaven.dev/")
    maven("https://api.modrinth.com/maven")
    maven("https://maven.shedaniel.me")
    maven("https://maven.neoforged.net/releases/")
    maven("https://maven.parchmentmc.org")
}

subprojects {
    apply(plugin = "java")

    sourceSets {
        named("main") {
            resources {
                srcDirs(generatedDir)
            }
        }
    }
    if (project.name.contains("fabric")) {
        loom {
            accessWidenerPath = file("src/main/resources/stargazer.accesswidener")
        }
    }

    if (project.name == "1.21.1-neoforge") {
        pluginManager.withPlugin("dev.architectury.loom") {
            dependencies {
                // The Minecraft dependency is usually added by Stonecraft;
                // only add it if it's missing in your setup.
                // minecraft("com.mojang:minecraft:${stonecutter.current.version}")

                "mappings"(
                    loom.layered {
                        officialMojangMappings()
                        parchment(
                            "org.parchmentmc.data:parchment-1.21.1:" +
                                    "${project.mod.prop("parchment_mappings_version")}@zip"
                        )
                    }
                )
            }
        }
    }
}

loom {
    version = project.mod.version
    enableTransitiveAccessWideners = true
}

// Add generated datagen assets to main resource source set
val generatedDir = file("src/main/generated")

fabricApi {
    configureDataGeneration {
        client = true
        createRunConfiguration = false
    }
}

// Ensure the generated resources are included in the source sets
sourceSets {
    main {
        resources {
            srcDir(generatedDir)
        }
    }
}

modSettings {
    clientOptions {
        fov = 110
        guiScale = 3
        narrator = false
        darkBackground = true
        musicVolume = 0.0
    }

    variableReplacements =
        mapOf(
            "minecraftVersionVirtual" to stonecutter.current.version,
            "forgeLoaderVersion" to
                    if (project.mod.isForge) {
                        project.mod
                            .prop("forge_version")
                            .substringAfter("-")
                            .substringBefore(".")
                    } else {
                        ""
                    },
        )
}

java {
    val javaVersion = when {
        // 1.21.1 targets Java 21
        stonecutter.current.project.startsWith("1.21.1") -> 21
        // 26.2 and 26.3 both target Java 25
        stonecutter.current.project.startsWith("26.2") -> 25
        // Fallback (should not normally be hit)
        else -> 21
    }
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
}

dependencies {
    val isFabric   = project.mod.isFabric
    val isNeoForge = stonecutter.current.project.endsWith("-neoforge")
    val is121      = stonecutter.current.project.startsWith("1.21.1")

    // --- Fabric API (Fabric only) ---
    if (isFabric) {
        implementation("net.fabricmc.fabric-api:fabric-api:${project.mod.prop("fabric_version")}")
        implementation("net.fabricmc:fabric-loader:${project.mod.loader}")
    }

    // --- GeckoLib ---
    val geckolibGroup = if (is121) "software.bernie.geckolib" else "com.geckolib"
    val geckolibVer   = project.mod.prop("geckolib_version")
    if (isFabric) {
        implementation("$geckolibGroup:geckolib-fabric-${stonecutter.current.version}:$geckolibVer")
    } else if (isNeoForge) {
        implementation("$geckolibGroup:geckolib-neoforge-${stonecutter.current.version}:$geckolibVer")
    }

    // --- Roughly Enough Items (REI) ---
    compileOnly("me.shedaniel:RoughlyEnoughItems-api:${project.mod.prop("rei_version")}")
    if (isFabric) {
        compileOnly("me.shedaniel:RoughlyEnoughItems-api-fabric:${project.mod.prop("rei_version")}")
        runtimeOnly("me.shedaniel:RoughlyEnoughItems-fabric:${project.mod.prop("rei_version")}")
    } else if (isNeoForge) {
        compileOnly("me.shedaniel:RoughlyEnoughItems-api-neoforge:${project.mod.prop("rei_version")}")
        runtimeOnly("me.shedaniel:RoughlyEnoughItems-neoforge:${project.mod.prop("rei_version")}")
    }

    // --- Cloth Config ---
    val clothVer = project.mod.prop("cloth_config_version")
    if (isFabric) {
        api("maven.modrinth:cloth-config:$clothVer+fabric")
    } else if (isNeoForge) {
        implementation("maven.modrinth:cloth-config:$clothVer+neoforge")
    }

    // --- Architectury API ---
    val archVer = project.mod.prop("architectury_api_version")
    implementation("dev.architectury:architectury:${archVer}")
    if (isFabric) {
        api("dev.architectury:architectury-fabric:$archVer")
    } else if (isNeoForge) {
        implementation("dev.architectury:architectury-neoforge:$archVer")
    }

    // --- StarLib ---
    val starlibProject = stonecutter.current.project
    val starlibVersion = stonecutter.current.version
    val classifier = if (is121) ":dev" else ""

    val starlibCoordinate =
        "com.github.starcatcher21.Starlib:$starlibProject:$starlibVersion$classifier@jar"

    implementation(starlibCoordinate)

    // --- Fabric Energy API (teamreborn:energy, Fabric only) ---
    if (isFabric) {
        implementation("teamreborn:energy:${project.mod.prop("energy_version")}") {
            isTransitive = false
        }
    }
}
