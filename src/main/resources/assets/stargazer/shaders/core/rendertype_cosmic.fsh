#version 150

#moj_import <fog.glsl>
#moj_import <matrix.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;
uniform float GameTime;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in vec3 localPos;
in float vertexDistance;

const vec3 COLORS[16] = vec3[16](
vec3(0.022087, 0.098399, 0.110818), vec3(0.011892, 0.095924, 0.089485),
vec3(0.027636, 0.101689, 0.100326), vec3(0.046564, 0.109883, 0.114838),
vec3(0.064901, 0.117696, 0.097189), vec3(0.063761, 0.086895, 0.123646),
vec3(0.084817, 0.111994, 0.166380), vec3(0.097489, 0.154120, 0.091064),
vec3(0.106152, 0.131144, 0.195191), vec3(0.097721, 0.110188, 0.187229),
vec3(0.133516, 0.138278, 0.148582), vec3(0.070006, 0.243332, 0.235792),
vec3(0.196766, 0.142899, 0.214696), vec3(0.047281, 0.315338, 0.321970),
vec3(0.204675, 0.390010, 0.302066), vec3(0.080955, 0.314821, 0.661491)
);

vec2 star_layer_uv(vec2 uv, float layer) {
mat2 rotate = mat2_rotate_z(radians((layer * layer * 4321.0 + layer * 9.0) * 2.0));
float scale = (4.5 - layer / 4.0) * 2.0;

// Random scattered directions per layer
vec2 dir = vec2(cos(layer * 2.3), sin(layer * 2.3));

// Swirling vortex effect
float angle = GameTime * 0.02 * (layer + 1.0);
mat2 swirl = mat2(cos(angle), -sin(angle), sin(angle), cos(angle));

vec2 translation = (swirl * dir) * (2.0 + layer) * (GameTime * 0.5);
vec2 shifted = uv + vec2(0.25) + translation;
return (rotate * shifted) * scale;
}

out vec4 fragColor;

void main() {
// 1. Cube mapping to prevent stretching (Just like star_leaves)
vec3 n = abs(cross(dFdx(localPos), dFdy(localPos)));
vec2 uv;
if (n.x > n.y && n.x > n.z) {
uv = localPos.yz;
} else if (n.y > n.z) {
uv = localPos.xz;
} else {
uv = localPos.xy;
}

const float ZOOM = 0.0075;
uv *= ZOOM;

// 2. Sample the base layer (Sampler0)
vec3 color = texture(Sampler0, uv).rgb * COLORS[0];

// 3. Sample the star layers (Sampler1) using the working 2D UV logic
for (int i = 0; i < 8; ++i) {
vec2 layerUV = star_layer_uv(uv, float(i + 1));
color += texture(Sampler1, layerUV).rgb * (COLORS[i] * 4.0);
}

// 4. Output with fog
fragColor = linear_fog(vec4(color, 1.0), vertexDistance, FogStart, FogEnd, FogColor);
}
