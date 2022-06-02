package com.example.doitmission03;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

//    ScrollView scrollView;
//    ScrollView scrollView2;
//    BitmapDrawable bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) 버튼 클릭할 때 이미지 왔다 갔다 하기.
        // XML 레이아웃에 들어 있는 두 개의 이미지뷰를 findViewById 메서드로 찾은 후 변수로 할당
        // findViewById는 Activity클래스에 있는 메서드로, XML과 엑티비티 컴포넌트를 이어줌.
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);

//        // 2) 이미지 크기 설정 및 스크롤뷰 추가
//        // 레이아웃에 정의된 뷰 객체 참조
//        scrollView = findViewById(R.id.scrollView);
//        scrollView2 = findViewById(R.id.scrollView2);
//        // 수평 스크롤바 사용 가능 설정
//        scrollView.setHorizontalScrollBarEnabled(true);
//        scrollView2.setHorizontalScrollBarEnabled(true);
//
//        // 리소스 이미지 참조. getResources() 메서드는 엑티비티 클래스에 있음.
//        Resources res = getResources();
//        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.mountain); // Resources객채의 getDrawable()
//        // BitmapDrawable 객채의 아래의 두 메서드를 사용하면 원본 이미지의 가로와 세로 크기를 알 수있음
//        int bitmapWidth = bitmap.getIntrinsicWidth();
//        int bitmapHeight = bitmap.getIntrinsicHeight();
//        // 이미지 리소스와 이미지 크기 설정 (이미지 뷰의 LayoutParams객채의 width와 height속성으로 설정)
//        // setImageDrawable 메서드를 사용하면 이미지뷰가 이미지의 크기를 화면 크기에 맞게 자동으로 줄이므로
//        // 이미지의 가로와 세로 크기를 직접 원본 이미지 크기 그대로 설정해 주어야 함.
//        imageView.setImageDrawable(bitmap);
//        imageView.getLayoutParams().width = bitmapWidth;
//        imageView.getLayoutParams().height = bitmapHeight;

    }

    public void onButtonUpClicked(View v) {
        showImageTop();
    }

    public void onButtonDownClicked(View v) {
        showImageBottom();
    }

    private void showImageTop() {
        imageView.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
    }

    private void showImageBottom() {
        imageView.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.VISIBLE);
//        // 리소스 이미지 참조. getResources() 메서드는 엑티비티 클래스에 있음.
//        Resources res = getResources();
//        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.ocean); // Resources객채의 getDrawable()
//        // BitmapDrawable 객채의 아래의 두 메서드를 사용하면 원본 이미지의 가로와 세로 크기를 알 수있음
//        int bitmapWidth = bitmap.getIntrinsicWidth();
//        int bitmapHeight = bitmap.getIntrinsicHeight();
//        // 이미지 리소스와 이미지 크기 설정 (이미지 뷰의 LayoutParams객채의 width와 height속성으로 설정)
//        // setImageDrawable 메서드를 사용하면 이미지뷰가 이미지의 크기를 화면 크기에 맞게 자동으로 줄이므로
//        // 이미지의 가로와 세로 크기를 직접 원본 이미지 크기 그대로 설정해 주어야 함.
//        imageView2.setImageDrawable(bitmap);
//        imageView2.getLayoutParams().width = bitmapWidth;
//        imageView2.getLayoutParams().height = bitmapHeight;
    }

}