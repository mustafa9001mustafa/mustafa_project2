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
import com.konden.projectpart2.databinding.FragmentLevelChooseBinding;
import com.konden.projectpart2.interfases.CallSkip;

import java.util.Locale;


public class LevelChooseFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM_int6 = "param6";
    private CountDownTimer countDownTimer;

    private CallSkip callSkip;

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;
    private int mParam6;

    public LevelChooseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callSkip = (CallSkip) context;
    }

    public static LevelChooseFragment newInstance(String param1, String param2 , String param3 , String param4 , String param5 , int param6) {
        LevelChooseFragment fragment = new LevelChooseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putInt(ARG_PARAM_int6, param6);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM5);
            mParam5 = getArguments().getString(ARG_PARAM5);
            mParam6 = getArguments().getInt(ARG_PARAM_int6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLevelChooseBinding binding = FragmentLevelChooseBinding.inflate(inflater,container,false);
        binding.cardSkipChoose.setOnClickListener(view -> {
            callSkip.call_choose(false);
        });

        binding.itTextQuestionChoose.setText(mParam1);
        binding.itChoose1.setText(mParam2);
        binding.itChoose2.setText(mParam3);
        binding.itChoose3.setText(mParam4);
        binding.itChoose4.setText(mParam5);
        binding.itTextTimerChoose.setText(mParam6);

        Log.e(TAG, "onCreateView: "+mParam6 );
        countDownTimer = new CountDownTimer(mParam6, 1000) {
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
//                Toast.makeText(getActivity(), "aaa", Toast.LENGTH_SHORT).show();
            }
        }.start();

        return binding.getRoot();
    }
}