package com.example.practiceeventhandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.practiceeventhandling.databinding.ActivityMainBinding;

// 엑티비티 클래스 자체를 이벤트 핸들러로 만들기 위해 다음 두 개의 인터페이스를 클래스 선언 부분에
// 상속 받고, 인터페이스의 추상 함수를 재정의
public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    ActivityMainBinding binding;

    // onCreate() 함수에 이벤트 등록
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bellName.setOnClickListener(this);
        binding.label.setOnClickListener(this);
        binding.repeatCheck.setOnCheckedChangeListener(this);
        binding.vibrate.setOnCheckedChangeListener(this);
        binding.onOff.setOnCheckedChangeListener(this);

    }

    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onClick(View v) {
        if (v == binding.bellName) {
            showToast("bell text click event...");
        } else if (v == binding.label) {
            showToast("label text click event...");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == binding.repeatCheck) {
            showToast("repeat checkbox is " + isChecked);
        } else if (buttonView == binding.vibrate) {
            showToast("vibrate checkbox is " + isChecked);
        } else if (buttonView == binding.onOff) {
            showToast("switch is " + isChecked);
        }
    }

    // 터치 이벤트
    double initX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 만약 화면에 터치된 순간의 이벤트가 발생하면
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 화면 내에서 터치 이벤트가 발생한 지점의 좌표 X를 initX에 지정
            initX = event.getRawX();
        // 만약 터치를 떼는 순간의 이벤트가 발생하면
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // initX와 현재 터치를 떼는 이벤트가 발생한 지점의 좌표 X의 값을 구해 diffX에 지정하고
            double diffX = initX - event.getRawX();

            // 만약 그 차이가 30 이상이면 왼쪽, -30 이하면 오른쪽으로 화면을 밀었다는 문구를 띄우기.
            // 이 차이는 크면 커 질수록 확실하게 화면 끝까지 밀어야 문구가 출력됨.
            if (diffX > 30) {
                showToast("왼쪽으로 화면을 밀었습니다.");
            } else if(diffX < -30) {
                showToast("오른쪽으로 화면을 밀었습니다.");
            }
        }
        return true;
    }

    // 키 이벤트
    long initTime = 0L;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 만약 눌러진 키 버튼이 뒤로가기 버튼이라면
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 만약 initTime과 버튼을 누른 현재 시간이 3초 이상이라면 - back button을 누른 지 3초가 지났다면,
            if (System.currentTimeMillis() - initTime > 3000) {
                // 메세지 띄우기
                showToast("종료하려면 한 번 더 누르세요.");
                // 현재 시간을 initTime에 지정
                initTime = System.currentTimeMillis();
            } else {
                // 3초 이내에 BackButton이 두 번 눌린 경우 Activity 종료
                finish();
            }
            // true를 리턴하는 경우, 기존 시스템이 가지는 해당 키 이벤트에 대한 처리를
            // 무시하겠다는 의미이다.
            // 즉, Toast메시지만 띄우고 기존의 뒤로가기 버튼의 동작인 이전 액티비티 이동
            // 또는 앱 종료와 같은 기능을 무시하고자 할 경우 true로 리턴해 주면 된다.
            return true;
            // 만약 Toast메시지를 띄우고 기존의 동작을 수행하길 원한다면,
            // 이 리턴문을 삭제하면 된다.
        }
        // 뒤로가기 키를 제외한 나머지 키 이벤트에 대한 처리는 시스템에 맡긴다
        return super.onKeyDown(keyCode, event);
    }

    // 키 이벤트
//    long initTime = 0L;
//    @Override
//    public void onBackPressed() {
//        if (System.currentTimeMillis() - initTime > 3000) {
//            // 메세지 띄우기
//            showToast("종료하려면 한 번 더 누르세요.");
//            // 현재 시간을 initTime에 지정
//            initTime = System.currentTimeMillis();
//        } else {
//            // 3초 이내에 BackButton이 두 번 눌린 경우 Activity 종료
//            finish();
//        }
//    }


}
