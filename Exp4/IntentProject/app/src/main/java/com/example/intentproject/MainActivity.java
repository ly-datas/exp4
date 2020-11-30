package com.example.intentproject;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText urlEditText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.button = findViewById(R.id.btn);//按钮
        this.urlEditText = findViewById(R.id.editText);//文本框
        button.setOnClickListener(new View.OnClickListener() {//点击事件
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();//获取url
                Intent intent = new Intent(Intent.ACTION_VIEW);//为Intent设置Action
                intent.setData(Uri.parse(url));
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory("my_category");
                startActivity(intent);
            }
        });
    }
}
