#version 150

#moj_import <minecraft:matrix.glsl>
#moj_import <minecraft:globals.glsl>

uniform sampler2D Sampler0;

in vec4 texProj0;

out vec4 fragColor;

void main() {
    mat4 translate = mat4(
        1.0, -1.0, 0.0, 8.0 * (GameTime * 16 * -1),
        -1.0, 1.0, 0.0, 8.0 * (GameTime * 16),
        0.0, 0.0, 1.0, -0.2,
        0.0, 0.0, -0.2, 1.0
    );
    mat2 rotate = mat2_rotate_z(radians(45.0));
    mat2 scale = mat2(1.0);
    mat4 fin = mat4(scale * rotate) * translate;
    vec4 color = textureProj(Sampler0, texProj0 * fin);
#ifdef ALPHA_CUTOUT
    if (color.a < ALPHA_CUTOUT) {
        discard;
    }
#endif
    fragColor = vec4(1.0, 0.5, 0.5, 1.0);
}
