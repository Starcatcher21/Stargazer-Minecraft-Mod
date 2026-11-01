#version 150

#moj_import <fog.glsl>

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform int FogShape;

in vec3 Position;

out vec3 localPos;
out float vertexDistance;

void main() {
vec4 viewPosition = ModelViewMat * vec4(Position, 1.0);
gl_Position = ProjMat * viewPosition;
localPos = Position;
vertexDistance = fog_distance(viewPosition.xyz, FogShape);
}
