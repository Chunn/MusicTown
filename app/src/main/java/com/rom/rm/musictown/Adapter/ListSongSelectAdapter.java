package com.rom.rm.musictown.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rom.rm.musictown.R;
import com.rom.rm.musictown.dataModel.Song;

import java.util.ArrayList;
import java.util.List;

public class ListSongSelectAdapter extends ArrayAdapter<Song> {
    private Context context;
    private int resource;
    private List<Song> songs;
    private Boolean[] mItemChecked=new Boolean[100];


    public ListSongSelectAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.resource = resource;
        this.songs = songs;
        for (int i = 0; i < 100; i++) {
            mItemChecked[i] = false;
        }
    }
    public boolean itemIsChecked(int position) {
        return mItemChecked[position];
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_select_playlist, null);
        }

        TextView tvName = convertView.findViewById(R.id.song_name);
        tvName.setText(songs.get(position).getNameSong());
        TextView tvSinger = convertView.findViewById(R.id.singer);
        tvSinger.setText(songs.get(position).getNameSinger());
        ImageView img = convertView.findViewById(R.id.img_song);
        img.setImageResource(songs.get(position).getImageSong());
        TextView tvAlbum = convertView.findViewById(R.id.album);
        tvAlbum.setText(songs.get(position).getAlbum());
        CheckBox checkBox=convertView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(mItemChecked[position]);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mItemChecked[position]=isChecked;
            }
        });
        return convertView;
    }
}

