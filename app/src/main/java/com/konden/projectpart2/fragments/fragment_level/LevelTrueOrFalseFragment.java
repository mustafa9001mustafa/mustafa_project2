package com.konden.projectpart2.fragments.fragment_level;

import static com.konden.projectpart2.fragments.fragment_setting.DialogFragmentBlank.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.FragmentLevelFileTextBinding;
import com.konden.projectpart2.databinding.FragmentLevelTrueOrFalseBinding;
import com.konden.projectpart2.interfases.CallSkip;

import java.util.Locale;


public class LevelTrueOrFalseFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    CountDownTimer countDownTimer;
    private String mParam1;
    private int mParam2;

    private CallSkip callSkip;


    public LevelTrueOrFalseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callSkip = (CallSkip) context;
    }

    public static LevelTrueOrFalseFragment newInstance(String param1, int param2) {
        LevelTrueOrFalseFragment fragment = new LevelTrueOrFalseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLevelTrueOrFalseBinding binding = FragmentLevelTrueOrFalseBinding.inflate(inflater,container,false);
        binding.cardSkipTrue.setOnClickListener(view -> {
            callSkip.call_true(true);
        });

        binding.itTextQuestionTrueOrFalse.setText(mParam1);
        binding.itTextTimerTrue.setText(mParam2);

        Log.e(TAG, "onCreateView: "+mParam2 );


        countDownTimer = new CountDownTimer(mParam2, 1000) {
            @Override
            public void onTick(long l) {
                long hour = (l / 3600000);
                long min = (l / 60000);
                long sec = (l / 1000) % 60;
                final String x = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec);
                binding.itTextTimerTrue.setText(x);
            }
            @Override
            public void onFinish() {

                // Todo
            }
        }.start();

        return binding.getRoot();
    }
}