#version 150

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
gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

positionXYZ = Position;
texCoord0 = UV0;

lightMapColor = texture(Sampler2, UV2);

vertexColor = Color * lightMapColor;
}