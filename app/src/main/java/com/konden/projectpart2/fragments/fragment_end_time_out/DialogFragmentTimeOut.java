package com.konden.projectpart2.fragments.fragment_end_time_out;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.FragmentDialogTimeOutBinding;
import com.konden.projectpart2.interfases.call_fragment_quastion.ListenerTimeOut;

import java.util.Objects;


public class DialogFragmentTimeOut extends DialogFragment {


    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private ListenerTimeOut listenerTimeOut;
    private int style = DialogFragment.STYLE_NO_TITLE, theme = R.style.MyDialog;

    public DialogFragmentTimeOut() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listenerTimeOut = (ListenerTimeOut) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerTimeOut = null;
    }

    public static DialogFragmentTimeOut newInstance(String param1) {
        DialogFragmentTimeOut fragment = new DialogFragmentTimeOut();
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
            setStyle(style, theme);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDialogTimeOutBinding binding = FragmentDialogTimeOutBinding.inflate(inflater, container, false);
        binding.itTimeOutText.setText(mParam1);

        binding.next.setOnClickListener(view -> {
            listenerTimeOut.time_out();
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