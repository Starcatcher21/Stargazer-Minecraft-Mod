plugins {
    id("dev.kikugie.stonecutter")
    id("gg.meza.stonecraft")
}

stonecutter {
    parameters {
        replacements.string(current.parsed < "1.21.11") {
            replace(".Identifier;", ".ResourceLocation;")
            replace(" Identifier", " ResourceLocation")
            replace("(Identifier", "(ResourceLocation")
            replace("Identifier.fromNamespace", "ResourceLocation.fromNamespace")
            replace("com.geckolib", "software.bernie.geckolib")
        }
    }
}

stonecutter active "1.21.1-neoforge" /* [SC] DO NOT EDIT */
