#version 150

#moj_import <minecraft:dynamictransforms.glsl>

in vec2 screenUv;

out vec4 fragColor;

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453123);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    f = f * f * (3.0 - 2.0 * f);

    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));

    return mix(mix(a, b, f.x), mix(c, d, f.x), f.y);
}

void main() {
    vec2 p = screenUv * 2.0 - 1.0;

    // Screen-space vertical sky lighting. This is intentionally not camera-space,
    // so walking, sprinting, FOV changes, and view-bob do not move it around.
    float vertical = clamp(screenUv.y, 0.0, 1.0);
    float horizon = 1.0 - abs(vertical * 2.0 - 1.0);
    float radial = clamp(length(p), 0.0, 1.0);

    float mistA = noise(p * 1.35 + vec2(12.0, -4.0));
    float mistB = noise(vec2(p.x + p.y, p.y - p.x) * 2.10 + vec2(-8.0, 6.0));
    float mist = smoothstep(0.24, 0.92, mistA * 0.62 + mistB * 0.38);

    vec3 lower = vec3(0.006, 0.018, 0.028);
    vec3 middle = vec3(0.015, 0.060, 0.075);
    vec3 upper = vec3(0.030, 0.105, 0.135);
    vec3 aurora = vec3(0.025, 0.245, 0.210);
    vec3 violet = vec3(0.105, 0.050, 0.160);

    vec3 color = mix(lower, middle, smoothstep(0.00, 0.62, vertical));
    color = mix(color, upper, smoothstep(0.52, 1.00, vertical));
    color += aurora * mist * (0.08 + horizon * 0.14);
    color += violet * smoothstep(0.58, 1.0, vertical) * 0.10;

    // Slightly darken the corners to keep the sky feeling deep without relying on camera rays.
    color *= 1.0 - radial * 0.10;

    fragColor = vec4(color * ColorModulator.rgb, 1.0);
}
