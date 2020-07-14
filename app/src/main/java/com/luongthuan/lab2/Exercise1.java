package com.luongthuan.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Exercise1 extends AppCompatActivity {
    String link = "http://10.0.3.2:9090/student";

    EditText edtName, edtScore;
    Button btnSend1;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);
        edtName = findViewById(R.id.edtA);
        edtScore = findViewById(R.id.edtB);
        btnSend1 = findViewById(R.id.btnSend4);
        tvResult = findViewById(R.id.tvResult4);
        btnSend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtName.getText().toString().trim();
                String score=edtScore.getText().toString().trim();
                PostLoaderEx1 postLoaderEx1=new PostLoaderEx1(Exercise1.this,tvResult);
                postLoaderEx1.execute(link,name,score);
            }
        });
    }
}