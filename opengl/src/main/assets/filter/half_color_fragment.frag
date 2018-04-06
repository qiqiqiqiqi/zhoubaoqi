#!/usr/bin/env bash
precision mediump float;
uniform sampler2D vTexture;
uniform int vChangeType;
uniform vec3 vChangeData;
varying vec2 vTexturePosition;

void handleColor(vec4 color){
  color.r=max(min(color.r,1.0),0.0);
  color.g=max(min(color.g,1.0),0.0);
  color.b=max(min(color.b,1.0),0.0);
  color.a=max(min(color.a,1.0),0.0);
}
void main(){
   vec4  nColor=texture2D(vTexture,vTexturePosition);
   if(vChangeType==0){
      nColor=texture2D(vTexture,vTexturePosition);
   }else if(vChangeType==1){
      float c=nColor.r*vChangeData.r+nColor.g*vChangeData.g+nColor.b*vChangeData.b;
      nColor=vec4(c,c,c,nColor.a);
   }else if(vChangeType==2){
      nColor=nColor+vec4(vChangeData,0.0);
      handleColor(nColor);
   }else if(vChangeType==3){
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x+vChangeData.r,vTexturePosition.y+vChangeData.r));
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x+vChangeData.r,vTexturePosition.y-vChangeData.r));
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x-vChangeData.r,vTexturePosition.y+vChangeData.r));
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x-vChangeData.r,vTexturePosition.y-vChangeData.r));

      nColor+=texture2D(vTexture,vec2(vTexturePosition.x+vChangeData.g,vTexturePosition.y+vChangeData.g));
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x+vChangeData.g,vTexturePosition.y-vChangeData.g));
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x-vChangeData.g,vTexturePosition.y+vChangeData.g));
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x-vChangeData.g,vTexturePosition.y-vChangeData.g));

      nColor+=texture2D(vTexture,vec2(vTexturePosition.x+vChangeData.b,vTexturePosition.y+vChangeData.b));
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x+vChangeData.b,vTexturePosition.y-vChangeData.b));
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x-vChangeData.b,vTexturePosition.y+vChangeData.b));
      nColor+=texture2D(vTexture,vec2(vTexturePosition.x-vChangeData.b,vTexturePosition.y-vChangeData.b));
      nColor/=13.0;
   }
  gl_FragColor=nColor;
}