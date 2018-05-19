package com.rom.rm.musictown.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.rom.rm.musictown.ManagerSong.SongOfflineManager;
import com.rom.rm.musictown.activity.HomeFragment;
import com.rom.rm.musictown.activity.PlayMusic;
import com.rom.rm.musictown.dataModel.Song;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service  implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener{
    public static final int NOTIFICATION_ID = 1;
    // media player
    private MediaPlayer player;
    // song list
    private ArrayList<Song> songs;
    // current position
    private int positionCurrent;

    private String songName;
    private int img;

    public void onCreate(){
        super.onCreate();
        positionCurrent=0;
        SongOfflineManager songOfflineManager = new SongOfflineManager();
        songs= songOfflineManager.getSongsOffline();
        initMediaPlayer();

        IntentFilter filter = new IntentFilter();
        filter.addAction(PlayMusic.ACTION_PLAY);
        mReceiver = new MyReceiver();
        registerReceiver(mReceiver, filter);

    }

    private void initMediaPlayer(){
        player=new MediaPlayer();
        songName=songs.get(positionCurrent).getNameSong();
        img=songs.get(positionCurrent).getImageSong();

        player = new MediaPlayer();
        try {
            player.setDataSource(songs.get(positionCurrent).getUrlSong());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void playSong() {
        Song playSong = songs.get(positionCurrent);
        try {
            player.reset();
            initMediaPlayer();
            createNotification(playSong.getNameSong(),playSong.getImageSong());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void createNotification(String nameSong, int imageSong) {
        //xd notification
        final Notification.Builder builder = new Notification.Builder(this);
        //Quản lý notification bằng id
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder.setSmallIcon(imageSong);
        builder.setContentTitle(songName);
        builder.setTicker("play " + songName);
        //nhảy vào ứng dụng khi ng dùng click vào
        final Intent notificationIntent = new Intent(this, PlayMusic.class);
        final PendingIntent pi = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        builder.setContentIntent(pi);
        //Tạo 1 đối tượng notification
        final Notification notification = builder.build();
        startForeground(NOTIFICATION_ID, notification);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        player.release();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //start play back
        mp.start();

    }
    public void setPositionCurrent(int positionCurrent) {
        this.positionCurrent = positionCurrent;
    }

    BroadcastReceiver mReceiver;

    // use this as an inner class like here or as a top-level class
    public class MyReceiver extends BroadcastReceiver {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(PlayMusic.ACTION_PLAY)) {
                setPositionCurrent(intent.getIntExtra(HomeFragment.INDEX, HomeFragment.POSITION));
                playSong();

            }
        }
    }


}
