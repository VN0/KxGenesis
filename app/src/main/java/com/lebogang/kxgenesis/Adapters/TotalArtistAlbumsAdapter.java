package com.lebogang.kxgenesis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lebogang.audiofilemanager.Models.Audio;
import com.lebogang.kxgenesis.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TotalArtistAlbumsAdapter extends RecyclerView.Adapter<TotalArtistAlbumsAdapter.Holder>{
    private LinkedHashMap<String, Audio> hashMap = new LinkedHashMap<>();
    private List<Audio> list = new ArrayList<>();
    private Context context;

    public void setList(List<Audio> list) {
        for (Audio audio: list){
            this.hashMap.put(audio.getAlbumTitle(), audio);
        }
        this.list = new ArrayList<>(hashMap.values());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_album_pager_item,parent
                , false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Audio audio = list.get(position);
        holder.title.setText(audio.getAlbumTitle());
        Glide.with(context).load(audio.getAlbumArtUri())
                .error(R.drawable.ic_music_record_light)
                .into(holder.imageView).waitForLayout();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private TextView title;
        private CircleImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}