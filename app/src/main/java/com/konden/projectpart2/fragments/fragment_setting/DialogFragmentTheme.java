package com.konden.projectpart2.fragments.fragment_setting;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.konden.projectpart2.databinding.FragmentDialogThemeBinding;
import com.konden.projectpart2.interfases.CallBoolTheme;

public class DialogFragmentTheme extends DialogFragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    CallBoolTheme call;

    private String mParam1;
    private String mParam2;
    private boolean mParam3;

    public DialogFragmentTheme() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        call = (CallBoolTheme) context;
    }

    public static DialogFragmentTheme newInstance(String param1, String param2, boolean param3) {
        DialogFragmentTheme fragment = new DialogFragmentTheme();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putBoolean(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getBoolean(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDialogThemeBinding binding = FragmentDialogThemeBinding.inflate(inflater, container, false);
        binding.itTextOut.setText(mParam1);
        binding.btOut.setText(mParam2);
        binding.btOut.setOnClickListener(view -> {
            if (mParam3 == true){
                call.callboll(true);
            }else {
                call.callboll(false);

            }
        });
        return binding.getRoot();
    }
}