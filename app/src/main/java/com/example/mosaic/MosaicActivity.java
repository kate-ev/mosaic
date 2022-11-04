package com.example.mosaic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mosaic.view.MosaicView;

public class MosaicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mosaic_view);

        Button btnStartAgain = findViewById(R.id.btnStartAgain);
        btnStartAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStartAgain();
            }
        });
    }

    private void onClickStartAgain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}