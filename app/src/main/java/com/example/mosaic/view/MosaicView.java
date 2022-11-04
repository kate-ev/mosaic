package com.example.mosaic.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MosaicView extends View {
    private static final int BITMAP_REPEAT_NUMBER = 16;
    private static final int BITMAP_ROW_NUMBER = 4;

    private static final String EXTRA_BITMAP_KEY = "bitmap";

    private Paint paint;

    public MosaicView(Context context) {
        super(context);
        paint = new Paint();
    }

    public MosaicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MosaicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap toDraw = ((Activity)getContext()).getIntent().getParcelableExtra(EXTRA_BITMAP_KEY);
        int width = toDraw.getWidth();
        int height = toDraw.getHeight();
        int left = 0;
        int top = 0;
        for (int i = 1; i < BITMAP_REPEAT_NUMBER + 1; i++) {
            canvas.drawBitmap(toDraw, left, top, paint);
            left += width;
            if (i % BITMAP_ROW_NUMBER == 0) {
                top += height;
                left = 0;
            }
        }
    }
}
