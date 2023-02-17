package com.konden.projectpart2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
    AdapterViewPager adapterViewPager;
    DialogFragmentAnswerFalse answerFalse;
    DialogFragmentAnswerTrue answerTrue;
    DialogFragmentTimeOut fragmentTimeOut;
    DialogFragmentEnd fragmentEnd;
    private double userCollected = 0, allQuestionPoints = 0;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleViewPageActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModelGame.class);
        binding.viewPoint.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
        Intent intent = getIntent();
        id = intent.getIntExtra("id_level", 0);
        String text_level = binding.viewLevelNow.getText().toString() + " " + id;
        binding.viewLevelNow.setText(text_level);
        binding.viewLevelNow.bringToFront();
        binding.conLevel.bringToFront();
        binding.conCoin.bringToFront();

        GetAllQuestion();
        BACK_GROUND_ACTIVITY();
    }

    private void BACK_GROUND_ACTIVITY() {
        if (Sherdpreferanses.getInstance().GetGameImageNumber() == 1)
            binding.getRoot().setBackgroundResource(R.drawable.game_q);
        else if (Sherdpreferanses.getInstance().GetGameImageNumber() == 2)
            binding.getRoot().setBackgroundResource(R.drawable.back_geme_2);
        else if (Sherdpreferanses.getInstance().GetGameImageNumber() == 3)
            binding.getRoot().setBackgroundResource(R.drawable.back_game_3);
    }

    private void GetAllQuestion() {
        viewModel.getAllQuestions(id).observe(this, new Observer<List<QuestionsEntity>>() {
            @Override
            public void onChanged(List<QuestionsEntity> questionsEntities) {
                questionsEntityArrayList.addAll(questionsEntities);
                adapterViewPager = new AdapterViewPager(PuzzleViewPageActivity.this, questionsEntityArrayList);
                binding.viewpagerFragment.setAdapter(adapterViewPager);
                binding.viewQuestionAll.setText(String.valueOf(questionsEntities.size()));
                allQuestionPoints += questionsEntities.size();
            }
        });
        binding.viewpagerFragment.setUserInputEnabled(false);
    }

    @Override
    public void CallFile(boolean x, String s) {
        if (x) {
            ShowAllFragment_true(s, "s1", 5);

        } else if (!x) {
            ShowAllFragment_false(s, "s1");
        }
    }

    @Override
    public void CallTrueOrFalse(boolean x, String s) {
        if (x) {
            ShowAllFragment_true(s, "s2", 3);

        } else if (!x) {
            ShowAllFragment_false(s, "s2");
        }
    }

    @Override
    public void CallChoose(boolean x, String s) {
        if (x) {
            ShowAllFragment_true(s, "s3", 2);
        } else if (!x) {
            ShowAllFragment_false(s, "s3");
        }
    }

    private void ShowAllFragment_true(String s, String tag, int score) {
        answerTrue = DialogFragmentAnswerTrue.newInstance(s);
        answerTrue.show(getSupportFragmentManager(), tag);
        answerTrue.setCancelable(false);
        Score(score);
        win();
    }

    private void ShowAllFragment_false(String s, String tag) {
        answerFalse = DialogFragmentAnswerFalse.newInstance(s);
        answerFalse.show(getSupportFragmentManager(), tag);
        answerFalse.setCancelable(false);
        loser();
    }

    @Override
    public void onFragment(boolean b) {
        if (b) {
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
            if (Sherdpreferanses.getInstance().GetSoundOther())
                Sound.getInstance().S1();
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
            updateLevelEvolution();
            int finished = Sherdpreferanses.getInstance().getFinished();
            Sherdpreferanses.getInstance().SetFinished(finished + 1);
            if (Sherdpreferanses.getInstance().GetSoundOther())
                Sound.getInstance().Win_end();

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void callEnd() {
        Sound.getInstance().sound_stop();
        startActivity(new Intent(PuzzleViewPageActivity.this, StartPlaying.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////


    private void Score(int n) {
        int i = Sherdpreferanses.getInstance().getScore();
        Sherdpreferanses.getInstance().SetScore(i + n);
        binding.viewPoint.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
    }

    private void win() {
        userCollected += 1.0;
        int Win = Sherdpreferanses.getInstance().getWin();
        Sherdpreferanses.getInstance().SetWin(Win + 1);
        if (Sherdpreferanses.getInstance().GetSoundOther())
            Sound.getInstance().S4();
    }

    private void loser() {
        int Loser = Sherdpreferanses.getInstance().getLoser();
        Sherdpreferanses.getInstance().SetLoser(Loser + 1);
        if (Sherdpreferanses.getInstance().GetSoundOther())
            Sound.getInstance().S5();
        Score(-1);
    }

    @Override
    public void id(int i) {
        binding.viewQuestionNow.setText(String.valueOf(i));
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

    private boolean updateLevelEvolution() {
        double result = userCollected / allQuestionPoints;
        viewModel.updateLevel_evolution(result, id);
        Log.e("TAG", "result: " + result);

        return true;
    }

    @Override
    public void time_out() {
        fragmentTimeOut.dismiss();
        Score(-1);
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