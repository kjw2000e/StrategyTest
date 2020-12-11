package twobeone.com.strategytest.ItemOfBattle;

import android.animation.Animator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public abstract class BaseBattleItem {
    private Context mContext;
    int battleItemID;

    public static final int MSG_ADD_VIEW = 0;
    public static final int MSG_REMOVE_VIEW = 1;

    private LinearLayout mLayoutUsed;
    private ImageView mImageUsedItem;
    public ImageView mIvEffect;

    boolean isDuringItemStar = false;
    boolean isDuringItemDouble = false;
    boolean isDuringItemShield = false;

    public Handler mainHandler;
    public BaseItemListener mListener;

    public BaseBattleItem(Context mContext) {
        this.mContext = mContext;
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public void SetBaseItemListener(BaseItemListener listener) {
        mListener = listener;
    }

    // 아이템 클릭 애니메이션 공통
    public void StartClickAnimation(int effectResID) {
        mIvEffect.setBackgroundResource(effectResID);

        YoYo.with(Techniques.FadeInUp)
                .duration(500)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        Log.e("kjw333", "클릭 애니메이션 시작!");
                        mIvEffect.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Log.e("kjw333", "클릭 애니메이션 종료!");
                        mIvEffect.setVisibility(View.GONE);
                        endClickItemAnimation();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                })
                .playOn(mIvEffect);
    }

    abstract public void endClickItemAnimation();

    public boolean isDuringItemStar() {
        return isDuringItemStar;
    }

    public boolean isDuringItemDouble() {
        return isDuringItemDouble;
    }

    public boolean isDuringItemShield() {
        return isDuringItemShield;
    }

    public interface BaseItemListener {
        void onEndAnimation(int itemId);
    }
}
