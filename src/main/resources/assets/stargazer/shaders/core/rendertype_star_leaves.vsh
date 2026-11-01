#version 330

#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:projection.glsl>
#moj_import <minecraft:dynamictransforms.glsl>

in vec3 Position;
in vec4 Color;

out vec3 localPos;
out float sphericalVertexDistance;
out float cylindricalVertexDistance;

void main() {
gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

// Pass the true 3D position to the fragment shader
localPos = Position;

sphericalVertexDistance = fog_spherical_distance(Position);
cylindricalVertexDistance = fog_cylindrical_distance(Position);
}
