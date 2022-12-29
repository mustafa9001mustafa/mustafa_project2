package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import com.konden.projectpart2.myapplication.MyApp;
import com.konden.projectpart2.R;
import com.konden.projectpart2.adapters.AdapterLevel;
import com.konden.projectpart2.databinding.ActivityStartPlayingBinding;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.level.LevelEntity;
import java.util.ArrayList;
import java.util.List;

public class StartPlaying extends AppCompatActivity {
    private ActivityStartPlayingBinding binding;
    AdapterLevel adapter;
    private ArrayList<LevelEntity> list;
    private ViewModelGame model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartPlayingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ViewModelProvider(this).get(ViewModelGame.class);

        model.getAllLevel().observe(this, new Observer<List<LevelEntity>>() {
            @Override
            public void onChanged(List<LevelEntity> levelEntities) {


                list = new ArrayList<>();
                adapter.setList(levelEntities);
                binding.recyclerView.setAdapter(adapter);
                binding.recyclerView.setHasFixedSize(true);
                binding.recyclerView.setLayoutManager(new GridLayoutManager(StartPlaying.this, 3, GridLayoutManager.VERTICAL, false));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        ALL_METHOD();
    }

    private void ALL_METHOD() {
        SET_ADAPTER();
    }

    private void SET_ADAPTER() {
        adapter = new AdapterLevel(list, StartPlaying.this, new AdapterLevel.CallLevel() {
            @Override
            public void callLevel(int x, boolean level_status) {
                if (level_status == true){
                    Intent intent = new Intent(StartPlaying.this, PuzzleViewPageActivity.class);
                    intent.putExtra("id_level", x);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
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
}