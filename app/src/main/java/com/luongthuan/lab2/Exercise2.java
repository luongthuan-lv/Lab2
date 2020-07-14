package com.luongthuan.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exercise2 extends AppCompatActivity {
    String link2 = "http://10.0.3.2:9090/rectangle";
    EditText edtRong, edtDai;
    Button btnSend2;
    TextView tvResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);
        edtDai = findViewById(R.id.edtB);
        edtRong = findViewById(R.id.edtA);
        tvResult2 = findViewById(R.id.tvResult4);
        btnSend2 = findViewById(R.id.btnSend4);
        btnSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rong = edtRong.getText().toString().trim();
                String dai = edtDai.getText().toString().trim();
                if (Double.parseDouble(rong) > Double.parseDouble(dai)) {
                    Toast.makeText(Exercise2.this, "Vui lòng nhập chiều rộng nhỏ hơn chiều dài", Toast.LENGTH_SHORT).show();
                    return;
                }else if (Double.parseDouble(rong)<0){
                    Toast.makeText(Exercise2.this, "Vui lòng nhập chiều rộng lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }else if (Double.parseDouble(dai)<0){
                    Toast.makeText(Exercise2.this, "Vui lòng nhập chiều dài lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                PostLoaderEx2 postLoaderEx2 = new PostLoaderEx2(Exercise2.this, tvResult2);
                postLoaderEx2.execute(link2, rong, dai);
            }
        });
    }
}