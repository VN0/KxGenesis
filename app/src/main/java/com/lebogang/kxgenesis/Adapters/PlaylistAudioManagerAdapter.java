package com.lebogang.kxgenesis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.lebogang.audiofilemanager.Models.Audio;
import com.lebogang.kxgenesis.R;
import com.lebogang.kxgenesis.databinding.ItemSong2Binding;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAudioManagerAdapter extends RecyclerView.Adapter<PlaylistAudioManagerAdapter.Holder>{
    private List<Audio> list = new ArrayList<>();
    private Context context;
    private List<Audio> checkedItems = new ArrayList<>();

    public PlaylistAudioManagerAdapter() {
    }

    public void setList(List<Audio> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Audio> getCheckedItems() {
        return checkedItems;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void removeItem(Audio audio){
        int x = list.indexOf(audio);
        list.remove(audio);
        notifyItemRemoved(x);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSong2Binding binding = ItemSong2Binding.inflate(inflater,parent, false);
        return new Holder(binding.getRoot(),binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Audio audioItem = list.get(position);
        holder.binding.titleTextView.setText(audioItem.getTitle());
        holder.binding.subtitleTextView.setText(audioItem.getArtistTitle() + " - " + audioItem.getAlbumTitle());
        Glide.with(context).load(audioItem.getAlbumArtUri())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.ic_music_light)
                .downsample(DownsampleStrategy.AT_MOST)
                .dontAnimate()
                .skipMemoryCache(true)
                .into(holder.binding.imageView)
                .clearOnDetach()
                .waitForLayout();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public ItemSong2Binding binding;

        public Holder(@NonNull View itemView, ItemSong2Binding binding) {
            super(itemView);
            this.binding = binding;
            binding.getRoot().setOnClickListener(v->{
                Audio audio = list.get(getAdapterPosition());
                binding.checkBox.setChecked(!binding.checkBox.isChecked());
                if (binding.checkBox.isChecked()){
                    if (!checkedItems.contains(audio))
                        checkedItems.add(audio);
                }
                if (!binding.checkBox.isChecked() && list.contains(audio)){
                    if (checkedItems.contains(audio))
                    checkedItems.remove(audio);
                }
            });
        }
    }
}
