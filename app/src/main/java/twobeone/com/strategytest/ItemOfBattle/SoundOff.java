package twobeone.com.strategytest.ItemOfBattle;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import twobeone.com.strategytest.BackgroundAnimation;
import twobeone.com.strategytest.BattleItem;

public class SoundOff extends BaseBattleItem implements BackgroundAnimation.BackGroundAnimationListener{
    private Context mContext;
    private BackgroundAnimation mBackgroundAnimation;

    public SoundOff(Context context, LinearLayout layoutBackground, ImageView ivEffect, BaseItemListener listener) {
        super(context);
        this.mListener = listener;
        this.mContext = context;
        this.mIvEffect = ivEffect;

        mBackgroundAnimation = new BackgroundAnimation(context, this, layoutBackground, BackgroundAnimation.BACKGROUND_ANI_TYPE_SOUNDOFF);
    }

    @Override
    public void onEndBackgroundAnimation() {
        mListener.onEndAnimation(BattleItem.ITEM_ID_SOUNDOFF);
    }

    @Override
    public void endClickItemAnimation() {
        mBackgroundAnimation.PlayAnimation();
    }
}
