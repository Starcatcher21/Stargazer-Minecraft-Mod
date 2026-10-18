#version 150

#moj_import <fog.glsl>

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform int FogShape;
uniform sampler2D Sampler2;

in vec3 Position;
in vec4 Color;
in ivec2 UV2;

out vec4 vertexColor;
out vec3 positionXYZ;

void main() {
    vec4 viewPosition = ModelViewMat * vec4(Position, 1.0);
    gl_Position = ProjMat * viewPosition;
    vertexColor = Color * texture(Sampler2, clamp(vec2(UV2) / 256.0, vec2(0.5 / 16.0), vec2(15.5 / 16.0)));
    positionXYZ = Position;
}
