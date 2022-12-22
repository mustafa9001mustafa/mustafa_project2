package com.konden.projectpart2.ui;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.konden.projectpart2.R;
import com.konden.projectpart2.adapters.AdapterLevel;
import com.konden.projectpart2.databinding.ActivityStartPlayingBinding;
import com.konden.projectpart2.model.User;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.LevelEntity;
import com.konden.projectpart2.room.game.QuestionsEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
        adapter = new AdapterLevel(list, StartPlaying.this);
        list = new ArrayList<>();

        adapter.setList(list);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(StartPlaying.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}