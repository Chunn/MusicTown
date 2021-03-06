package com.rom.rm.musictown.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rom.rm.musictown.adapter.PlaylistAdapter;
import com.rom.rm.musictown.R;
import com.rom.rm.musictown.dataModel.PlayList;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private static ArrayList<PlayList>  playLists;
    private PlaylistAdapter playlistAdapter;
    private ListView listView;
    private TextView tvUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_account);
        init();
        Intent intent=getIntent();
        tvUser.setText(intent.getStringExtra(LoginActivity.USER));
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showDiaLogAddPlaylist();

            }
        });
        playlistAdapter=new PlaylistAdapter(UserActivity.this,R.layout.row_playlist,playLists);
        listView.setAdapter(playlistAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(UserActivity.this,HomeFragment.class);
                startActivity(intent);

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void init(){
        toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fab = findViewById(R.id.fab);
        playLists = new ArrayList<>();
        listView=findViewById(R.id.lvPlaylist);
        registerForContextMenu(listView);
        tvUser=findViewById(R.id.user);

    }
    private void showDiaLogAddPlaylist(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.dialog_add_playlist,null);
        final EditText text=dialogView.findViewById(R.id.addPlaylist);
        builder.setTitle("Tạo playlist");
        builder.setView(dialogView);
        builder.setCancelable(false);
        builder.setPositiveButton("Bỏ qua", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String namePlaylist=text.getText().toString();
                playLists.add(new PlayList(namePlaylist));
                Intent intent=new Intent(UserActivity.this,AddSongtoPlaylist.class);
                startActivity(intent);
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position=menuInfo.position;
        PlayList playList=playLists.get(position);

        switch (item.getItemId()){
            case R.id.context_edit:
                Log.d("X", showEditDiaLogAddPlaylist());
                playLists.get(position).setName( showEditDiaLogAddPlaylist());
                break;
            case R.id.context_add:
                Intent intent=new Intent(UserActivity.this,AddSongtoPlaylist.class);
                startActivity(intent);
                /*Toast.makeText(getApplicationContext(),"Đã lêm vào playlist!",Toast.LENGTH_SHORT).show();*/
                break;
            case R.id.context_del:
                playLists.remove(playList);
                Toast.makeText(getApplicationContext(),"Delete thành công",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private String showEditDiaLogAddPlaylist(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.dialog_add_playlist,null);
        final EditText text=dialogView.findViewById(R.id.addPlaylist);
        builder.setTitle("Sửa tên playlist");
        builder.setView(dialogView);
        builder.setCancelable(false);
        builder.setPositiveButton("Bỏ qua", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        String namePlaylist=text.getText().toString();
        return namePlaylist;
    }
}
