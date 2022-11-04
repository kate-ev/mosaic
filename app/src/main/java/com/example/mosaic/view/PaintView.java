package com.example.mosaic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mosaic.BrushPath;

import java.util.ArrayList;
import java.util.List;

// Zīmēšanas loģika paņemta no https://www.geeksforgeeks.org/how-to-create-a-paint-application-in-android/

public class PaintView extends View {
    private static final int DEFAULT_BRUSH_COLOUR = Color.RED;
    private static final float DEFAULT_BRUSH_SIZE = 35f;

    private Path path = new Path();
    private final Paint paint = new Paint();
    private int currentColour;
    private float currentBrushSize;
    private final List<BrushPath> brushPaths = new ArrayList<>();

    public boolean isEmpty = true;

    public PaintView(Context context) {
        super(context);
        init();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setColour(int colour) {
        brushPaths.add(brushPaths.size() - 1, new BrushPath(path, currentColour, currentBrushSize));
        path = new Path();
        currentColour = colour;
        resetDrawingTools();
    }

    public void setBrushSize(float size) {
        brushPaths.add(brushPaths.size() - 1, new BrushPath(path, currentColour, currentBrushSize));
        path = new Path();
        currentBrushSize = size;
        resetDrawingTools();
    }

    private void init() {
        currentColour = DEFAULT_BRUSH_COLOUR;
        currentBrushSize = DEFAULT_BRUSH_SIZE;
        resetDrawingTools();
    }

    private void resetDrawingTools() {
        paint.setAntiAlias(true);
        paint.setColor(currentColour);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(DEFAULT_BRUSH_SIZE);
        brushPaths.add(new BrushPath(path, currentColour, currentBrushSize));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            default:
                return false;
        }
        isEmpty = false;
        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (BrushPath bp : brushPaths) {
            paint.setColor(bp.getColour());
            paint.setStrokeWidth(bp.getBrushSize());
            canvas.drawPath(bp.getPath(), paint);
        }
    }
}
