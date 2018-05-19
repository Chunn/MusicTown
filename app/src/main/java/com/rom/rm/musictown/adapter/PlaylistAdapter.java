package com.rom.rm.musictown.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rom.rm.musictown.R;
import com.rom.rm.musictown.dataModel.PlayList;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<PlayList> {

    private Context context;
    private int resource;
    private List<PlayList> playLists;

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PlayList> playLists) {
        super(context, resource, playLists);
        this.context=context;
        this.resource=resource;
        this.playLists=playLists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.row_playlist,null);
        }

        TextView tvName= convertView.findViewById(R.id.playlist_name);
        tvName.setText(playLists.get(position).getName());
       /* TextView tvQty=convertView.findViewById(R.id.qty);
        tvQty.setText(HomeFragment.getMySongs().size());*/
        return convertView;
    }
}
