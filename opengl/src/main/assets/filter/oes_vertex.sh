#!/usr/bin/env bash
attribute vec4 vPosition;
attribute vec2 aTexturePosition;
uniform mat4 vProjectMatrix;
uniform mat4 vViewMatrix;
uniform mat4 vModeMatrix;
varying vec2 vTexturePosition;
void main(){
    gl_Position=vProjectMatrix * vViewMatrix * vModeMatrix * vec4(vPosition.xyz,1);
    vTexturePosition=aTexturePosition;
}