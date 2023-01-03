package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.konden.projectpart2.R;
import com.konden.projectpart2.adapters.levelandquestion.AdapterViewPager;
import com.konden.projectpart2.databinding.ActivityPuzzleViewPageActivtyBinding;
import com.konden.projectpart2.fragments.fragment_answer.DialogFragmentAnswerFalse;
import com.konden.projectpart2.fragments.fragment_answer.DialogFragmentAnswerTrue;
import com.konden.projectpart2.fragments.fragment_end_time_out.DialogFragmentEnd;
import com.konden.projectpart2.fragments.fragment_end_time_out.DialogFragmentTimeOut;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerCallAnswerFragmentChoose;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerCallAnswerFragmentFile;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerCallAnswerFragmentTrueOrFalse;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerCallDialogOk;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerCallEnd;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerCallId;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerCallOnFinchesTimer;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerCallSkip;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerCallToast;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerTimeOut;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;
import com.konden.projectpart2.sound.Sound;

import java.util.ArrayList;
import java.util.List;

public class PuzzleViewPageActivity extends AppCompatActivity implements ListenerCallAnswerFragmentFile
        , ListenerCallAnswerFragmentTrueOrFalse, ListenerCallAnswerFragmentChoose, ListenerCallDialogOk
        , ListenerCallOnFinchesTimer, ListenerCallSkip, ListenerCallEnd, ListenerCallId, ListenerTimeOut, ListenerCallToast {
    ActivityPuzzleViewPageActivtyBinding binding;
    ArrayList<QuestionsEntity> questionsEntityArrayList = new ArrayList<>();
    ViewModelGame viewModel;
    int id, id_Questions;
    AdapterViewPager adapterViewPager;
    DialogFragmentAnswerFalse answerFalse;
    DialogFragmentAnswerTrue answerTrue;
    DialogFragmentTimeOut fragmentTimeOut;
    DialogFragmentEnd fragmentEnd;
    Sound sound = new Sound();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleViewPageActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModelGame.class);
        binding.viewPoint.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
        Intent intent = getIntent();
        id = intent.getIntExtra("id_level", 0);
        binding.viewLevelNow.setText(binding.viewLevelNow.getText().toString() + " " + String.valueOf(id));
        GetAllQuestion();

    }

    private void GetAllQuestion() {
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
    public void CallFile(boolean x, String s) {
        if (x == true) {
            answerTrue = DialogFragmentAnswerTrue.newInstance(s);
            answerTrue.show(getSupportFragmentManager(), "s1");
            answerTrue.setCancelable(false);
            Score(5);
            win();
        } else if (x == false) {
            Log.e("TAG", "CallFile: false");
            answerFalse = DialogFragmentAnswerFalse.newInstance(s);
            answerFalse.show(getSupportFragmentManager(), "s1");
            answerFalse.setCancelable(false);
            loser();
        }
    }

    @Override
    public void CallTrueOrFalse(boolean x, String s) {
        if (x == true) {
            answerTrue = DialogFragmentAnswerTrue.newInstance(s);
            answerTrue.show(getSupportFragmentManager(), "s2");
            answerTrue.setCancelable(false);
            Score(3);
            win();
        } else if (x == false) {
            answerFalse = DialogFragmentAnswerFalse.newInstance(s);
            answerFalse.show(getSupportFragmentManager(), "s2");
            answerFalse.setCancelable(false);
            loser();
        }
    }

    @Override
    public void CallChoose(boolean x, String s) {
        if (x == true) {
            answerTrue = DialogFragmentAnswerTrue.newInstance(s);
            answerTrue.show(getSupportFragmentManager(), "s3");
            answerTrue.setCancelable(false);
            Score(2);
            win();
        } else if (x == false) {
            answerFalse = DialogFragmentAnswerFalse.newInstance(s);
            answerFalse.show(getSupportFragmentManager(), "s3");
            answerFalse.setCancelable(false);
            loser();
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
    public void TrieOrFalseSkip() {
        SkipFragment();
    }

    @Override
    public void ChooseSkip() {
        SkipFragment();
    }

    @Override
    public void FileTextSkip() {
        SkipFragment();
    }

    private void SkipFragment() {
        int x = Sherdpreferanses.getInstance().getScore();
        if (x <= 4) {
            Snackbar.make(binding.getRoot(), R.string.toast_coin, Snackbar.LENGTH_SHORT).show();
            if (Sherdpreferanses.getInstance().GetSoundOther() == true)
                sound.S1();

        } else {
            moveViewPager();
            Score(-3);
            binding.viewPoint.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
            loser();
        }
    }

    private void moveViewPager() {

        int currentItem = binding.viewpagerFragment.getCurrentItem();
        if (currentItem == adapterViewPager.getItemCount() - 1) {
            fragmentEnd = DialogFragmentEnd.newInstance(getResources().getString(R.string.end_level));
            fragmentEnd.show(getSupportFragmentManager(), "s5");
            fragmentEnd.setCancelable(false);
            int finished = Sherdpreferanses.getInstance().getFinished();
            Sherdpreferanses.getInstance().SetFinished(finished + 1);
            if (Sherdpreferanses.getInstance().GetSoundOther() == true)
                sound.Win_end();
        } else {
            binding.viewpagerFragment.setCurrentItem(currentItem + 1, false);
        }
    }

    @Override
    public void OnFinchesTimer() { //
        fragmentTimeOut = DialogFragmentTimeOut.newInstance(getResources().getString(R.string.next));
        fragmentTimeOut.show(getSupportFragmentManager(), "s6");
        fragmentTimeOut.setCancelable(false);

    }

    @Override
    public void callEnd() {
        startActivity(new Intent(PuzzleViewPageActivity.this, StartPlaying.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    private void Score(int n) {
        int i = Sherdpreferanses.getInstance().getScore();
        Sherdpreferanses.getInstance().SetScore(i + n);
        binding.viewPoint.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
    }

    private void win() {
        int Win = Sherdpreferanses.getInstance().getWin();
        Sherdpreferanses.getInstance().SetWin(Win + 1);
        if (Sherdpreferanses.getInstance().GetSoundOther() == true)
            sound.S4();
    }

    private void loser() {

        int Loser = Sherdpreferanses.getInstance().getLoser();
        Sherdpreferanses.getInstance().SetLoser(Loser + 1);
        if (Sherdpreferanses.getInstance().GetSoundOther() == true)
            sound.S5();
    }

    @Override
    public void id(int i) {
        binding.viewQuestionNow.setText(String.valueOf(i));
        id_Questions = i;
        Sherdpreferanses.getInstance().Set_Id_Questions(i, id);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "لا يمكن الخروج قبل إنتهاء الأسئلة", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void time_out() {
        fragmentTimeOut.dismiss();
        moveViewPager();
    }

    @Override
    public void call_toast(String hint) {
        if (Sherdpreferanses.getInstance().getScore() > 3) {
            Toast.makeText(this, "" + hint, Toast.LENGTH_SHORT).show();
            Score(-2);


        } else
            Toast.makeText(this, R.string.hint_dount, Toast.LENGTH_SHORT).show();
    }
}