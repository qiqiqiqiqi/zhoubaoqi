precision mediump float;
uniform sampler2D uTexture;
varying vec2 vTextureCoord;
varying vec4 vDiffuse;
void main(){
gl_FragColor=(vDiffuse+vec4(0.6,0.6,0.6,1.0))*texture2D(uTexture,vec2(vTextureCoord.s,vTextureCoord.t));
}