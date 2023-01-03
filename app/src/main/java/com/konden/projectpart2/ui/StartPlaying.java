package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.konden.projectpart2.R;
import com.konden.projectpart2.adapters.levelandquestion.AdapterLevel;
import com.konden.projectpart2.databinding.ActivityStartPlayingBinding;
import com.konden.projectpart2.fragments.dialog_fragment_is_firs.DialogFragmentIsFirst;
import com.konden.projectpart2.interfases.settings.ListenerIsFirst;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.level.LevelEntity;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;
import com.konden.projectpart2.sound.Sound;

import java.util.ArrayList;
import java.util.List;

public class StartPlaying extends AppCompatActivity implements ListenerIsFirst {
    private ActivityStartPlayingBinding binding;
    private AdapterLevel adapter;
    private ArrayList<LevelEntity> list;
    private ViewModelGame model;
    private DialogFragmentIsFirst fragmentIsFirst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartPlayingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ViewModelProvider(this).get(ViewModelGame.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ALL_METHOD();
        GET_LEVEL();
        SHOW_DIALOG_IS_FIRST();
    }

    private void SHOW_DIALOG_IS_FIRST() {
        if (Sherdpreferanses.getInstance().GetCheckBox() == true) {
            fragmentIsFirst = DialogFragmentIsFirst.newInstance("معلومات عن اللعبة");
            fragmentIsFirst.show(getSupportFragmentManager(), "is_first");
            fragmentIsFirst.setCancelable(false);
        }
    }

    private void GET_LEVEL() {
        model.getAllLevel().observe(this, new Observer<List<LevelEntity>>() {
            @Override
            public void onChanged(List<LevelEntity> levelEntities) {
                if (Sherdpreferanses.getInstance().getScore() >= Sherdpreferanses.getInstance().getUnlockNow())
                    Sherdpreferanses.getInstance().SetUnlockNow(Sherdpreferanses.getInstance().getScore());

                list = new ArrayList<>();
                list.addAll(levelEntities);
                for (int i = 0; i < list.size(); i++) {
                    if (levelEntities.get(i).getUnlock_points() <= Sherdpreferanses.getInstance().getUnlockNow()) {
                        levelEntities.get(i).setLevel_status(true);
                    }
                }
                adapter.setList(levelEntities);
                binding.recyclerView.setAdapter(adapter);
                binding.recyclerView.setHasFixedSize(true);
                binding.recyclerView.setLayoutManager(new GridLayoutManager(StartPlaying.this, 3, GridLayoutManager.VERTICAL, false));
            }
        });
    }

    private void ALL_METHOD() {
        SET_ADAPTER();
    }

    private void SET_ADAPTER() {
        adapter = new AdapterLevel(list, StartPlaying.this, new AdapterLevel.CallLevel() {
            @Override
            public void callLevel(int x, boolean level_status) {
                if (level_status || list.get(x).getUnlock_points() <= Sherdpreferanses.getInstance().getUnlockNow()) {
                    Intent intent = new Intent(StartPlaying.this, PuzzleViewPageActivity.class);
                    intent.putExtra("id_level", x);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    if (Sherdpreferanses.getInstance().GetSoundOther() == true)
                        new Sound().S3();
                }
//                if (level_status == true){
//                    Intent intent = new Intent(StartPlaying.this, PuzzleViewPageActivity.class);
//                    intent.putExtra("id_level", x);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                }
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(StartPlaying.this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void ok() {
        fragmentIsFirst.dismiss();
    }

    @Override
    public void not_now() {
        Sherdpreferanses.getInstance().SetCheckBox(false);
        fragmentIsFirst.dismiss();
    }
}