package com.android.practicedialog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.practicedialog.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    // 이벤트 처리를 위해 다이얼로그 객체를 멤버변수로 선언
    AlertDialog customDialog;
    AlertDialog listDialog;
    AlertDialog alertDialog;

    // 매개 변수의 문자열을 Toast로 띄우는 개발자 함수
    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // Dialog 버튼 이벤트 처리
    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (dialog == customDialog && which == DialogInterface.BUTTON_POSITIVE) {
                showToast("custom dialog 확인 click");
            } else if (dialog == listDialog) {
                // 목록 다이얼로그의 항목이 선택되었을 때, 항목 문자열 획득
                String[] datas = getResources().getStringArray(R.array.dialog_array);
                showToast(datas[which] + " 선택 하셨습니다.");
            } else if (dialog == alertDialog && which == DialogInterface.BUTTON_POSITIVE) {
                showToast("alert dialog ok click");
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // alartButton
        binding.alertButton.setOnClickListener(view -> {
            //AlertDialog 띄우기
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("알림");
            builder.setMessage("정말 종료하시겠습니까?");
            builder.setPositiveButton("OK", dialogListener);
            builder.setNegativeButton("NO", null);
            builder.setCancelable(false);
            alertDialog = builder.create();
            alertDialog.show();
        });

        // listButton
        binding.listButton.setOnClickListener(view -> {
            // 목록 다이얼로그 띄우기
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("알람 벨소리");
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);
            builder.setPositiveButton("확인", null);
            builder.setNegativeButton("취소", null);
            builder.setCancelable(false);
            listDialog = builder.create();
            listDialog.show();
        });

        // dateButton
        binding.dateButton.setOnClickListener(view -> {
            // 현재 날짜로 다이얼로그를 띄우기 위해 날짜를 구함
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // 두 번째 매개변수는 날짜 선택 시 발생하는 이벤트, 세 번째 매개변수는 다이얼로그에서 기본으로 보여야 할 날짜의 연, 월, 일
            DatePickerDialog dateDialog = new DatePickerDialog(this,
//                    new DatePickerDialog.OnDateSetListener() {
//                        @Override
//                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                            showToast(year + ":" + (month+1) + ":" + dayOfMonth);
//                        }
//                    }, year, month, day);
                    (view1, year1, month1, dayOfMonth) -> showToast(year1 + "년 " + (month1 + 1) + " 월 " + dayOfMonth + " 일"), year, month, day);

            dateDialog.show();
        });

        // timeButton
        binding.timeButton.setOnClickListener(view -> {
            // 현재 시간으로 다이얼로그를 띄우기 위해 시간을 구함
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            TimePickerDialog timeDialog = new TimePickerDialog(this,
//                    new TimePickerDialog.OnTimeSetListener() {
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                            showToast(hourOfDay + ":" + minute);
//                        }
//                    }, hour, minute, false);
                    (view12, hourOfDay, minute1) -> showToast(hourOfDay + " 시 " + minute1 + " 분"), hour, minute, false);

            timeDialog.show();
        });


        // customButton (디버깅창)
        binding.customButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // custom dialog를 위한 레이아웃 XML 초기화
            LayoutInflater inflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View dialogView = inflator.inflate(R.layout.dialog_layout, null);
            builder.setView(dialogView);
            builder.setPositiveButton("확인", dialogListener);
            builder.setNegativeButton("취소", null);
            customDialog = builder.create();
            customDialog.show();
        });
    }
}