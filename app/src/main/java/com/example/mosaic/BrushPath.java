package com.example.mosaic;

import android.graphics.Path;

public class BrushPath {
    private final Path path;
    private final int colour;
    private final float brushSize;

    public BrushPath(Path path, int colour, float brushSize) {
        this.path = path;
        this.colour = colour;
        this.brushSize = brushSize;
    }

    public Path getPath() {
        return path;
    }

    public int getColour() {
        return colour;
    }

    public float getBrushSize() {
        return brushSize;
    }
}
