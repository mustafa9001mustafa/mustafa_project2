package com.konden.projectpart2.fragments.fragment_level;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.konden.projectpart2.databinding.FragmentLevelTrueOrFalseBinding;

import java.util.Locale;


public class LevelTrueOrFalseFragment extends Fragment {


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


    public LevelTrueOrFalseFragment() {
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        callSkip = (CallPuzzleTask) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static LevelTrueOrFalseFragment newInstance(
            int id, String title, String true_answer, String hint, int point, int duration
    ) {
        LevelTrueOrFalseFragment fragment = new LevelTrueOrFalseFragment();
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

        FragmentLevelTrueOrFalseBinding binding = FragmentLevelTrueOrFalseBinding.inflate(inflater, container, false);

        binding.itTextQuestionTrueOrFalse.setText(title);
        binding.itTextTimerTrue.setText(String.valueOf(duration));

        binding.trueIcon.setOnClickListener(view -> {
            if (true_answer.equals("true"))
                Toast.makeText(getActivity(), "true", Toast.LENGTH_SHORT).show();
        });

        binding.falseIcon.setOnClickListener(view -> {
            if (true_answer.equals("false"))
                Toast.makeText(getActivity(), "false", Toast.LENGTH_SHORT).show();
        });


        binding.hintIconTrue.setOnClickListener(view -> {
            Toast.makeText(getActivity(), hint, Toast.LENGTH_SHORT).show();
        });


        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                long hour = (l / 3600000);
                long min = (l / 60000);
                long sec = (l / 1000) % 60;
                final String Count = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec);
                binding.itTextTimerTrue.setText(Count);
            }

            @Override
            public void onFinish() {

            }
        }.start();

        return binding.getRoot();
    }
}
////////
// Todo : on a null object reference حل مشكلة إنه قيمة التايمير لما تنتقل من واجهة  لواجهة بيرجع قيمة
///////