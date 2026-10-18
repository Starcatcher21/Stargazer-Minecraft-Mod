#version 150

#moj_import <fog.glsl>
#moj_import <light.glsl>

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform int FogShape;
uniform sampler2D Sampler2;

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in ivec2 UV2;

out vec4 vertexColor;
out vec2 texCoord0;
out vec3 positionXYZ;
out float vertexDistance;

void main() {
    vec4 viewPosition = ModelViewMat * vec4(Position, 1.0);
    gl_Position = ProjMat * viewPosition;
    vertexDistance = fog_distance(viewPosition.xyz, FogShape);
    positionXYZ = Position;
    texCoord0 = UV0;
    vertexColor = Color * minecraft_sample_lightmap(Sampler2, UV2);
}
