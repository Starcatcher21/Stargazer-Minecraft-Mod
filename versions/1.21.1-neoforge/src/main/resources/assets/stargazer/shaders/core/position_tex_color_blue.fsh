#version 150

uniform vec4 ColorModulator;

in vec4 vertexColor;
out vec4 fragColor;

void main() {
    vec4 color = vertexColor * ColorModulator;
    if (color.a == 0.0) {
        discard;
    }
    fragColor = vec4(color.r, color.g, 1.0 - color.b, color.a);
}
