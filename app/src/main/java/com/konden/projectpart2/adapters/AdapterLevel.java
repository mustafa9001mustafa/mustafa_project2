package com.konden.projectpart2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.konden.projectpart2.databinding.ItemQBinding;
import com.konden.projectpart2.room.game.level.LevelEntity;

import java.util.List;

public class AdapterLevel extends RecyclerView.Adapter<AdapterLevel.LevelsHolder> {


    List<LevelEntity> list;
    Context context;
    CallLevel callLevel;

    public AdapterLevel(List<LevelEntity> list, Context context, CallLevel callLevel) {
        this.list = list;
        this.context = context;
        this.callLevel = callLevel;
    }

    public List<LevelEntity> getList() {
        return list;
    }

    public void setList(List<LevelEntity> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LevelsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQBinding binding = ItemQBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LevelsHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull LevelsHolder holder, int position) {
        int pos =position;
        holder.setData(list.get(position));
        holder.item.getRoot().setOnClickListener(view -> {
            callLevel.callLevel(list.get(pos).getLevel_no());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class LevelsHolder extends RecyclerView.ViewHolder {
        ItemQBinding item;

        public LevelsHolder(@NonNull View itemView) {
            super(itemView);
            item = ItemQBinding.bind(itemView);
        }

        public void setData(LevelEntity level) {
            item.itemTextLevel.setText(String.valueOf(level.getLevel_no()));
            item.itemTextUnlock.setText(String.valueOf(level.getUnlock_points()));
        }
    }

    public static interface CallLevel {
        void callLevel(int x);
    }
}