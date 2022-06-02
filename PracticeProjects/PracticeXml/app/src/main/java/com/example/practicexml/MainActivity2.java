package com.example.practicexml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button1 = findViewById(R.id.버튼1);

    }

    int num = 0;

    public void clickButton2(View v) {
        if (num == 0) {
            button1.setVisibility(View.INVISIBLE);
            num = 1;
        } else if (num == 1) {
            button1.setVisibility(View.VISIBLE);
            num = 0;
        }
    }

}
