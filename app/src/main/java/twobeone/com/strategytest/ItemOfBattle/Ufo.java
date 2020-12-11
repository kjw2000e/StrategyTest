package twobeone.com.strategytest.ItemOfBattle;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import twobeone.com.strategytest.Animation.BaseBattleAnimation;
import twobeone.com.strategytest.BackgroundAnimation;
import twobeone.com.strategytest.BattleItem;
import twobeone.com.strategytest.Animation.UfoAnimation;

public class Ufo extends BaseBattleItem implements UfoAnimation.UfoAnimationListener {
    //1. 클릭 애니메이션
    //2. ufo 내려오는 애니메이션
    //3. 화면 가리기
    private Context mContext;
    private ImageView mIvUfo;
    private UfoAnimation ufoAnimation;

    public Ufo(Context context, ImageView ivEffect, ImageView mIvBattleItemUfoUsed, RelativeLayout mLayoutBattleItemUfoUsed, RelativeLayout mLayoutNoteRoot, BaseItemListener listener) {
        super(context);
        mContext = context;
        mIvEffect = ivEffect;
        mListener = listener;

        ufoAnimation = new UfoAnimation(mIvBattleItemUfoUsed, mLayoutBattleItemUfoUsed, mLayoutNoteRoot, this);
    }

    @Override
    public void endClickItemAnimation() {
        // 클릭 애니메이션 end 콜백
        ufoAnimation.PlayAnimation();
    }

    @Override
    public void onEndUfoAnimation() {
        // ufo 애니메이션 종료
       mListener.onEndAnimation(BattleItem.ITEM_ID_UFO);
    }
}
