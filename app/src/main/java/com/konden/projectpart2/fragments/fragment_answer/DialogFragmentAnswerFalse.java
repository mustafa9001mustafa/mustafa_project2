package com.konden.projectpart2.fragments.fragment_answer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.FragmentDialogAnswerFalseBinding;
import com.konden.projectpart2.databinding.FragmentDialogeAnswerBinding;
import com.konden.projectpart2.interfases.ListenerCallDialogOk;

import java.util.Objects;


public class DialogFragmentAnswerFalse extends DialogFragment {


    private static final String ARG_PARAM1 = "param1";
    private String Hint;

    private ListenerCallDialogOk callDialogOk;

    public DialogFragmentAnswerFalse() {
    }


    public static DialogFragmentAnswerFalse newInstance(String Hint) {
        DialogFragmentAnswerFalse fragment = new DialogFragmentAnswerFalse();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, Hint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callDialogOk = (ListenerCallDialogOk) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callDialogOk = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Hint = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDialogAnswerFalseBinding binding = FragmentDialogAnswerFalseBinding.inflate(inflater,container,false);
        binding.itText.setText(Hint);
        binding.ok.setOnClickListener(view -> {
            callDialogOk.onFragment(false);
        });
        return  binding.getRoot();
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