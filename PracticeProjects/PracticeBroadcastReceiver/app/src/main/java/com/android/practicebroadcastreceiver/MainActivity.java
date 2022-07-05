package com.android.practicebroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import com.android.practicebroadcastreceiver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 버튼을 클릭하면 인텐트를 발생시켜 브로드케스트 리시버를 실행함
        binding.lab1Button.setOnClickListener(view -> {
            // 브로드캐스트 리시버를 발생시킬 인텐트 준비
            Intent intent = new Intent(this, MyReceiver.class);
            // 브로드캐스트 리시버 인텐트를 발생시키는 함수 sendBroadcast
            sendBroadcast(intent);
        });

        // 배터리 상태 파악
        // registerReceiver() 함수로 시스템의 배터리 상태의 정보값을 얻기 위한 구문
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);

        // 스마트폰에 전원이 공급되고 있는지 파악하기 위한 구문. isCharging 값이 true이면 전원이 공급되고 있는 상황.
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;

        // 만약 공급되고 있다면,
        if (isCharging) {
            // 전원이 공급되고 있을 때 USB를 이용하는 건지, AC를 이용하는 건지
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            // 만약 usb로 charge되고 있으면 (usbCharge가 true이면)
            if (usbCharge) {
                binding.lab1PluggedView.setText("USB Charging");
            // 만약 ac로 charge되고 있으면 (acCharge가 true 이면)
            } else if (acCharge) {
                binding.lab1PluggedView.setText("AC Charging");
            }
        // 전원이 공급되고 있지 않다면
        } else {
            binding.lab1PluggedView.setText("unPlugged");
        }

        // 배터리가 몇 퍼센트 충전된 상황인지 파악하는 구문
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = (level / (float) scale) * 100;
        binding.lab1LevelView.setText("현재 배터리 : " + batteryPct + "%");
    }
}