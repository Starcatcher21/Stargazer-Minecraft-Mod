#version 150

#moj_import <minecraft:light.glsl>
#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV2;
in vec3 Normal;

uniform sampler2D Sampler2;

out vec4 vertexColor;
out vec2 texCoord0;
out vec4 lightMapColor;
out vec3 positionXYZ;

void main() {
// 1. Calculate standard gl_Position
gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

// 2. Pass local/world coordinates to your Aurora Fragment Shader
positionXYZ = Position;

// 3. Pass texture UV mapping
texCoord0 = UV0;

// 4. Sample the lightmap directly using a basic texel fetch
lightMapColor = texelFetch(Sampler2, UV2, 0);

// 5. Combine the base vertex color with the lightmap data
// This bypasses 'minecraft_sample_lightmap' while achieving the same result
vertexColor = Color * lightMapColor;
}