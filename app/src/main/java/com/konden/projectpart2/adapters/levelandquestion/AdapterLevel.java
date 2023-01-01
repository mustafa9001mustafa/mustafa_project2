package com.konden.projectpart2.adapters.levelandquestion;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.ItemQBinding;
import com.konden.projectpart2.room.game.level.LevelEntity;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;

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
        LevelEntity level = list.get(position);
        int pos = position;
        holder.setData(list.get(position));
        setFadeAnimation(holder.item.itemTextUnlock);
        setScaleAnimation(holder.item.getRoot());

        holder.item.getRoot().setOnClickListener(view -> {
            if (level.isLevel_status()) {
                callLevel.callLevel(level.getLevel_no(), level.isLevel_status());
            }
        });

        if (level.getUnlock_points() <= Sherdpreferanses.getInstance().getUnlockNow()) {

            holder.item.cardView.setCardBackgroundColor(Color.GREEN);
            holder.item.itemLock.setAnimation(R.raw.unlocked);
            holder.item.itemTextUnlock.setText("");

            if (Sherdpreferanses.getInstance().getScore() >= Sherdpreferanses.getInstance().getUnlockNow())
                Sherdpreferanses.getInstance().SetUnlockNow(Sherdpreferanses.getInstance().getScore());

//            if (level.getUnlock_points() <= Sherdpreferanses.getInstance().getScore()) {
//                holder.item.cardView.setCardBackgroundColor(Color.GREEN);
//                holder.item.itemLock.setAnimation(R.raw.unlocked);
//                holder.item.itemTextUnlock.setText("");
//                holder.item.getRoot().setOnClickListener(view -> {
//                    //
//                });
        }
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
        void callLevel(int x, boolean level_status);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 0.2f);
        anim.setDuration(350);
        view.startAnimation(anim);
    }
}