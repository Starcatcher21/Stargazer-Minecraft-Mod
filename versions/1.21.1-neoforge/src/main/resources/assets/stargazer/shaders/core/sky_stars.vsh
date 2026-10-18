#version 150

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

in vec3 Position;
in vec2 UV0;
in vec4 Color;

out vec2 texCoord0;
out vec4 vertexColor;

void main() {
    vec4 clip = ProjMat * ModelViewMat * vec4(Position, 1.0);
    clip.z = clip.w * 0.000001;
    gl_Position = clip;
    texCoord0 = UV0;
    vertexColor = Color;
}
