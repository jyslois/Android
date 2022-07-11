package com.android.playmusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

public class PlayMusicService extends Service {
    MediaPlayer player;

    // 이 서비스가 종료될 때
    @Override
    public void onDestroy() {
        player.release();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // aidl 파일을 구현한 객체를 만들고,
        // 이 객체로 외부 프로세스에서 통신할 Stub를 만들어서 반환
        return new MusicService.Stub() {
            @Override
            public void startMusic() throws RemoteException {
                player = MediaPlayer.create(PlayMusicService.this, R.raw.asyoufadeaway);
                try {
                    player.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void stopMusic() throws RemoteException {
                // 음악이 플레이어 되고 있는 도중에만
                if (player.isPlaying()) {
                    player.stop();
                }
            }
        };
    }
}