package com.rom.rm.musictown.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rom.rm.musictown.Adapter.ListSongAdapter;
import com.rom.rm.musictown.R;
import com.rom.rm.musictown.ManagerSong.SongManager;
import com.rom.rm.musictown.dataModel.Song;

import java.util.ArrayList;

public class HomeFragment extends AppCompatActivity {
    private ListView list_song;
    private static ArrayList<Song> songs;
    public static ArrayList<Song> mySongs= new ArrayList<Song>();
    public static String INDEX="INDEX";
    public static int POSITION=1;
    private ListSongAdapter songAdapter;
    private SongManager songManager;

    public static void setSongs(ArrayList<Song> songs) {
        HomeFragment.songs = songs;
    }

    public static ArrayList<Song> getMySongs() {
        return mySongs;
    }

    public static void setMySongs(ArrayList<Song> mySongs) {
        HomeFragment.mySongs = mySongs;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        list_song= findViewById(R.id.album);
        Toolbar toolbar=findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        songs=new ArrayList<Song>();
        songManager= new SongManager();
        songs=songManager.getSongsOffline();
        songAdapter= new ListSongAdapter(this,R.layout.row_list_song,songs);
        list_song.setAdapter(songAdapter);
        list_song.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(HomeFragment.this,PlayMusic.class);
                intent.putExtra(INDEX,position);
                Log.d("INDEX",position+"");
                startActivity(intent);
            }
        });

        registerForContextMenu(list_song);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position= menuInfo.position;
        Song song=songs.get(position);

        switch (item.getItemId()){
            case R.id.context_add:
                mySongs.add(song);
                Toast.makeText(getApplicationContext(),"Đã lêm vào playlist!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.context_del:
                songs.remove(position);
                songAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Delete'"+song.getNameSong()+"' thành công",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

  /*  public static ArrayList<Song> getSongs() {
        songs.add(new Song("A Little Love","Fionna Fung", R.drawable.alittlelove,R.raw.alittle_love,"Nhạc của tui"));
        songs.add(new Song("Forever","Stratovarius", R.drawable.forever,R.raw.forever,"Nhạc của tui"));
        songs.add(new Song("Forever Friend","Fionna Fung", R.drawable.forever_friend,R.raw.forver_friend,"Nhạc của tui"));
        songs.add(new Song("Hello Goodbye","Đỗ Phú Quý", R.drawable.hello_goodbye,R.raw.hello_goodbye,"Nhạc của tui"));
        songs.add(new Song("Kiss the rain","Yiruma", R.drawable.kiss,R.raw.kiss_the_rain,"Nhạc của tui"));
        songs.add(new Song("Tồn tại không hoàn chỉnh","Úc Khải Duy",R.drawable.ton_tai,R.raw.ton_tai_khong_hoan_chinh,"Nhạc của tui"));
        songs.add(new Song("Mưa","Thùy Chi", R.drawable.mua,R.raw.mua,"Nhạc của tui"));
        return songs;
    }*/

}
