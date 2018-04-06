#!/usr/bin/env bash
attribute vec4 vPosition;
attribute vec2 aTexturePosition;
varying vec2 vTexturePosition;
uniform mat4 vMVPMatrix;
void main(){
 gl_Position=vMVPMatrix * vec4(vPosition.xyz,1);
 vTexturePosition=aTexturePosition;
}