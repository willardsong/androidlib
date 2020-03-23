package com.infrastructure.adapter.recyclerview;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 *
 * @author MK
 * @date 2016/12/19
 */
public class ScaleAnimationEffect {
    private float mFromXScale;
    private float mToXScale;
    private float mFromYScale;
    private float mToYScale;
    private long mDuration;

    public void setAttributs(float fromXScale, float toXScale, float fromYScale, float toYScale, long duration) {
        this.mFromXScale = fromXScale;
        this.mFromYScale = fromYScale;
        this.mToXScale = toXScale;
        this.mToYScale = toYScale;
        this.mDuration = duration;
    }

    public Animation createAnimation() {
        ScaleAnimation anim = new ScaleAnimation(mFromXScale, mToXScale, mFromYScale, mToYScale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.setDuration(mDuration);
        return anim;
    }

    public void setTranAttributs(float fromXScale, float toXScale, float fromYScale, float toYScale) {
        this.mFromXScale = fromXScale;
        this.mFromYScale = fromYScale;
        this.mToXScale = toXScale;
        this.mToYScale = toYScale;
    }

    public Animation createTranAnimation() {
        TranslateAnimation anim = new TranslateAnimation(mFromXScale, mToXScale, mFromYScale, mToYScale);
        anim.setFillAfter(true);
        anim.setInterpolator(new AccelerateInterpolator());
        return anim;
    }
}
