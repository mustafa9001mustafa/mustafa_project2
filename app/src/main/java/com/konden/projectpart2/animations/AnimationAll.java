package com.konden.projectpart2.animations;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.konden.projectpart2.R;

public class AnimationAll {

    public Animation a1_FromTheTop(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fromthetop);
    }


    public Animation a2_FromTheRight(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fortheright);
    }

    public Animation a3_FromTheLeft(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fortheleft);
    }

    public Animation a4_FromTheBottom(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.frombottom);
    }

    public Animation a5_FadeIn(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fadein);
    }

    public Animation a6_anim(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.amime2);
    }

    public Animation a7_shake(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.shake);
    }

    public Animation a8_loop(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.spinclockwise);
    }

    public Animation a9_Small_to_big(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.smalltobig);
    }

    public Animation a10_anim4(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.animeion4);
    }

    public Animation a11_anim3(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.animeion3);
    }
    public Animation a12_anim2(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.animeion2);
    }
    public Animation a13_anim1(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.anim1alqa);
    }

    public Animation a14_small_big_forth(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.smallbigforth);
    }

}
