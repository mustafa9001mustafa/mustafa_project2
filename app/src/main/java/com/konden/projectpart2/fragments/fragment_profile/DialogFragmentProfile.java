package com.konden.projectpart2.fragments.fragment_profile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.konden.projectpart2.R;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.databinding.FragmentDialogeBinding;
import com.konden.projectpart2.interfases.CallProfileData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class DialogFragmentProfile extends DialogFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private FragmentDialogeBinding binding;
    public static final String TAG = "tag";
    String birthday, gender, suser, semail, Country;
    CallProfileData callData;

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;




    public DialogFragmentProfile() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callData = (CallProfileData) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callData = null;
    }


    public static DialogFragmentProfile newInstance(
            String param1, String param2, String param3, String param4, String param5
    ) {
        DialogFragmentProfile fragment = new DialogFragmentProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
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
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDialogeBinding.inflate(inflater, container, false);
        binding.getRoot().startAnimation(new AnimationAll().a4_FromTheBottom(getActivity()));
        binding.inputUser.setText(mParam1);
        suser = mParam1;
        binding.inputEmail.setText(mParam2);
        semail = mParam2;

        binding.btnBirthDay.setText(mParam3);
        birthday = mParam3;

        if (mParam4.equals(getString(R.string.male))) {
            binding.male.setChecked(true);
            gender = mParam4;
        } else {
            binding.female.setChecked(true);
            gender = mParam4;
        }

        USERNAME();
        BIRTHDAY();
        GENDER();
        CONTRE();

        binding.btnSaveData.setOnClickListener(view -> {
            Validation();
        });
        return binding.getRoot();
    }

    private void Validation() {
        try {
            EMAIL();
            if ((!suser.equals(""))
                    && (!semail.equals(""))
                    && (!birthday.equals("")) && (!gender.equals("")) && (!Country.equals(""))) {
                callData.onProfile(suser, semail, birthday, gender, Country, 9);
                Toast.makeText(getActivity(), "تم التعديل", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), R.string.completdata, Toast.LENGTH_SHORT).show();

        }

    }

    private void EMAIL() {
        String email = binding.editEmail.getEditText().getText().toString();
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            semail = binding.editEmail.getEditText().getText().toString();
        }
    }


    private void USERNAME() {

        binding.inputUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() > binding.editUser.getCounterMaxLength()) {

                    binding.editUser.setError("Max character length");
                    binding.userIcon.setVisibility(View.INVISIBLE);


                } else {

                    if (charSequence.length() < 3) {
                        binding.editUser.setError("Min character length");
                        binding.userIcon.setVisibility(View.INVISIBLE);

                    } else if (charSequence.length() > 3 && charSequence.length() < binding.editUser.getCounterMaxLength()) {
                        binding.editUser.setError("");
                        binding.editUser.setErrorIconDrawable(R.drawable.ic_baseline_check_circle);
                        suser = binding.editUser.getEditText().getText().toString();
                        binding.userIcon.setVisibility(View.INVISIBLE);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    private void BIRTHDAY() {

        binding.btnBirthDay.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            if (year >= 2017) {
                                Toast.makeText(getActivity(), "the data " + year + " " + getString(R.string.isunge), Toast.LENGTH_SHORT).show();
                                binding.btnBirthDay.setError("enter Birth day");
                            } else {
                                Toast.makeText(getActivity(), "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year, Toast.LENGTH_SHORT).show();
                                String dateString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                birthday = dateString;
                                Log.e(TAG, "onDateSet: " + birthday);
                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date d1 = null;
                                try {
                                    d1 = dateFormat.parse(dateString);

                                    binding.btnBirthDay.setText(dateString);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });

    }

    private void GENDER() {

        binding.gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                gender = (R.id.male == i) ? "male" : "female";
            }
        });
    }

    private void CONTRE() {
        ArrayList<String> list = new ArrayList<>();
        list.add(getString(R.string.Entercontrey));
        list.add(getString(R.string.palestine));
        list.add(getString(R.string.Egypt));
        list.add(getString(R.string.Quoter));
        list.add(getString(R.string.SaudiArabia));
        list.add(getString(R.string.Algeria));
        list.add(getString(R.string.Kuwait));
        list.add(getString(R.string.Morocco));
        list.add(getString(R.string.Lebanon));
        list.add(getString(R.string.Jordan));
        list.add(getString(R.string.Sudan));
        list.add(getString(R.string.Tunisia));
        list.add(getString(R.string.Sorya));
        list.add(getString(R.string.Iraq));
        list.add(getString(R.string.Yemen));
        list.add(getString(R.string.Oman));
        list.add(getString(R.string.UAS));
        list.add(getString(R.string.Algeria));
        list.add(getString(R.string.UAS));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCountry.setAdapter(adapter);

        binding.spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                binding.spinnerCountry.setSelection(i, true);
                View v = binding.spinnerCountry.getSelectedView();
                ((TextView) v).setTextColor(Color.WHITE);
                if (!(i == 0)) {
                    Country = String.valueOf(adapterView.getItemAtPosition(i));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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