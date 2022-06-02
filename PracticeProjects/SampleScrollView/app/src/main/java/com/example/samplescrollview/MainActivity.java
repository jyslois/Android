package com.example.samplescrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.BitmapRegionDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ImageView imageView;
    BitmapDrawable bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃에 정의된 뷰 객체 참조하여 변수에 대입
        scrollView = findViewById(R.id.scrollView);
        imageView = findViewById(R.id.imageView);
        // 수평 스크롤바 사용 가능 설정
        scrollView.setHorizontalScrollBarEnabled(true);

        // 리소스의 이미지 참조.
        // 이미지는 Resources 객채의 getDrawable 메서드를 이용해 코드에서
        // BitmapDarawable 객채로 만들어진다다
        // 액티비티에 정의된 getResources 메서드를 이용하면 Resources 객체를 참조할 수 있다.
        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.mountain);
        // BitmapDrawable 객채의 getIntrinsicWidth()와 getIntrinsicHeight() 메서드를
        // 사용하면 원본 이미지의 가로와 세로 크기를 알 수 있다.
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        // 이미지 리소스와 이미지 크기 설정
        // 이미지뷰에 이미지를 설정하는 방법 중 하나로 setImageDrawable 메서드가 있는데,
        // 이 메서드를 사용하면 이미지뷰가 이미지의 크기를 화면 크기에 맞게 자동으로 줄인다.
        // 따라서 원본 이미지의 크기 그대로 이미지뷰에 보일 수 있게 이미지의 가로와 세로 크기를
        // 직접 설정해야 스크롤뷰 안에서 스크롤을 사용해 원본 이미지를 볼 수 있다.
        imageView.setImageDrawable(bitmap);
        // 위의 메서드들로 알아낸 가로와 세로 크기 값은
        // 이미지 뷰에 설정된 LayoutParams객체의 width와 height 속성으로 설정할 수 있다.
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }

    public void onButtonClicked(View v) {
        changeImage();
    }

    private void changeImage() {
        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.ocean);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }

}