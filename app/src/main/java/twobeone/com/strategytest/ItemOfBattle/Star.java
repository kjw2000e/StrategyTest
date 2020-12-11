package twobeone.com.strategytest.ItemOfBattle;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import twobeone.com.strategytest.BackgroundAnimation;
import twobeone.com.strategytest.Animation.BaseBattleAnimation;
import twobeone.com.strategytest.BattleItem;
import twobeone.com.strategytest.Animation.StarAnimation;

public class Star extends BaseBattleItem implements BackgroundAnimation.BackGroundAnimationListener, StarAnimation.StarAnimationListener {
    //1. 이펙트 애니메이션
    //2. 백그라운드 애니메이션
    private ImageView mIvStar;
    private StarAnimation starAnimation;
    private BackgroundAnimation backgroundAnimation;

    public Star(Context context, LinearLayout layoutBackground, ImageView ivEffect, ImageView ivStar, BaseItemListener listener) {
        super(context);
        this.mIvStar = ivStar;
        this.mIvEffect = ivEffect;
        this.mListener = listener;

        backgroundAnimation = new BackgroundAnimation(context, this, layoutBackground, BackgroundAnimation.BACKGROUND_ANI_TYPE_STAR);
        starAnimation = new StarAnimation(mIvStar, this);
    }

    @Override
    public void endClickItemAnimation() {
        Log.e("kjw333", "스타 애니메이션 시작");

        // 백그라운드 애니메이션과 star의 애니메이션 동시 진행
        backgroundAnimation.PlayAnimation();
        starAnimation.PlayAnimation();
    }

    @Override
    public void onEndBackgroundAnimation() {
        Log.e("kjw333", "백그라운드 애니메이션 종료");
    }

    @Override
    public void onEndStarAnimation() {
        mListener.onEndAnimation(BattleItem.ITEM_ID_STAR);
    }
}
