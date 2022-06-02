package com.example.viewbindingpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.viewbindingpractice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 뷰바인딩 객체 획득
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        //액티비티 화면 출력
        setContentView(binding.getRoot());

        //뷰 객체 이용 : 이때 뷰 객체의 이름은 레이아웃 XML에 등록된 id 속성값이다
        binding.visibleBtn.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               binding.targetView.setVisibility(View.VISIBLE);
           }
        });

        binding.invisibleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.targetView.setVisibility(View.INVISIBLE);
            }
        });


    }

}