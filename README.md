# Stargazer

A Minecraft mod called Stargazer.

This project uses [Stonecraft](https://stonecraft.meza.gg) to build for Fabric and NeoForge. It includes:

- a shared mod entrypoint across the selected loaders;
- data generation for `1.21.1-neoforge`, `26.2-fabric`, and `26.2-neoforge`;
- an access widener that Stonecraft converts for loaders that require it;


## Requirements

- JDK 25

The Gradle wrapper downloads the required Gradle distribution automatically.

## Build and verify

Run the available commands from the project root:

```shell
./gradlew buildAndCollect
./gradlew runDatagen
```

`buildAndCollect` builds every configured Minecraft-version and loader pair, then collects the resulting JARs under `build/libs`.
`runDatagen` runs data generation for every configured pair and writes into each `versions/*/src/main/generated` directory.

## Data-generation coverage

- **26.2 Fabric:** tags, loot, recipes, worldgen and dynamic registries, models, advancements, equipment assets, language, and Stargazer's custom Starlib data.
- **1.21.1 NeoForge:** tags, built-in worldgen registries, block/fishing/chest/entity loot tables, recipes, language, and Stargazer's custom Starlib data.
- **26.2 NeoForge:** tags, built-in worldgen registries, block/fishing/chest/entity loot tables, recipes, advancements, models, equipment assets, language, and Stargazer's custom Starlib data.

NeoForge provider parity is incomplete: Fabric's custom dynamic-registry provider and 1.21.1 NeoForge advancements and models are not currently generated.

## Generated resources

Data generation writes to `src/main/generated` inside each generated Stonecutter version project. Stonecraft includes that directory in loader builds automatically.

## Access widening

Add access-widener entries to `src/main/resources/stargazer.accesswidener`. Stonecraft uses it directly where supported and converts it for loaders that use access transformers.
