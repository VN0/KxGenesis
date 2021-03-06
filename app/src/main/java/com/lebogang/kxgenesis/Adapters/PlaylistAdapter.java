package com.lebogang.kxgenesis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lebogang.audiofilemanager.Models.Playlist;
import com.lebogang.kxgenesis.R;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.Holder>{
    private Context context;
    private OnClickInterface clickInterface;
    private OnClickOptionsInterface clickOptionsInterface;
    private List<Playlist> list = new ArrayList<>();

    public PlaylistAdapter(OnClickInterface clickInterface, OnClickOptionsInterface clickOptionsInterface) {
        this.clickInterface = clickInterface;
        this.clickOptionsInterface = clickOptionsInterface;
    }

    public void setList(List<Playlist> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_playlist_single_line, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Playlist playlist = list.get(position);
        holder.title.setText(playlist.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private TextView title;
        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
            itemView.setOnClickListener(v->{
                clickInterface.onClick(list.get(getAdapterPosition()));
            });
            itemView.findViewById(R.id.imageButton).setOnClickListener(v->{
                clickOptionsInterface.onOptionsClick(list.get(getAdapterPosition()));
            });
        }
    }
}
