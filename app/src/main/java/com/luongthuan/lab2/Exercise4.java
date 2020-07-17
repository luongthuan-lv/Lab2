package com.luongthuan.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Exercise4 extends AppCompatActivity {
    // gắn link api cho máy ảo
    //String link4 = "http://10.0.3.2:9090/phuongtrinh";
    String link4 = "http://192.168.35.104:9090/phuongtrinh";

    EditText edtA, edtB, edtC;
    Button btnSend4;
    TextView tvResult4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise4);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        btnSend4 = findViewById(R.id.btnSend4);
        tvResult4 = findViewById(R.id.tvResult4);

        btnSend4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = edtA.getText().toString().trim();
                String b = edtB.getText().toString().trim();
                String c = edtC.getText().toString().trim();
                PostLoaderEx4 postLoaderEx4 = new PostLoaderEx4(Exercise4.this, tvResult4);
                postLoaderEx4.execute(link4,a,b,c);
            }
        });
    }
}