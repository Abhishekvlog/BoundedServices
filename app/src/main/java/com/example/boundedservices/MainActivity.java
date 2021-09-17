package com.example.boundedservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnStart;
    private Button mBtnPause;
    private Button mBtnStop;
    private MusicService musicService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.ServiceBinder serviceBinder = (MusicService.ServiceBinder) iBinder;
            musicService = serviceBinder.getMusicService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnPause =findViewById(R.id.btnPause);
        mBtnStop =findViewById(R.id.btnStop);
        mBtnStart =findViewById(R.id.btnPlay);
        startService();
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicService != null){
                    musicService.play();
                }
            }
        });
        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicService != null){
                    musicService.Pause();
                }
            }
        });
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicService != null){
                    musicService.stop();
                }
            }
        });
    }
    private void startService(){
        Intent intent  = new Intent(MainActivity.this , MusicService.class);
        bindService(intent, serviceConnection,BIND_AUTO_CREATE);
    }
}