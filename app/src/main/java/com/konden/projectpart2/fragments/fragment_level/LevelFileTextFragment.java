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

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.FragmentLevelFileTextBinding;
import com.konden.projectpart2.interfases.ListenerCallAnswerFragmentFile;
import com.konden.projectpart2.interfases.ListenerCallAnswerFragmentTrueOrFalse;
import com.konden.projectpart2.interfases.ListenerCallOnFinchesTimer;

import java.util.Locale;


public class LevelFileTextFragment extends Fragment {


    private static final String ARG_ID = "id1";
    private static final String ARG_TITLE = "title";
    private static final String ARG_TRUE_ANSWER = "true_answer";
    private static final String ARG_POINT = "point";
    private static final String ARG_DURATION = "duration";
    private static final String ARG_HINT = "hint";

    private int id;
    private String title;
    private String true_answer;
    private String hint;
    private int point;
    private int duration;

    CountDownTimer countDownTimer;
    ListenerCallAnswerFragmentFile listenerCallAnswerFragmentFile;
    private ListenerCallOnFinchesTimer listenerCallOnFinchesTimer;


    public LevelFileTextFragment() {
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listenerCallAnswerFragmentFile = (ListenerCallAnswerFragmentFile) context;
        listenerCallOnFinchesTimer = (ListenerCallOnFinchesTimer) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerCallAnswerFragmentFile = null;
    }

    public static LevelFileTextFragment newInstance(
            int id, String title, String true_answer, String hint, int point, int duration) {
        LevelFileTextFragment fragment = new LevelFileTextFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_TITLE, title);
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
            true_answer = getArguments().getString(ARG_TRUE_ANSWER);
            hint = getArguments().getString(ARG_HINT);
            duration = getArguments().getInt(ARG_DURATION);
            point = getArguments().getInt(ARG_POINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLevelFileTextBinding binding = FragmentLevelFileTextBinding.inflate(inflater, container, false);

        binding.itTextQuestionFile.setText(title);
        binding.itTextTimer.setText(String.valueOf(duration));

        binding.hintIconFile.setOnClickListener(view -> {
            Toast.makeText(getActivity(), hint, Toast.LENGTH_SHORT).show();
        });

        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                long hour = (l / 3600000);
                long min = (l / 60000);
                long sec = (l / 1000) % 60;
                final String x = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec);
                binding.itTextTimer.setText(x);
            }

            @Override
            public void onFinish() {
                listenerCallOnFinchesTimer.OnFinchesTimer();
            }
        }.start();

        binding.btnSaveDataNext.setOnClickListener(view -> {
            String s = binding.editAnswer.getEditText().getText().toString();
            if (!s.equals("")){
                Log.d("TAG", "onCreateView:   111 ");
                getTrueAnswer("s");
                Log.d("TAG", "onCreateView:   2222 ");

            }else {
                Toast.makeText(getActivity(), R.string.file_edit_text_fragment, Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    private void getTrueAnswer(String sab) {
        countDownTimer.cancel();

        if (true_answer.equals(sab))
            listenerCallAnswerFragmentFile.CallFile(true, hint);
        else
            listenerCallAnswerFragmentFile.CallFile(false, hint);


    }
}