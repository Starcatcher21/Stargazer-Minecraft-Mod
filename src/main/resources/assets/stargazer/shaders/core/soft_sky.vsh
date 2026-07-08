#version 150

#moj_import <minecraft:projection.glsl>
#moj_import <minecraft:dynamictransforms.glsl>

in vec3 Position;

out vec2 screenUv;

void main() {
    // Position is already a full-screen clip-space quad: x/y are -1..1.
    // Do not reconstruct a camera ray from ProjMat/ModelViewMat here.
    // Projection/FOV/view-bob changes are what made the lighting slide while walking.
    gl_Position = vec4(Position.xy, 0.0, 1.0);
    screenUv = Position.xy * 0.5 + 0.5;
}
