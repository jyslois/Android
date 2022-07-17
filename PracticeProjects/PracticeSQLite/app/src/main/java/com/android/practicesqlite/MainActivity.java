package com.android.practicesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.android.practicesqlite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addButton.setOnClickListener(view -> {
            // 입력받은 제목과 내용을 문자열로 전환하여 String 변수에 할당받기
            String title = binding.title.getText().toString();
            String content = binding.content.getText().toString();

            // DBHelper 객체를 통해 SQLiteDatabase 객체 획득
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            // insert SQL문 - 테이블에 제목과 내용 삽입하기 (title, content 콜럼에 values 유저가 입력한 title, content 데이터 삽입)
            db.execSQL("insert into tb_memo (title, content) values (?,?)",
                    new String[]{title, content});
            // 자바 소스 코드에서 SQL 문을 표현할 때 데이터가 들어갈 자리를 ?로 표현한 후
            // 이후 매개변수에서 ?에 해당하는 데이터를 지정하는 방식은 소프트웨어 개발에서 흔히 이용된다.

            db.close();

            // 테이블에 데이터 저장 후에 결과를 확인할 액티비티 ReadDBActivity로 화면 전환
            Intent intent = new Intent(this, ReadDBActivity.class);
            startActivity(intent);
        });
    }
}