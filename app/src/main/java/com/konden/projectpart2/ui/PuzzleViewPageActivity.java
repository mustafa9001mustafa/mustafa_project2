package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.konden.projectpart2.R;
import com.konden.projectpart2.adapters.AdapterViewPager;
import com.konden.projectpart2.databinding.ActivityPuzzleViewPageActivtyBinding;
import com.konden.projectpart2.fragments.fragment_answer.DialogFragmentAnswerFalse;
import com.konden.projectpart2.fragments.fragment_answer.DialogFragmentAnswerTrue;
import com.konden.projectpart2.interfases.ListenerCallAnswerFragmentChoose;
import com.konden.projectpart2.interfases.ListenerCallAnswerFragmentFile;
import com.konden.projectpart2.interfases.ListenerCallAnswerFragmentTrueOrFalse;
import com.konden.projectpart2.interfases.ListenerCallDialogOk;
import com.konden.projectpart2.interfases.ListenerCallOnFinchesTimer;
import com.konden.projectpart2.interfases.TimerListener;
import com.konden.projectpart2.myapplication.MyApp;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;

import java.util.ArrayList;
import java.util.List;

public class PuzzleViewPageActivity extends AppCompatActivity implements TimerListener, ListenerCallAnswerFragmentFile
        , ListenerCallAnswerFragmentTrueOrFalse, ListenerCallAnswerFragmentChoose, ListenerCallDialogOk
, ListenerCallOnFinchesTimer {
    ActivityPuzzleViewPageActivtyBinding binding;
    ArrayList<QuestionsEntity> questionsEntityArrayList = new ArrayList<>();
    ViewModelGame viewModel;
    int id;
    AdapterViewPager adapterViewPager;
    DialogFragmentAnswerFalse answerFalse;
    DialogFragmentAnswerTrue answerTrue;

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
                adapterViewPager = new AdapterViewPager(PuzzleViewPageActivity.this, questionsEntityArrayList);
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
    public void CallFile(boolean x, String s) {
        if (x == true) {
            answerTrue = DialogFragmentAnswerTrue.newInstance(s);
            answerTrue.show(getSupportFragmentManager(), "s1");
            int i = Sherdpreferanses.getInstance().getScore();
            Sherdpreferanses.getInstance().SetScore(i + 5);
            binding.viewPoint.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
        } else if (x == false) {
            Log.e("TAG", "CallFile: false");
            answerFalse = DialogFragmentAnswerFalse.newInstance(s);
            answerFalse.show(getSupportFragmentManager(), "s1");

        }
    }

    @Override
    public void CallTrueOrFalse(boolean x, String s) {
        if (x == true) {
            answerTrue = DialogFragmentAnswerTrue.newInstance(s);
            answerTrue.show(getSupportFragmentManager(), "s2");
            int i = Sherdpreferanses.getInstance().getScore();
            Sherdpreferanses.getInstance().SetScore(i + 3);
            binding.viewPoint.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
        } else if (x == false) {
            answerFalse = DialogFragmentAnswerFalse.newInstance(s);
            answerFalse.show(getSupportFragmentManager(), "s2");
        }
    }

    @Override
    public void CallChoose(boolean x, String s) {
        if (x == true) {
            answerTrue = DialogFragmentAnswerTrue.newInstance(s);
            answerTrue.show(getSupportFragmentManager(), "s3");
            int i = Sherdpreferanses.getInstance().getScore();
            Sherdpreferanses.getInstance().SetScore(i + 2);
            binding.viewPoint.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
        } else if (x == false) {
            answerFalse = DialogFragmentAnswerFalse.newInstance(s);
            answerFalse.show(getSupportFragmentManager(), "s3");
        }
    }

    @Override
    public void onFragment(boolean b) {
        if (b == true) {
            answerTrue.dismiss();
            moveViewPager();
        } else {
            answerFalse.dismiss();
            moveViewPager();
        }
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

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void OnFinchesTimer() {
        moveViewPager();
    }
}