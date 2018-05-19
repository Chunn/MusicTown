package com.rom.rm.musictown.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rom.rm.musictown.R;

import com.rom.rm.musictown.adapter.SongAdapter;
import com.rom.rm.musictown.dataModel.Song;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends AppCompatActivity {
    private ListView list_song;
    public static ArrayList<Song> songs=new ArrayList<Song>();
    public static ArrayList<Song> mySongs= new ArrayList<Song>();
    public static String INDEX="INDEX";
    public static int POSITION=1;
    private SongAdapter songAdapter;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);
        list_song= findViewById(R.id.album);
        Toolbar toolbar=findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        new GetSongOnline().execute("http://192.168.1.8:14249/api/songs");
        Log.d("song",songs.size()+"");

        songAdapter= new SongAdapter(this,R.layout.row,songs);
        list_song.setAdapter(songAdapter);
        list_song.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SearchFragment.this,PlaySongOnline.class);
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
    class GetSongOnline extends AsyncTask<String,Void,Integer> {

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer==400){
                Log.d("Test","Failed");
            }
            else if(integer==200)
            {
                Log.d("Test","Successful");
            }
            super.onPostExecute(integer);
        }
        @Override
        protected Integer doInBackground(String... paramas) {
            String urlString=paramas[0];
            URL url=null;
            HttpURLConnection httpURLConnection=null;
            InputStream inputStream=null;
            String result="";
            int c;
            try {
                url=new URL(urlString);
                httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");

                httpURLConnection.setDoInput(true);
                // đẩy dữ liệu lên server Do Output
                httpURLConnection.connect();
                Log.d("url",urlString);

                //cần luồng đọc dữ liệu đầu vào
                inputStream=httpURLConnection.getInputStream();
                //endoffile=-1
                while ((c=inputStream.read())!=-1){
                    result +=(char)c;

                }
                Log.d("test",result);

                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject;

                for (int i=0;i<jsonArray.length();i++){
                    Log.d("leng",jsonArray.length()+"");
                    jsonObject=jsonArray.getJSONObject(i);
                    songs.add(new Song(jsonObject.getInt("idSong"),jsonObject.getString("nameSong"),
                            jsonObject.getString("url")));
                    Log.d("ob",songs+"");
                }
                for(Song song:songs){
                    Log.d("song",song.getNameSong());
                }

            }catch (Exception ex) {
                ex.printStackTrace();

                return 400;
            }
            return 200;
        }
    }
    private List<Integer> listImg(){
        ArrayList<Integer> list=new ArrayList<>();
        list.add(R.drawable.alittlelove);
        list.add(R.drawable.forever);
        list.add(R.drawable.forever_friend);
        list.add(R.drawable.hello_goodbye);
        list.add(R.drawable.kiss);
        list.add(R.drawable.mua);
        list.add(R.drawable.ton_tai);
        list.add(R.drawable.con_mua_tuoi_thanh_xuan);
        list.add(R.drawable.alittlelove);
        list.add(R.drawable.forever);
        list.add(R.drawable.forever_friend);
        list.add(R.drawable.hello_goodbye);
        list.add(R.drawable.kiss);
        list.add(R.drawable.mua);
        list.add(R.drawable.ton_tai);
        list.add(R.drawable.con_mua_tuoi_thanh_xuan);
        list.add(R.drawable.alittlelove);
        list.add(R.drawable.forever);
        list.add(R.drawable.forever_friend);
        list.add(R.drawable.hello_goodbye);
        list.add(R.drawable.kiss);
        list.add(R.drawable.mua);
        list.add(R.drawable.ton_tai);
        list.add(R.drawable.con_mua_tuoi_thanh_xuan);
        return list;
    }

}
