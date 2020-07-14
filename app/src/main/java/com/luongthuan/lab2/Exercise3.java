package com.luongthuan.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exercise3 extends AppCompatActivity {
    EditText edtCanh;
    Button btnSend3;
    TextView tvResult3;
    String link3="http://10.0.3.2:9090/cube";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise3);
        edtCanh=findViewById(R.id.edtA);
        btnSend3=findViewById(R.id.btnSend4);
        tvResult3=findViewById(R.id.tvResult4);
        btnSend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String canh = edtCanh.getText().toString().trim();

                 if (Double.parseDouble(canh)<0){
                    Toast.makeText(Exercise3.this, "Vui lòng nhập cạnh lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                PostLoaderEx3 postLoaderEx3 = new PostLoaderEx3(Exercise3.this, tvResult3);
                postLoaderEx3.execute(link3,canh);
            }
        });
    }
}