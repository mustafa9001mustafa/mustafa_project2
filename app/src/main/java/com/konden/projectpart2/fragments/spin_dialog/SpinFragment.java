package com.konden.projectpart2.fragments.spin_dialog;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.konden.projectpart2.R;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.databinding.FragmentSpinBinding;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class SpinFragment extends DialogFragment {

    private static final String[] sectors = {"عجلة حظ", "نقطتان", "3 نقاط", "4 نقاط"};
    private static final int[] sectorsDegrees = new int[sectors.length];
    private static final Random random = new Random();
    private static RotateAnimation rotateAnimation;
    private int degree = 0, coin_free = 1;
    private boolean isSpining = false;
    private RewardedAd mRewardedAd;
    private FragmentSpinBinding binding;


    public SpinFragment() {
        // Required empty public constructor
    }


    public static SpinFragment newInstance() {
        SpinFragment fragment = new SpinFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpinBinding.inflate(inflater, container, false);


        binding.clickSpine.setOnClickListener(view -> {
            on_click();
        });
        getDegreesForSectors();
        get_coin_without();
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
                        binding.spine.startAnimation(animationAll.a14_small_big_forth(getActivity()));
                        binding.spineThis.startAnimation(animationAll.a14_small_big_forth(getActivity()));
                        binding.textShowHelp.setVisibility(View.VISIBLE);
                        binding.spine.setVisibility(View.INVISIBLE);
                        binding.spineThis.setVisibility(View.INVISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        binding.textShowHelp.startAnimation(animationAll.a14_small_big_forth(getActivity()));
                        binding.spine.startAnimation(animationAll.a9_Small_to_big(getActivity()));
                        binding.spineThis.startAnimation(animationAll.a9_Small_to_big(getActivity()));
                        binding.textShowHelp.setVisibility(View.INVISIBLE);
                        binding.spine.setVisibility(View.VISIBLE);
                        binding.spineThis.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }

    private void get_coin_without() {


        binding.spine.setOnClickListener(view -> {
            coin_free++;
            if (coin_free == 9) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (coin_free == 9)
                            coin_free = -91;
                    }
                }, 8000);
            }


            if (coin_free == -80) {
                Sherdpreferanses.getInstance().SetCoinShop(25);
                Toast.makeText(getActivity(), ".", Toast.LENGTH_SHORT).show();
            }

        });
    }


    private void getDegreesForSectors() {
        int setDegree = 360 / sectors.length;
        for (int i = 0; i < sectors.length; i++) {
            sectorsDegrees[i] = (i + 1) * setDegree;

        }
    }

    private void if_spiner() {


        degree = random.nextInt(sectors.length);
        rotateAnimation = new RotateAnimation(0, (360 * sectors.length) + sectorsDegrees[degree],
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);

        if (degree == 0) {
            Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
            set_score(4);
        } else if (degree == 1) {
            Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
            set_score(3);
        } else if (degree == 2) {
            Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
            set_score(2);
        } else if (degree == 3) {
            Toast.makeText(getActivity(), "again", Toast.LENGTH_SHORT).show();
            if_spiner();

        }
        isSpining = false;
    }

    private void on_click() {
        AD();

        if (mRewardedAd != null) {
            Activity activityContext = getActivity();
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Toast.makeText(getActivity(), "The user earned the reward.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "The user earned the reward.");
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();

                    mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdClicked() {
                            Toast.makeText(getActivity(), "onAdClicked", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Ad was clicked.");
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            if (!isSpining) {

                                degree = random.nextInt(sectors.length);
                                rotateAnimation = new RotateAnimation(0, (360 * sectors.length) + sectorsDegrees[degree],
                                        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                                rotateAnimation.setDuration(3200);
                                rotateAnimation.setFillAfter(true);
                                rotateAnimation.setInterpolator(new DecelerateInterpolator());

                                rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {
                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        if (degree == 0) {
                                            Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
                                            set_score(4);
                                        } else if (degree == 1) {
                                            Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                                            set_score(3);
                                        } else if (degree == 2) {
                                            Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                                            set_score(2);
                                        } else if (degree == 3) {
                                            Toast.makeText(getActivity(), "again", Toast.LENGTH_SHORT).show();
                                            if_spiner();
                                            binding.spine.startAnimation(rotateAnimation);
                                        }
                                        isSpining = false;

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                binding.spine.startAnimation(rotateAnimation);
                                isSpining = true;

                            }

                            Toast.makeText(getActivity(), "dismissed", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Ad dismissed fullscreen content.");
                            d_ads();
                            /////////////////////////////////////
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Ad failed to show fullscreen content.");
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdImpression() {
                            Toast.makeText(getActivity(), "impression", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Ad recorded an impression.");
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            Toast.makeText(getActivity(), "showed", Toast.LENGTH_SHORT).show();

                            // Called when ad is shown.
                            Log.d(TAG, "Ad showed fullscreen content.");
                        }
                    });

                }
            });
        } else {
            Toast.makeText(getActivity(), "لا يتوفر إعلان في الوقت الحالي \n   " +
                    " \t \t\t حاول مرة أخرى", Toast.LENGTH_SHORT).show();


        }

    }


    private void d_ads() {
        new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {

                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) / 60;
                long sec = (millisUntilFinished / 1000) % 60;
                final String x = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec);
                binding.clickSpine.setText(x);
                binding.clickSpine.setOnClickListener(view -> {
                    Toast.makeText(getActivity(), " إنتظر " + x + " لمشاهدة الإعلان التالي ", Toast.LENGTH_SHORT).show();
                });
            }

            public void onFinish() {
                binding.clickSpine.setOnClickListener(view -> {
                    on_click();
                });
                binding.clickSpine.setText(getResources().getString(R.string.spine));

            }

        }.start();

    }

    private void AD() {

        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(getActivity(), "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d(TAG, loadAdError.toString());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d(TAG, "Ad was loaded.");
                    }
                });
    }

    private void set_score(int x) {
        Sherdpreferanses.getInstance().SetCoinShop(Sherdpreferanses.getInstance().GetCoinShop() + x);
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