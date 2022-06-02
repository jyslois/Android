package com.example.visibilitypractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        text = findViewById(R.id.text);
    }

    public void clickButton(View v) {
        if (v == button1) {
            text.setVisibility(View.VISIBLE);
        } else if (v == button2) {
            text.setVisibility(View.INVISIBLE);
        }
    }

}