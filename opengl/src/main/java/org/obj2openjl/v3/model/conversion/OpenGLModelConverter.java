package org.obj2openjl.v3.model.conversion;

import org.obj2openjl.v3.model.Face;
import org.obj2openjl.v3.model.OpenGLModelData;

import java.util.List;

public interface OpenGLModelConverter {

    public OpenGLModelData convert(List<Face> faces);

}