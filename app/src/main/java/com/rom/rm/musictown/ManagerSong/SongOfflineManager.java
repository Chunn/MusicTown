package com.rom.rm.musictown.ManagerSong;

import android.media.MediaMetadataRetriever;
import android.os.Environment;

import com.rom.rm.musictown.R;
import com.rom.rm.musictown.dataModel.Song;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SongOfflineManager {
    final String MEDIA_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/media/music";
    private ArrayList<Song> songsOffline = new ArrayList();



    public SongOfflineManager() {
    }
    public ArrayList<Song> getSongsOffline() {
        File home = new File(this.MEDIA_PATH);
        MediaMetadataRetriever mediaMetadataRetriever= new MediaMetadataRetriever();
        if (home.listFiles(new FileExtensionFilter()).length>0) {
            File[] files;
            int count = (files = home.listFiles(new SongOfflineManager.FileExtensionFilter())).length;
            File file;
            String nameSong;
            String singerName;
            String album;
            String fileName;
            Song song;
            for(int i = 0; i < count; ++i) {
                file = files[i];
                fileName=file.getName();
                mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
                song = new Song();
                nameSong=mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                singerName=mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                album=mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                String p[]=fileName.split("_");
                if (singerName==null){
                    try {
                        singerName=p[1];
                    }catch (ArrayIndexOutOfBoundsException e){
                        singerName="unknown";
                    }
                }
                if (nameSong==null){
                    try{
                        nameSong=p[0];
                    }catch (ArrayIndexOutOfBoundsException e){
                        nameSong=fileName.substring(0,fileName.length()-4);
                    }
                }
                if (nameSong==null){
                    album="unknown";
                }
                song.setNameSong(nameSong);
                song.setNameSinger(singerName);
                song.setUrlSong(file.getAbsolutePath());
                song.setImageSong(listImg().get(i));
                song.setAlbum(album);
                this.songsOffline.add(song);
            }
        }
        //Hiển thị bài hát theo thứ tự ABC
        Collections.sort(this.songsOffline, new Comparator<Song>() {
            public int compare(Song a, Song b) {
                return a.getNameSong().compareTo(b.getNameSong());
            }
        });
        return this.songsOffline;
    }
    class FileExtensionFilter implements FilenameFilter {
        FileExtensionFilter() {
        }
        public boolean accept(File dir, String name) {
            return name.endsWith(".mp3") || name.endsWith(".MP3");
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
