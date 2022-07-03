package com.android.practiceactivitysettings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.android.practiceactivitysettings.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 키보드가 보이거나 숨겨지게 하는 기능을 제공하는 클래스
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        binding.lab3Button.setOnClickListener(view -> {
            // 현상황과 반대로 키보드를 제어한다. 안 보이면 보이게 하고, 보이면 사라지게 한다.
            manager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        });

        // API30부터는 WindowInsetsController 객체에 의해 상태바와 네비게이션 바가 나오지 않도록 설정한다.
        // 그 전 버전에서는 Windows 객체의 setFlag() 함수를 이용한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Window window = getWindow();
            WindowInsetsController controller = window.getInsetsController();
            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars()|WindowInsets.Type.navigationBars());
            } else {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }
    }
}