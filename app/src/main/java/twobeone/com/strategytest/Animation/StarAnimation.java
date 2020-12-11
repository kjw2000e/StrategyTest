package twobeone.com.strategytest.Animation;

import android.animation.Animator;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class StarAnimation implements BaseBattleAnimation {
    private ImageView mIvStar;
    private int imgResourceId;
    private StarAnimationListener mListener;

    public StarAnimation(ImageView ivStar, StarAnimationListener listener) {
        mIvStar = ivStar;
        mListener = listener;
    }

    @Override
    public void PlayAnimation() {
        YoYo.with(Techniques.Pulse)
                .duration(500)
                .repeat(18) // 10초
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mIvStar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mIvStar.setVisibility(View.GONE);
                        mListener.onEndStarAnimation(); //todo is
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(mIvStar);
    }

    public interface StarAnimationListener {
        void onEndStarAnimation();
    }
}
