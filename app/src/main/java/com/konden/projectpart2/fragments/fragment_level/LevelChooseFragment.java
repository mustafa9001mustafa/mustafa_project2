package com.konden.projectpart2.fragments.fragment_level;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.konden.projectpart2.databinding.FragmentLevelChooseBinding;
import com.konden.projectpart2.interfases.ListenerCallAnswerFragmentChoose;
import com.konden.projectpart2.interfases.ListenerCallId;
import com.konden.projectpart2.interfases.ListenerCallOnFinchesTimer;
import com.konden.projectpart2.interfases.ListenerCallSkip;
import com.konden.projectpart2.interfases.TimerListener;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;

import java.util.Locale;


public class LevelChooseFragment extends Fragment {

    private static final String ARG_ID = "id1";
    private static final String ARG_TITLE = "title";
    private static final String ARG_ANSWER1 = "answer_1";
    private static final String ARG_ANSWER2 = "answer_2";
    private static final String ARG_ANSWER3 = "answer_3";
    private static final String ARG_ANSWER4 = "answer_4";
    private static final String ARG_TRUE_ANSWER = "true_answer";
    private static final String ARG_POINT = "point";
    private static final String ARG_DURATION = "duration";
    private static final String ARG_HINT = "hint";
    private CountDownTimer countDownTimer;
    private TimerListener timerListener;
    private ListenerCallAnswerFragmentChoose listenerCallAnswerFragmentChoose;
    private ListenerCallOnFinchesTimer listenerCallOnFinchesTimer;
    private ListenerCallSkip callSkip;
    private ListenerCallId listenerCallId;

    private int id;
    private String title;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private String true_answer;
    private String hint;
    private int point;
    private int duration;

    public LevelChooseFragment() {

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        timerListener = (TimerListener) context;
        listenerCallAnswerFragmentChoose = (ListenerCallAnswerFragmentChoose) context;
        listenerCallOnFinchesTimer = (ListenerCallOnFinchesTimer) context;
        callSkip = (ListenerCallSkip) context;
        listenerCallId = (ListenerCallId) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerCallAnswerFragmentChoose = null;
        timerListener = null;
        listenerCallOnFinchesTimer = null;
        callSkip = null;
        listenerCallId = null;
    }

    public static LevelChooseFragment newInstance(
            int id, String title, String answer_1, String answer_2, String answer_3, String answer_4
            , String true_answer, String hint, int point, int duration
    ) {
        LevelChooseFragment fragment = new LevelChooseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_ANSWER1, answer_1);
        args.putString(ARG_ANSWER2, answer_2);
        args.putString(ARG_ANSWER3, answer_3);
        args.putString(ARG_ANSWER4, answer_4);
        args.putString(ARG_TRUE_ANSWER, true_answer);
        args.putString(ARG_HINT, hint);
        args.putInt(ARG_POINT, point);
        args.putInt(ARG_DURATION, duration);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
            title = getArguments().getString(ARG_TITLE);
            answer_1 = getArguments().getString(ARG_ANSWER1);
            answer_2 = getArguments().getString(ARG_ANSWER2);
            answer_3 = getArguments().getString(ARG_ANSWER3);
            answer_4 = getArguments().getString(ARG_ANSWER4);
            true_answer = getArguments().getString(ARG_TRUE_ANSWER);
            hint = getArguments().getString(ARG_HINT);
            duration = getArguments().getInt(ARG_DURATION);
            point = getArguments().getInt(ARG_POINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLevelChooseBinding binding = FragmentLevelChooseBinding.inflate(inflater, container, false);

        Log.e("TAG", "onCreateView: "+id );

        listenerCallId.id(id);
        binding.cardSkip.setOnClickListener(view -> {
            callSkip.ChooseSkip();
            if (Sherdpreferanses.getInstance().getScore() > 2)
                countDownTimer.cancel();
        });

        binding.hintIconChoose.setOnClickListener(view -> {
            Toast.makeText(getActivity(), hint, Toast.LENGTH_SHORT).show();
        });
        binding.itChoose1.setOnClickListener(view -> {
            String s = binding.itChoose1.getText().toString();
            getTrueAnswer(s);
        });

        binding.itChoose2.setOnClickListener(view -> {
            String s = binding.itChoose2.getText().toString();
            getTrueAnswer(s);
        });

        binding.itChoose3.setOnClickListener(view -> {
            String s = binding.itChoose3.getText().toString();
            getTrueAnswer(s);
        });

        binding.itChoose4.setOnClickListener(view -> {
            String s = binding.itChoose4.getText().toString();
            getTrueAnswer(s);
        });

        binding.itTextQuestionChoose.setText(title);
        binding.itChoose1.setText(answer_1);
        binding.itChoose2.setText(answer_2);
        binding.itChoose3.setText(answer_3);
        binding.itChoose4.setText(answer_4);
        binding.itTextTimerChoose.setText(String.valueOf(duration));
        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                long hour = (l / 3600000);
                long min = (l / 60000);
                long sec = (l / 1000) % 60;
                final String x = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec);
                binding.itTextTimerChoose.setText(x);
            }

            @Override
            public void onFinish() {
                if (listenerCallAnswerFragmentChoose != null)
                listenerCallOnFinchesTimer.OnFinchesTimer();
            }
        }.start();

        return binding.getRoot();
    }

    private void getTrueAnswer(String sab) {
        countDownTimer.cancel();
        if (true_answer.equals(sab))
            listenerCallAnswerFragmentChoose.CallChoose(true, hint);
        else {
            listenerCallAnswerFragmentChoose.CallChoose(false, hint);
        }
    }
}