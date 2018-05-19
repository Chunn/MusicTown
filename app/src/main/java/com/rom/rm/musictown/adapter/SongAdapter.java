package com.rom.rm.musictown.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rom.rm.musictown.R;
import com.rom.rm.musictown.dataModel.Song;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {
    private Context context;
    private int resource;
    private List<Song> songs;

    public SongAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context=context;
        this.resource=resource;
        this.songs=songs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.row,null);
        }

        TextView tvName= convertView.findViewById(R.id.song_name);
        tvName.setText(songs.get(position).getNameSong());

        ImageView img=convertView.findViewById(R.id.img_song);
        img.setImageResource(songs.get(position).getImageSong());

        return convertView;
    }
}
