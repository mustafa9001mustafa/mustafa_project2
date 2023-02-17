package com.konden.projectpart2.fragments.dialog_sound;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;

import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.FragmentBlankBinding;
import com.konden.projectpart2.interfases.settings.ListenerCallSounds;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;

import java.util.Objects;

public class DialogFragmentSound extends DialogFragment {


    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    ListenerCallSounds callSounds;
    private int style = DialogFragment.STYLE_NO_TITLE, theme = R.style.MyDialog;



    public DialogFragmentSound() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callSounds = (ListenerCallSounds) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callSounds = null;
    }

    public static DialogFragmentSound newInstance(String param1) {
        DialogFragmentSound fragment = new DialogFragmentSound();
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
        FragmentBlankBinding binding = FragmentBlankBinding.inflate(inflater, container, false);
        binding.itTextSound.setText(mParam1);

        if (Sherdpreferanses.getInstance().GetSoundOther() == false)
            binding.switch2Sound.setChecked(false);

        if (Sherdpreferanses.getInstance().GetTimerEnd() == false)
            binding.switch1Sound.setChecked(false);


        binding.switch1Sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    callSounds.sound1(true);
                } else {
                    callSounds.sound1(false);
                }
            }
        });




        binding.switch2Sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    callSounds.sound2(true);
                } else {
                    callSounds.sound2(false);
                }
            }
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