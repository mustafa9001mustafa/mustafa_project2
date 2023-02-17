package com.konden.projectpart2.fragments.fragment_setting;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.FragmentDialogLanguageBinding;
import com.konden.projectpart2.interfases.settings.CallFragment;

import java.util.Objects;


public class DialogFragmentLanguage extends DialogFragment {

    CallFragment language;

    private boolean a = false, e = false;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";


    private String mParam1;
    private String mParam2;
    private String mParam3;

    private int style = DialogFragment.STYLE_NO_TITLE, theme = R.style.MyDialog;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        language = (CallFragment) context;
    }

    public DialogFragmentLanguage() {
    }

    public static DialogFragmentLanguage newInstance(String param1, String param2, String param3) {
        DialogFragmentLanguage fragment = new DialogFragmentLanguage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
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
            setStyle(style, theme);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDialogLanguageBinding binding = FragmentDialogLanguageBinding.inflate(inflater, container, false);
        binding.lan1.setOnClickListener(view -> {
            binding.lan1.setBackgroundResource(R.drawable.shaponclick);
            binding.lan2.setBackgroundResource(R.drawable.shapnotonclick);
            a = true;
            e = false;
        });

        binding.lan2.setOnClickListener(view -> {
            binding.lan2.setBackgroundResource(R.drawable.shaponclick);
            binding.lan1.setBackgroundResource(R.drawable.shapnotonclick);
            a = false;
            e = true;
        });

        binding.itTextLan.setText(mParam1);
        binding.lan1.setText(mParam2);
        binding.lan2.setText(mParam3);
        binding.btnSaveData.setOnClickListener(view -> {
            if (a == true)
                language.callLanguage(true);
            else
                language.callLanguage(false);

        });

        binding.cloIcon.setOnClickListener(view -> {
            language.callLanguageClose(true);
        });
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = Objects.requireNonNull(getDialog()).getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(params);
    }
}