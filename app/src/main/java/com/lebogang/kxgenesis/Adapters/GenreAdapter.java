package com.lebogang.kxgenesis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lebogang.audiofilemanager.Models.Genre;
import com.lebogang.kxgenesis.R;

import java.util.ArrayList;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.Holder>{
    private OnClickInterface clickInterface;
    private List<Genre> list = new ArrayList<>();
    private Context context;
    private boolean isLayoutGrid = true;

    public GenreAdapter(OnClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    public GenreAdapter() {
    }

    public void setList(List<Genre> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void setLayoutGrid(boolean layoutGrid) {
        isLayoutGrid = layoutGrid;
    }

    @NonNull
    @Override
    public GenreAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (isLayoutGrid){
            View view = LayoutInflater.from(context).inflate(R.layout.item_genre_multiple_column,parent, false);
            return new Holder(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_genre_single_column,parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.Holder holder, int position) {
        Genre generalItem = list.get(position);
        holder.title.setText(generalItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private TextView title;

        public Holder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.titleTextView);
            itemView.setOnClickListener(v -> {
                clickInterface.onClick(list.get(getAdapterPosition()));
            });
        }
    }
}
