package com.konden.projectpart2.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.konden.projectpart2.constant.FinalContract;
import com.konden.projectpart2.fragments.fragment_level.LevelChooseFragment;
import com.konden.projectpart2.fragments.fragment_level.LevelFileTextFragment;
import com.konden.projectpart2.fragments.fragment_level.LevelTrueOrFalseFragment;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;
import java.util.ArrayList;

public class AdapterViewPager extends FragmentStateAdapter {
    ArrayList<QuestionsEntity> questionsEntityArrayList;


    public AdapterViewPager(@NonNull FragmentActivity fragmentActivity, ArrayList<QuestionsEntity> questionsEntityArrayList) {
        super(fragmentActivity);
        this.questionsEntityArrayList = questionsEntityArrayList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        QuestionsEntity questionsEntity = questionsEntityArrayList.get(position);
        int id_Question = questionsEntity.getId_Question();
        String title =  questionsEntity.getTitle();
        String answer_1 = questionsEntity.getAnswer_1();
        String answer_2 = questionsEntity.getAnswer_2();
        String answer_3 = questionsEntity.getAnswer_3();
        String answer_4 = questionsEntity.getAnswer_4();
        String true_answer= questionsEntity.getTrue_answer();
        int points = questionsEntity.getPoints();
        int duration = questionsEntity.getDuration();
        String hint = questionsEntity.getHint() ;
        int pattern_id = questionsEntity.getPattern_idChild();

        // //todo set a member var for each fragment class
        if (pattern_id == FinalContract.pattern_True_Or_False) {
            return LevelTrueOrFalseFragment.newInstance(id_Question,title,true_answer,hint,points,duration);
        } else if (questionsEntity.getPattern_idChild() == FinalContract.pattern_Choose) {
            return LevelChooseFragment.newInstance(id_Question,title ,answer_1, answer_2 ,answer_3 ,answer_4,true_answer,hint,points,duration);
        }else if (questionsEntity.getPattern_idChild() == FinalContract.pattern_File_Text) {
            return LevelFileTextFragment.newInstance(id_Question,title,true_answer,hint,points,duration);
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return questionsEntityArrayList.size();
    }

    public void setQuestionsEntityArrayList(ArrayList<QuestionsEntity> questionsEntityArrayList) {
        this.questionsEntityArrayList = questionsEntityArrayList;
    }
}
