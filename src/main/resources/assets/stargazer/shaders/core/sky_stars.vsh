#version 150

#moj_import <minecraft:projection.glsl>
#moj_import <minecraft:dynamictransforms.glsl>

in vec3 Position;
in vec2 UV0;
in vec4 Color;

out vec2 texCoord0;
out vec4 vertexColor;

void main() {
    vec4 clip = ProjMat * ModelViewMat * vec4(Position, 1.0);

    // 1.21.11 uses normal depth here: clear sky is near 1.0, blocks are closer.
    // Pin stars to far depth so clear sky shows them but blocks occlude them.
    clip.z = clip.w * 0.999999;

    gl_Position = clip;
    texCoord0 = UV0;
    vertexColor = Color;
}
