package com.konden.projectpart2.fragments.fragment_end;

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
import com.konden.projectpart2.databinding.FragmentDialogEndBinding;
import com.konden.projectpart2.interfases.ListenerCallEnd;

import java.util.Objects;


public class DialogFragmentEnd extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private ListenerCallEnd callEnd;

    public DialogFragmentEnd() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callEnd = (ListenerCallEnd) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callEnd = null;
    }

    public static DialogFragmentEnd newInstance(String param1) {
        DialogFragmentEnd fragment = new DialogFragmentEnd();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDialogEndBinding binding = FragmentDialogEndBinding.inflate(inflater,container,false);
        binding.itEndText.setText(mParam1);
        binding.next.setOnClickListener(view -> {
            callEnd.call();
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