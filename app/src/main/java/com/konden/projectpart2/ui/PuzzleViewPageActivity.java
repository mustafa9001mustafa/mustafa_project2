package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.konden.projectpart2.R;
import com.konden.projectpart2.adapters.AdapterViewPager;
import com.konden.projectpart2.constant.FinalContract;
import com.konden.projectpart2.databinding.ActivityPuzzleViewPageActivtyBinding;
import com.konden.projectpart2.fragments.fragment_level.LevelChooseFragment;
import com.konden.projectpart2.fragments.fragment_level.LevelFileTextFragment;
import com.konden.projectpart2.fragments.fragment_level.LevelTrueOrFalseFragment;
import com.konden.projectpart2.interfases.CallSkip;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;

import java.util.ArrayList;
import java.util.List;

public class PuzzleViewPageActivity extends AppCompatActivity implements CallSkip {
    ActivityPuzzleViewPageActivtyBinding binding;
    ArrayList<Fragment> list = new ArrayList<>();
    ViewModelGame viewModel;
        QuestionsEntity questions;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleViewPageActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModelGame.class);
        questions = new QuestionsEntity();


        Intent intent = getIntent();
         x = intent.getIntExtra("id_level", 0);
        binding.viewLevelNow.setText(binding.viewLevelNow.getText().toString() + " " + String.valueOf(x));


        viewModel.getAllQuestions(x).observe(this, new Observer<List<QuestionsEntity>>() {
            @Override
            public void onChanged(List<QuestionsEntity> questionsEntities) {
                questions = questionsEntities.get(x);
//                x = questions.getId_Question();


                Log.e("TAG", "onChanged: "+questionsEntities.size() );
                Log.e("TAG", "onChanged:1111111111111111111111111111111111 " );
                for (int i = x; i < questionsEntities.size(); i++) {
                    if (questionsEntities.get(x).getPattern_idChild() == FinalContract.FinalConstant.pattern_True_Or_False) {
                        list.add(LevelTrueOrFalseFragment.newInstance(questionsEntities.get(x).getTitle(), questionsEntities.get(x).getDuration()));

                    } else if ((questionsEntities.get(x).getPattern_idChild() == FinalContract.FinalConstant.pattern_Choose)) {
                        list.add(LevelChooseFragment.newInstance(questionsEntities.get(x).getTitle(),
                                questionsEntities.get(x).getAnswer_1(), questionsEntities.get(x).getAnswer_2(),questionsEntities.get(x).getAnswer_3()
                                , questionsEntities.get(x).getAnswer_4(),  questionsEntities.get(x).getDuration()));

                    } else if ((questionsEntities.get(x).getPattern_idChild() == FinalContract.FinalConstant.pattern_File_Text)) {
                        list.add(LevelFileTextFragment.newInstance(questionsEntities.get(x).getTitle(), questionsEntities.get(x).getDuration()));
                    }


                }
                Log.e("TAG", "onChanged:22222222222222222222222222222222222222 " );

            }
        });


        AdapterViewPager adapterViewPager = new AdapterViewPager(this, list);
        binding.viewpagerFragment.setAdapter(adapterViewPager);
        binding.viewpagerFragment.setUserInputEnabled(false);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PuzzleViewPageActivity.this, StartPlaying.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void call_true(boolean x) {
        if (x == true) {
            binding.viewpagerFragment.setCurrentItem(getItem(+1), true);
            x = false;

        }
    }

    @Override
    public void call_file(boolean x) {
        if (x == true) {
            binding.viewpagerFragment.setCurrentItem(getItem(+1), true);
            x = false;

        }
    }

    @Override
    public void call_choose(boolean x) {
        if (x == false) {
            binding.viewpagerFragment.setCurrentItem(getItem(+1), true);
            x = true;
        }
    }

    private int getItem(int i) {
        return binding.viewpagerFragment.getCurrentItem() + i;
    }
}