#version 150

#moj_import <minecraft:matrix.glsl>
#moj_import <minecraft:globals.glsl>
#moj_import <minecraft:dynamictransforms.glsl>

uniform sampler2D Sampler0;

in vec2 texCoord0;
in vec4 vertexColor;

out vec4 fragColor;

const vec3[] COLORS = vec3[](
    vec3(0.022087, 0.098399, 0.110818),
    vec3(0.011892, 0.095924, 0.089485),
    vec3(0.027636, 0.101689, 0.100326),
    vec3(0.046564, 0.109883, 0.114838),
    vec3(0.064901, 0.117696, 0.097189),
    vec3(0.063761, 0.086895, 0.123646),
    vec3(0.084817, 0.111994, 0.166380),
    vec3(0.097489, 0.154120, 0.091064),
    vec3(0.106152, 0.131144, 0.195191),
    vec3(0.097721, 0.110188, 0.187229),
    vec3(0.133516, 0.138278, 0.148582),
    vec3(0.070006, 0.243332, 0.235792),
    vec3(0.196766, 0.142899, 0.214696),
    vec3(0.047281, 0.315338, 0.321970),
    vec3(0.204675, 0.390010, 0.302066),
    vec3(0.080955, 0.314821, 0.661491)
);

const mat4 SCALE_TRANSLATE = mat4(
    1.0, 0.0, 0.0, 0.25,
    0.0, 1.0, 0.0, 0.25,
    0.0, 0.0, 8.0, 0.0,
    0.0, 0.0, 0.0, 8.0
);

mat2 star_layer(float layer) {
    mat2 rotate = mat2_rotate_z(radians((layer * layer * 4321.0 + layer * 9.0) * 2.0) * (GameTime / 128));
    if (int(layer) % 2 == 0) {
        rotate = mat2_rotate_z(-radians((layer * layer * 4321.0 + layer * 9.0) * 2.0) * (GameTime / 128));
    }

    float scale_factor = (4.5 - layer) / 32.0;
    mat2 scale = mat2(scale_factor);
    return scale * rotate;
}

void main() {
    vec3 color = vec3(0.0, 0.0, 0.0);
    for (int i = 0; i < 8; i++) {
        color += texture(Sampler0, texCoord0 * star_layer(float(i+1))).rgb * ColorModulator.rgb * (COLORS[i] * 4);
    }
    fragColor = vec4(color, 1.0);
}
