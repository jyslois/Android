package com.android.practicesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        // 두 번째 매개 변수: 데이터베이스 파일명
        super(context, "memodb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String memoSQL = "create table tb_memo (" + "_id integer primary key autoincrement," +
                         "title," + "content)";
        // 테이블 생성
        // 칼럼은 _id, title, content 3개로 구성
        db.execSQL(memoSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 만약 테이블 생성을 잘못해서 수정해도 onCreate() 함수는 앱 설치 후 한 번만 호출되므로
        // 수정한 부분이 반영되지 않는다. 이럴 때 DATABASE_VERSION의 값이 증가하면 onUpgrade() 함수가
        // 자동으로 호출되고, 잘못 만든 테이블을 삭제 후 다시 만든다.
        if (newVersion == DATABASE_VERSION) {
            // 잘못 만든 테이블 삭제
            db.execSQL("drop table tb_memo");
            // 테이블 다시 만들기
            onCreate(db);
        }
    }
}
