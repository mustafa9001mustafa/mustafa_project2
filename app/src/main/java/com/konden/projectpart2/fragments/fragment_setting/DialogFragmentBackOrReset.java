package com.konden.projectpart2.fragments.fragment_setting;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.FragmentDialogBackBinding;
import com.konden.projectpart2.interfases.settings.CallFragment;

import java.util.Objects;

public class DialogFragmentBackOrReset extends DialogFragment {
    int style = DialogFragment.STYLE_NO_TITLE;
    int theme = R.style.MyDialog;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private CallFragment call;
    private String mParam1;
    private String mParam2;
    private String mParam3;

    public DialogFragmentBackOrReset() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        call = (CallFragment) context;
    }

    public static DialogFragmentBackOrReset newInstance(String param1 , String param2, String param3) {
        DialogFragmentBackOrReset fragment = new DialogFragmentBackOrReset();
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
        FragmentDialogBackBinding binding = FragmentDialogBackBinding.inflate(inflater,container,false);
//        binding.close.setButtonColor(requireActivity().getColor(R.color.card_background_color));
//        binding.non.setButtonColor(requireActivity().getColor(R.color.green));
        binding.itText.setText(mParam1);
        binding.close.setText(mParam2);
//        binding.non.setText(mParam3);

        binding.close.setOnClickListener(view -> {
            call.call(true);
        });
//        binding.non.setOnClickListener(view -> {
//            call.call(false);
//        });
        binding.closeIcon.setOnClickListener(view -> {
            call.call(false);
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