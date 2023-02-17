package com.konden.projectpart2.fragments.fragment_shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.konden.projectpart2.R;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.databinding.FragmentShopBinding;
import com.konden.projectpart2.interfases.number_image.CallImageNumber;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;

import java.util.Objects;


public class ShopFragment extends DialogFragment {


    CallImageNumber callImageNumber;
    private FragmentShopBinding binding;


    public ShopFragment() {

    }

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callImageNumber = (CallImageNumber) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callImageNumber = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setStyle(DialogFragment.STYLE_NO_TITLE,R.style.MyDialog);
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShopBinding.inflate(inflater, container, false);

        Score(0);

        CheckDataShard();
        CheckDataInFIRST();
        Save_Image_Game();
        Save_Image_Level();

        click_help();
        return binding.getRoot();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void click_help() {
        binding.help.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                AnimationAll animationAll = new AnimationAll();

                int event = motionEvent.getActionMasked();
                switch (event) {
                    case MotionEvent.ACTION_DOWN:

                        binding.textShowHelp.startAnimation(animationAll.a9_Small_to_big(getActivity()));
                        binding.cardImage1.startAnimation(animationAll.a14_small_big_forth(getActivity()));
                        binding.textShowHelp.setVisibility(View.VISIBLE);
                        binding.cardImage1.setVisibility(View.INVISIBLE);
                        break;

                    case MotionEvent.ACTION_UP:
                        binding.textShowHelp.startAnimation(animationAll.a14_small_big_forth(getActivity()));
                        binding.cardImage1.startAnimation(animationAll.a9_Small_to_big(getActivity()));
                        binding.textShowHelp.setVisibility(View.INVISIBLE);
                        binding.cardImage1.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }

    private void Save_Image_Level() {

        binding.btnSaveBackLevel1.setOnClickListener(view -> {
            Sherdpreferanses.getInstance().SetLevelImageNumber(1);
            callImageNumber.image_number();
        });

        binding.btnSaveBackLevel2.setOnClickListener(view -> {
            if (binding.getBackLevel2.getVisibility() == View.INVISIBLE) {
                Sherdpreferanses.getInstance().SetLevelImageNumber(2);
                callImageNumber.image_number();

            } else
                Toast.makeText(getActivity(), "pay image to save", Toast.LENGTH_SHORT).show();
        });

        binding.btnSaveBackLevel3.setOnClickListener(view -> {
            if (binding.getBackLevel3.getVisibility() == View.INVISIBLE) {
                Sherdpreferanses.getInstance().SetLevelImageNumber(3);
                callImageNumber.image_number();

            } else
                Toast.makeText(getActivity(), "pay image to save", Toast.LENGTH_SHORT).show();
        });

    }

    private void Save_Image_Game() {
        binding.btnSaveBackGame1.setOnClickListener(view -> {
            Sherdpreferanses.getInstance().SetGameImageNumber(1);
            callImageNumber.image_number();
        });

        binding.btnSaveBackGame2.setOnClickListener(view -> {
            if (binding.getBackGame2.getVisibility() == View.INVISIBLE) {
                Sherdpreferanses.getInstance().SetGameImageNumber(2);
                callImageNumber.image_number();

            } else
                Toast.makeText(getActivity(), "pay image to save", Toast.LENGTH_SHORT).show();
        });

        binding.btnSaveBackGame3.setOnClickListener(view -> {
            if (binding.getBackGame3.getVisibility() == View.INVISIBLE) {
                Sherdpreferanses.getInstance().SetGameImageNumber(3);
                callImageNumber.image_number();

            } else
                Toast.makeText(getActivity(), "pay image to save", Toast.LENGTH_SHORT).show();
        });

    }

    private void CheckDataInFIRST() {
        if (Sherdpreferanses.getInstance().GetImageLevel2())
            binding.getBackLevel2.setVisibility(View.INVISIBLE);

        if (Sherdpreferanses.getInstance().GetImageLevel3())
            binding.getBackLevel3.setVisibility(View.INVISIBLE);


        if (Sherdpreferanses.getInstance().GetImageGame2())
            binding.getBackGame2.setVisibility(View.INVISIBLE);


        if (Sherdpreferanses.getInstance().GetImageGame3())
            binding.getBackGame3.setVisibility(View.INVISIBLE);

    }

    private void CheckDataShard() {
        binding.imageLevel2.setOnClickListener(view -> {
            if (binding.getBackLevel2.getVisibility() == View.VISIBLE) {
                if (Sherdpreferanses.getInstance().GetCoinShop() >= 5) {
                    binding.getBackLevel2.setVisibility(View.INVISIBLE);
                    Sherdpreferanses.getInstance().SetImageLevel2(true);
                    Score(5);
                } else
                    Toast.makeText(getActivity(), "" + getResources().getString(R.string.toast_coin), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getActivity(), "تم شراء هذا العنصر", Toast.LENGTH_SHORT).show();
        });


        binding.imageLevel3.setOnClickListener(view -> {
            if (binding.getBackLevel3.getVisibility() == View.VISIBLE) {
                if (Sherdpreferanses.getInstance().GetCoinShop() >= 10) {
                    binding.getBackLevel3.setVisibility(View.INVISIBLE);
                    Sherdpreferanses.getInstance().SetImageLevel3(true);
                    Score(10);
                } else
                    Toast.makeText(getActivity(), "" + getResources().getString(R.string.toast_coin), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getActivity(), "تم شراء هذا العنصر", Toast.LENGTH_SHORT).show();

        });


        binding.imageGame2.setOnClickListener(view -> {
            if (binding.getBackGame2.getVisibility() == View.VISIBLE) {
                if (Sherdpreferanses.getInstance().GetCoinShop() >= 5) {
                    binding.getBackGame2.setVisibility(View.INVISIBLE);
                    Sherdpreferanses.getInstance().SetImageGame2(true);
                    Score(5);
                } else
                    Toast.makeText(getActivity(), "" + getResources().getString(R.string.toast_coin), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getActivity(), "تم شراء هذا العنصر", Toast.LENGTH_SHORT).show();

        });


        binding.imageGame3.setOnClickListener(view -> {
            if (binding.getBackGame3.getVisibility() == View.VISIBLE) {
                if (Sherdpreferanses.getInstance().GetCoinShop() >= 10) {
                    binding.getBackGame3.setVisibility(View.INVISIBLE);
                    Sherdpreferanses.getInstance().SetImageGame3(true);
                    Score(10);
                } else
                    Toast.makeText(getActivity(), "" + getResources().getString(R.string.toast_coin), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getActivity(), "تم شراء هذا العنصر", Toast.LENGTH_SHORT).show();

        });
    }

    private void Score(int n) {
        int i = Sherdpreferanses.getInstance().GetCoinShop();
        Sherdpreferanses.getInstance().SetCoinShop(i - n);
        binding.countCoin.setText(getResources().getText(R.string.coin) + " " + String.valueOf(Sherdpreferanses.getInstance().GetCoinShop()));
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