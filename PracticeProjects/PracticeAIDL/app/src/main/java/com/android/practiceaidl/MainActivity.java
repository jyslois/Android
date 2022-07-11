package com.android.practiceaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.android.playmusic.MusicService;
import com.android.practiceaidl.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MusicService pService;
    ActivityMainBinding binding;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // Stub 객체 획득, 서비스에서 받은 객체를 매개변수로 전달.
            pService = MusicService.Stub.asInterface(service);
            // play 이미지 버튼 활성화
            binding.play.setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 서비스와 연결이 끊어졌을 때.
            pService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 서비스 구동
        Intent intent = new Intent("com.android.playmusic.ACTION_PLAY");
        intent.setPackage("com.android.playmusic");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        // 바인드 서비스로 서비스를 제공해주는 앱을 AIDL방식으로 연동한다. 함수만 콜.
        binding.play.setOnClickListener(view -> {
            // 서비스가 구동되었으면 (연결되어 있다면)
            if (pService != null) {
                try {
                    pService.startMusic();
                    // play 버튼은 비활성화하고, stop 버튼은 활성화하고
                    binding.play.setEnabled(false);
                    binding.stop.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.stop.setOnClickListener(view -> {
            if (pService != null) {
                try {
                    pService.stopMusic();
                    binding.play.setEnabled(true);
                    binding.stop.setEnabled(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 액티비티가 종료되면, 서비스와 연결 끊기.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}