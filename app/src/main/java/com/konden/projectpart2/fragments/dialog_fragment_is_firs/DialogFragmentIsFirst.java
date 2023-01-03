package com.konden.projectpart2.fragments.dialog_fragment_is_firs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.konden.projectpart2.databinding.FragmentDialogIsFirstBinding;
import com.konden.projectpart2.interfases.settings.ListenerIsFirst;

import java.util.Objects;


public class DialogFragmentIsFirst extends DialogFragment {


    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private ListenerIsFirst listenerIsFirst;

    public DialogFragmentIsFirst() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listenerIsFirst = (ListenerIsFirst) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerIsFirst = null;
    }

    public static DialogFragmentIsFirst newInstance(String param1) {
        DialogFragmentIsFirst fragment = new DialogFragmentIsFirst();
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
        FragmentDialogIsFirstBinding binding = FragmentDialogIsFirstBinding.inflate(inflater, container, false);
        binding.itTimeFirstText.setText(mParam1);
        binding.okFirst.setOnClickListener(view -> {
            if (binding.checkbox.isChecked())
                listenerIsFirst.not_now();
            else
                listenerIsFirst.ok();
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