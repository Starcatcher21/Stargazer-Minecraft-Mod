#version 151

// ============================================================================
//                         USER CONFIGURATION BLOCK
//     Modify the values below to fully customize the fluid and colors.
// ============================================================================

// --- RETRO PIXELATION ---
#define PIXEL_DENSITY     16.0  // Number of "pixels" per block unit (16.0 = 16x16 texture look)

// --- VISUAL SCALE & DYNAMICS ---
#define NOISE_SCALE       0.3   // Lower = massive sweeping waves; Higher = tight, busy ripples
#define ANIMATION_SPEED   2.0   // Master velocity of the fluid flow
#define SWIRL_INTENSITY   2.0   // How heavily the liquid eddies twist into each other

// --- THEME & BRIGHTNESS CONTROLS ---
#define MASTER_BRIGHTNESS 0.55  // Controls the overall intensity of the fluid colors
#define HIGHLIGHT_SHEEN   0.45  // Strength of the bright reflections on wave crests
#define EMISSIVE_FLOOR    0.35  // Brightness in pitch black darkness/caves (0.0 = dark, 1.0 = neon)

// --- COLOR RANGE SELECTION (HSV Spectrum) ---
#define HUE_MIN           0.15  // The starting color bound (Vibrant Lime/Emerald Green)
#define HUE_MAX           0.70  // The ending color bound (Deep Saturated Cyan)
#define COLOR_CYCLE_SPEED 0.015 // How fast the liquid shifts between green and cyan

// --- CRT RETRO FILTER ---
#define SCANLINE_DENSITY  500.0 // Number of horizontal monitor rows across the face
#define SCANLINE_DARKNESS 0.06  // Opacity of the lines (0.0 = completely disabled)

// ============================================================================
//      END OF CONFIGURATION - CORE LOGIC BELOW
// ============================================================================

#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:projection.glsl>
#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:globals.glsl>

in vec4 vertexColor;
in vec2 texCoord0;
in vec4 lightMapColor;
in vec3 positionXYZ;

uniform sampler2D Sampler0;

out vec4 fragColor;

#define TWO_PI 6.28318530718

float noise(in vec2 uv) {
return fract(sin(dot(uv, vec2(12.9898, 78.233))) * 43758.5453123);
}

float smoothNoise(in vec2 uv) {
vec2 i = floor(uv);
vec2 f = fract(uv);
f = f * f * (3.0 - 2.0 * f);

float a = noise(i);
float b = noise(i + vec2(1.0, 0.0));
float c = noise(i + vec2(0.0, 1.0));
float d = noise(i + vec2(1.0, 1.0));

return mix(mix(a, b, f.x), mix(c, d, f.x), f.y);
}

float fluidFBM(in vec2 uv) {
float value = 0.0;
float amplitude = 0.5;
for (int i = 0; i < 6; i++) {
value += amplitude * smoothNoise(uv);
uv *= 2.0;
amplitude *= 0.5;
}
return value;
}

vec3 hsv2rgb(vec3 c) {
vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

void main() {
vec4 baseColor = texture(Sampler0, texCoord0);

// Setup animation time with safety wrapping
float time = mod(GameTime * 1200.0 * ANIMATION_SPEED, 3600.0);

// --- WORLD-SPACE POSITIONING ---
vec3 fdx = dFdx(positionXYZ);
vec3 fdy = dFdy(positionXYZ);
vec3 normal = normalize(cross(fdx, fdy));
vec3 absNormal = abs(normal);

vec2 projectedCoords;
if (absNormal.y > absNormal.x && absNormal.y > absNormal.z) {
projectedCoords = positionXYZ.xz;
} else if (absNormal.x > absNormal.z) {
projectedCoords = positionXYZ.zy;
} else {
projectedCoords = positionXYZ.xy;
}

// --- 16x16 PIXELATION STEP ---
// Snaps coordinates to a pixel grid relative to block sizes
projectedCoords = floor(projectedCoords * PIXEL_DENSITY) / PIXEL_DENSITY;
// ----------------------------------------------------

// LIQUID DOMAIN WARPING
vec2 uv = projectedCoords * NOISE_SCALE;

vec2 timeOffset1 = vec2(sin(time * 0.005), cos(time * 0.005)) * 10.0;
vec2 timeOffset2 = vec2(cos(time * 0.006), sin(time * 0.004)) * 12.0;

vec2 q = vec2(
fluidFBM(uv + timeOffset1),
fluidFBM(uv + timeOffset2)
);

vec2 timeOffset3 = vec2(sin(time * 0.008), cos(time * 0.007)) * 15.0;
vec2 timeOffset4 = vec2(cos(time * 0.005), sin(time * 0.009)) * 13.0;

vec2 r = vec2(
fluidFBM(uv + 4.0 * q + timeOffset3 + vec2(0.0, 2.5)),
fluidFBM(uv + 4.0 * q + timeOffset4 + vec2(5.2, 0.0))
);

vec2 liquidUV = uv + SWIRL_INTENSITY * r;

// Pixelate the warped liquid space again to maintain crisp edges post-distortion
liquidUV = floor(liquidUV * PIXEL_DENSITY) / PIXEL_DENSITY;

// Sample the Fluid Noise maps
float densityA = fluidFBM(liquidUV);
float densityB = fluidFBM(liquidUV + vec2(1.5, -2.3));

float liquidMask = mix(densityA, densityB, 0.5);
liquidMask = smoothstep(0.15, 0.80, liquidMask);

// Palette & HSV Engine
float hueTime = mod(time * COLOR_CYCLE_SPEED, TWO_PI);
float hueCycle = sin(hueTime + densityA * 0.3) * 0.5 + 0.5;

float baseHue = mix(HUE_MIN, HUE_MAX, hueCycle);
float accentHue = mix(HUE_MAX, HUE_MAX + 0.07, hueCycle + r.x * 0.1);

vec3 primaryColor  = hsv2rgb(vec3(baseHue, 1.0, MASTER_BRIGHTNESS));
vec3 neonAccent    = hsv2rgb(vec3(accentHue, 1.0, MASTER_BRIGHTNESS * 0.95));
vec3 neonHighlight = hsv2rgb(vec3(accentHue - 0.05, 0.6, MASTER_BRIGHTNESS * 1.15));

vec3 finalLiquidColor = mix(primaryColor, neonAccent, densityB);
finalLiquidColor += neonHighlight * pow(liquidMask, 3.0) * HIGHLIGHT_SHEEN;

vec3 jadeBackground = vec3(0.00, 0.015, 0.008);

// Combine Layers
vec3 finalRGB = (baseColor.rgb * vertexColor.rgb) + jadeBackground + (finalLiquidColor * liquidMask * 1.35);
float finalAlpha = max(baseColor.a, 1.0) * vertexColor.a * ColorModulator.a;

// --- CRT SCANLINE OVERLAY ---
float scanline = sin(texCoord0.y * SCANLINE_DENSITY) * SCANLINE_DARKNESS + (1.0 - SCANLINE_DARKNESS);
finalRGB *= scanline;

// Final Output
fragColor = vec4(finalRGB, finalAlpha) * ColorModulator;
fragColor.rgb = max(fragColor.rgb, finalLiquidColor * liquidMask * EMISSIVE_FLOOR + jadeBackground);
}