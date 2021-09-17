package com.example.boundedservices;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer =MediaPlayer.create(this , R.raw.shapeofyou);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }
    public void play(){
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }
    public void Pause(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    public void stop(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    public class ServiceBinder extends Binder{
        public MusicService getMusicService(){
            return MusicService.this;      // return the service object
        }
    }
}