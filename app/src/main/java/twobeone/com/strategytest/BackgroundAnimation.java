package twobeone.com.strategytest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import twobeone.com.strategytest.Animation.BaseBattleAnimation;

public class BackgroundAnimation implements BaseBattleAnimation {
    private Context mContext;

    private LinearLayout mLayoutBackground;
    private Animation mAnimation;

    private BackGroundAnimationListener mListener;

    public static final int BACKGROUND_ANI_TYPE_STAR = 0;
    public static final int BACKGROUND_ANI_TYPE_SHIELD = 1;
    public static final int BACKGROUND_ANI_TYPE_DOUBLE = 2;
    public static final int BACKGROUND_ANI_TYPE_SOUNDOFF = 3;

    private int imgResourceIndex;
    private int[] imgResource = {
            R.drawable.bg_layout_battle_item_star,
            R.drawable.bg_layout_battle_item_shield,
            R.drawable.bg_layout_battle_item_double,
            R.drawable.bg_layout_battle_item_soundoff,
    };


    public BackgroundAnimation(Context context, BackGroundAnimationListener listener, LinearLayout layoutBackground, int resIndex) {
        mContext = context;
        mListener = listener;
        mLayoutBackground = layoutBackground;
        mAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_blink);
        imgResourceIndex = resIndex;
    }

    @Override
    public void PlayAnimation() {
        Log.e("kjw333", "백그라운드 애니메이션 시작");
        mLayoutBackground.setBackgroundResource(imgResource[imgResourceIndex]);

        mAnimation.reset();
        mLayoutBackground.startAnimation(mAnimation);
        mLayoutBackground.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mLayoutBackground.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLayoutBackground.setVisibility(View.GONE);
                mListener.onEndBackgroundAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public interface BackGroundAnimationListener{
        void onEndBackgroundAnimation();
    }
}
