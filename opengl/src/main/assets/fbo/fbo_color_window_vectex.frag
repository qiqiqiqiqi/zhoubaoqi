attribute vec4 aPosition;
attribute vec2 aTextureCoord;
varying vec2 vTextureCoord;
void main(){
 gl_Position=vec4(aPosition.xyz,1);
 vTextureCoord=aTextureCoord;
}