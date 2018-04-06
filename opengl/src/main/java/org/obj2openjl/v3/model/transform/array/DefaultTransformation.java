package org.obj2openjl.v3.model.transform.array;

import org.obj2openjl.v3.model.Normal;
import org.obj2openjl.v3.model.TexturePoint;
import org.obj2openjl.v3.model.Vertex;

import java.util.List;

public class DefaultTransformation extends ArrayTransformation {

    public DefaultTransformation() {
        super(1);
    }

    @Override
    public List<Vertex> transformVertices(List<Vertex> vertices) {
        return vertices;
    }

    @Override
    public List<Normal> transformNormals(List<Normal> normals) {
        return normals;
    }

    @Override
    public List<Short> transformIndices(List<Short> indices, int maxIndex) {
        return indices;
    }

    @Override
    public List<TexturePoint> transformTextureCoordinates(List<TexturePoint> textureCoordinates) {
        return textureCoordinates;
    }

}
