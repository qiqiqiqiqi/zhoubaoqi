attribute vec4 aPosition;
attribute vec2 aTextureCoord;
attribute vec3 aNormalPosition;
varying vec2 vTextureCoord;
varying vec4 vDiffuse;
uniform mat4 uMmatrix;
uniform mat4 uVmatrix;
uniform mat4 uPmatrix;
uniform mat4 uMVPmatrix;
uniform vec3 uLightLocation;
void main(){
 gl_Position=uPmatrix * uVmatrix * uMmatrix * vec4(aPosition.xyz,1);
 vTextureCoord=aTextureCoord;
 vec3 normalPosition=aNormalPosition;
 vec3 normalVector=normalize((uMmatrix*vec4(normalPosition.xyz,1)).xyz);
 vec3 lightVector=normalize(uLightLocation-(uMmatrix*vec4(aPosition.xyz,1)).xyz);
 vDiffuse=max(0.0,dot(normalVector,lightVector))*vec4(1.0,1.0,1.0,1.0);
}
