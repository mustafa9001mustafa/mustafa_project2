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
import android.widget.Toast;

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.FragmentLevelChooseBinding;
import com.konden.projectpart2.databinding.FragmentLevelFileTextBinding;
import com.konden.projectpart2.interfases.CallSkip;

import java.util.Locale;


public class LevelFileTextFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private int mParam2;

    CountDownTimer countDownTimer;

    private CallSkip callSkip;


    public LevelFileTextFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callSkip = (CallSkip) context;
    }

    public static LevelFileTextFragment newInstance(String param1, int param2) {
        LevelFileTextFragment fragment = new LevelFileTextFragment();
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
        FragmentLevelFileTextBinding binding = FragmentLevelFileTextBinding.inflate(inflater, container, false);
        binding.cardSkipFile.setOnClickListener(view -> {
            callSkip.call_file(true);
        });

        Log.e(TAG, "onCreateView: "+mParam2 );
binding.itTextQuestionFile.setText(mParam1);
binding.itTextTimer.setText(mParam2);

        countDownTimer = new CountDownTimer(mParam2, 1000) {
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
//                Toast.makeText(getActivity(), "aaa", Toast.LENGTH_SHORT).show();
            }
        }.start();


        return binding.getRoot();
    }

}