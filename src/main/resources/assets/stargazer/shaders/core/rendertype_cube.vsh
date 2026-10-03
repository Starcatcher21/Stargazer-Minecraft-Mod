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
// 1. Calculate standard render position
gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

// 2. Pass texture UV mapping and light calculations
texCoord0 = UV0;
lightMapColor = texelFetch(Sampler2, UV2, 0);
vertexColor = Color * lightMapColor;

// 3. THE ANTIDOTE TO SIDE-WRAPPING: QUAD CORNER GENERATOR
// Extract a 0 to 1 position matrix by evaluating vertex index ordering modulo 4.
// Every block face is made of 4 corner indices, forming a self-contained local map.
int cornerId = gl_VertexID % 4;

vec2 localQuadUV = vec2(0.0);
if (cornerId == 0) localQuadUV = vec2(0.0, 0.0);
else if (cornerId == 1) localQuadUV = vec2(0.0, 1.0);
else if (cornerId == 2) localQuadUV = vec2(1.0, 1.0);
else if (cornerId == 3) localQuadUV = vec2(1.0, 0.0);

// Store local quad coordinates along with the normal vector as a seed to differentiate sides
positionXYZ = vec3(localQuadUV * 1.0, dot(Normal, vec3(1.0, 2.0, 3.0)));
}