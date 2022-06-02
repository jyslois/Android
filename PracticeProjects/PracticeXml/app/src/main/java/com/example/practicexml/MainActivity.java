package com.example.practicexml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.HORIZONTAL);

        Button button1 = new Button(this);
        button1.setText("버튼 1");
        linear.addView(button1);

        Button button2 = new Button(this);
        button2.setText("버튼 2");
        linear.addView(button2);

        setContentView(linear);

    }
}