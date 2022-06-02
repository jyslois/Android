package com.example.sampleframelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML 레이아웃에 들어 있는 두 개의 이미지뷰를 findViewById 메서드로 찾은 후 변수로 할당
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }

    public void onButtonClicked(View v) {
        changeImage();
    }

    // 어떤 이미지 뷰가 프레임 레이아웃에 보이고 있는지 알 수 있도록 선언한 변수
    int imageIndex = 0;

    // 버튼을 눌렀을 때 호출되는 메서드
    private void changeImage() {
        if (imageIndex == 0) {
            // 첫번째 이미지 뷰가 보일 때는 값을 0으로 설정
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageIndex = 1;
        } else if (imageIndex == 1) {
            // 두 번째 이미지뷰가 화면에 보일 대는 값을 1로 설정
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageIndex = 0;
        }
    }


}