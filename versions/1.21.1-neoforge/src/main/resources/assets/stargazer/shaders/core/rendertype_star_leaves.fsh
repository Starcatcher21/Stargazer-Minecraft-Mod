#version 150

#moj_import <fog.glsl>
#moj_import <matrix.glsl>

uniform sampler2D Sampler0;
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

vec2 dir = vec2(cos(layer * 2.3), sin(layer * 2.3));

float angle = GameTime * 0.02 * (layer + 1.0);
mat2 swirl = mat2(cos(angle), -sin(angle), sin(angle), cos(angle));

vec2 translation = (swirl * dir) * (2.0 + layer) * (GameTime * 0.5);
vec2 shifted = uv + vec2(0.25) + translation;
return (rotate * shifted) * scale;
}

out vec4 fragColor;

void main() {
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

vec4 color = vec4(0.0);
for (int i = 0; i < 32; ++i) {
int colorIndex = i < 16 ? i : i - 16;
vec2 layerUV = star_layer_uv(uv, float(i + 1));
vec4 tex = texture(Sampler0, layerUV);

// Multiply RGB by Alpha to prevent dark edges when blending
color.rgb += tex.rgb * COLORS[colorIndex] * 4.0 * tex.a;
color.a += tex.a;
}

color.a /= 8.0;
color.a = min(color.a, 1.0);

if (color.a < 0.1) {
discard;
}

fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
}
