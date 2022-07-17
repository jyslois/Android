package com.android.practicesqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.practicesqlite.databinding.ActivityReadDbactivityBinding;

public class ReadDBActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityReadDbactivityBinding binding = ActivityReadDbactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // DBHelper 객체를 통해 SQLiteDatabase 객체 획득
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        // select SQL 구문: title, content column 선택 from tb_memo table order by 소팅 조건. 1건만 뽑기.
        Cursor cursor = db.rawQuery("select title, content from tb_memo order by _id desc limit 1", null);

        // Cursor 객체를 통해 행을 선택하고 선택한 행의 열 데이터를 획득해서 화면에 띄우기기
        while (cursor.moveToNext()) {
            binding.readTitle.setText(cursor.getString(0));
            binding.readContent.setText(cursor.getString(1));
        }

        db.close();
    }
}