package com.rom.rm.musictown.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.rom.rm.musictown.adapter.ListSongSelectAdapter;
import com.rom.rm.musictown.ManagerSong.SongOfflineManager;
import com.rom.rm.musictown.R;
import com.rom.rm.musictown.dataModel.Song;

import java.util.ArrayList;


public class AddSongtoPlaylist extends AppCompatActivity{
    private Toolbar toolbar;
    private ListSongSelectAdapter listSongAdapter;
    private ListView listView;
    private SongOfflineManager songOfflineManager;
    private ArrayList<Song> songs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_song_to_playlist);
        init();
        songs= songOfflineManager.getSongsOffline();
        listSongAdapter=new ListSongSelectAdapter(AddSongtoPlaylist.this,R.layout.row_playlist, songs);
        listView.setAdapter(listSongAdapter);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(AddSongtoPlaylist.this,"OK",Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void init(){
        toolbar=findViewById(R.id.toolbar_addSong);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView=findViewById(R.id.lv_song_add);
        songOfflineManager =new SongOfflineManager();
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_add, menu);
        return true;
    }


}

