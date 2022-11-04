package com.example.mosaic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mosaic.view.PaintView;

public class MainActivity extends AppCompatActivity {
    private static final String EXTRA_BITMAP_KEY = "bitmap";
    private static final double BITMAP_SCALING_COEFFICIENT = 0.3;
    private static final int MIN_BRUSH_SIZE = 5;
    private static final int MAX_BRUSH_SIZE = 70;
    private static final int DEFAULT_BRUSH_SIZE = 35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PaintView paintView = findViewById(R.id.paintView);

        Button btnColourRed = findViewById(R.id.btnClrRed);
        btnColourRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setColour(Color.RED);
            }
        });

        Button btnColourYellow = findViewById(R.id.btnClrYellow);
        btnColourYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setColour(Color.YELLOW);
            }
        });

        Button btnColourGreen = findViewById(R.id.btnClrGreen);
        btnColourGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setColour(Color.GREEN);
            }
        });

        Button btnColourBlue = findViewById(R.id.btnClrBlue);
        btnColourBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setColour(Color.BLUE);
            }
        });

        ImageButton imgBtnEraser = findViewById(R.id.imgBtnEraser);
        imgBtnEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setColour(Color.WHITE);
            }
        });

        SeekBar sbSetBrushSize = findViewById(R.id.sbSetBrSize);
        sbSetBrushSize.setProgress(DEFAULT_BRUSH_SIZE);
        sbSetBrushSize.setMax(MAX_BRUSH_SIZE);
        sbSetBrushSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                float value = MIN_BRUSH_SIZE + progress;
                paintView.setBrushSize(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button btnCreateMosaic = findViewById(R.id.btnCreateMosaic);
        btnCreateMosaic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCreateMosaic();
            }
        });
    }

    private void onClickCreateMosaic() {
        PaintView paintView = findViewById(R.id.paintView);
        paintView.setDrawingCacheEnabled(true);
        if (!paintView.isEmpty) {
        Bitmap drawingCache = Bitmap.createBitmap(paintView.getDrawingCache());
            Bitmap scaled = Bitmap.createScaledBitmap(drawingCache,
                    (int)(drawingCache.getWidth()  * BITMAP_SCALING_COEFFICIENT),
                    (int)(drawingCache.getHeight() * BITMAP_SCALING_COEFFICIENT),
                    true);

            Intent intent = new Intent(this, MosaicActivity.class);
            intent.putExtra(EXTRA_BITMAP_KEY, scaled);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Your drawing is empty!", Toast.LENGTH_LONG).show();
        }
    }
}