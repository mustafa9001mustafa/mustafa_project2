package com.konden.projectpart2.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.konden.projectpart2.R;
import com.konden.projectpart2.adapters.AdapterViewPager;
import com.konden.projectpart2.databinding.ActivityPuzzleViewPageActivtyBinding;
import com.konden.projectpart2.interfases.TimerListener;
import com.konden.projectpart2.myapplication.MyApp;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;

import java.util.ArrayList;
import java.util.List;

public class PuzzleViewPageActivity extends MyApp implements  TimerListener {
    ActivityPuzzleViewPageActivtyBinding binding;
    ArrayList<QuestionsEntity> questionsEntityArrayList = new ArrayList<>();
    ViewModelGame viewModel;
    int id;
    AdapterViewPager adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleViewPageActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModelGame.class);
        binding.viewPoint.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
        binding.viewQuestionNow.setText(String.valueOf(1));



        Intent intent = getIntent();
        id = intent.getIntExtra("id_level", 0);
        binding.viewLevelNow.setText(binding.viewLevelNow.getText().toString() + " " + String.valueOf(id));
        Log.d("sizeTest", "onCreate: " + id);

        viewModel.getAllQuestions(id).observe(this, new Observer<List<QuestionsEntity>>() {
            @Override
            public void onChanged(List<QuestionsEntity> questionsEntities) {
                questionsEntityArrayList.addAll(questionsEntities);
                adapterViewPager = new AdapterViewPager(PuzzleViewPageActivity.this,questionsEntityArrayList);


                binding.viewpagerFragment.setAdapter(adapterViewPager);
                //
                binding.viewQuestionAll.setText(String.valueOf(questionsEntities.size()));
            }
        });
        binding.viewpagerFragment.setUserInputEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PuzzleViewPageActivity.this, StartPlaying.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    private int getItem(int i) {
        return binding.viewpagerFragment.getCurrentItem() + i;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onTimeOut() {
        moveViewPager();
    }

    private void moveViewPager() {
        int currentItem = binding.viewpagerFragment.getCurrentItem();
        if (currentItem == adapterViewPager.getItemCount() - 1) {
            onBackPressed();
        } else {
            binding.viewpagerFragment.setCurrentItem(currentItem + 1, false);
        }
    }
}