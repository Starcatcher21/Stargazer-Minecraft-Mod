#version 150

uniform sampler2D Sampler0;

in vec2 texCoord0;
in vec4 vertexColor;

out vec4 fragColor;

void main() {
//    vec4 sprite = textureGather(Sampler0, texCoord0);
//    float brightness = max(sprite.r, max(sprite.g, sprite.b));
//
//    // The source Stargazer sky texture contains star sprites on black.
//    // Discard the black background so only the star shapes draw.
//    if (sprite.a < 0.015 || brightness < 0.02) {
//        discard;
//    }

//    vec4 color = sprite * vertexColor;
    vec4 color = vec4(0.5,1.0,0.0,1.0) * vertexColor;
    fragColor = vec4(color.rgb * 2.15, color.a);
}
