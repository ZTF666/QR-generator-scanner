package com.example.qrscanner_maker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Button Generator,Scanner;
    ImageView GeneratedQrImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.TxtInput);
        Generator=findViewById(R.id.Generate);
        Scanner=findViewById(R.id.Scan);
        GeneratedQrImg=findViewById(R.id.ImgV);

        Generator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = inputValue.getText().toString();

                if (data.isEmpty()) {
                    inputValue.setError("An input is required !");
                } else{

                    QRGEncoder qrg = new QRGEncoder(data, null, QRGContents.Type.TEXT, 255);
                try {
                    Bitmap bitQr = qrg.encodeAsBitmap();
                    GeneratedQrImg.setImageBitmap(bitQr);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
            }
        });

        Scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Scanning.class));
            }
        });
    }
}
