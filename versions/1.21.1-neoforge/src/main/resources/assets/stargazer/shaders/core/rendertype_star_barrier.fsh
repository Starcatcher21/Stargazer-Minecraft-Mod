#version 150

uniform sampler2D Sampler0;

in vec4 texProj0;
out vec4 fragColor;

void main() {
    vec4 color = textureProj(Sampler0, texProj0);
    if (color.a < 0.1) {
        discard;
    }
    fragColor = vec4(1.0, 0.5, 0.5, color.a);
}
