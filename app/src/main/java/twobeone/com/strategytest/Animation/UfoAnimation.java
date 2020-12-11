package twobeone.com.strategytest.Animation;

import android.animation.Animator;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class UfoAnimation implements BaseBattleAnimation {

    private ImageView mIvBattleItemUfoUsed;
    private RelativeLayout mLayoutBattleItemUfoUsed;
    private RelativeLayout mLayoutNoteRoot;  // 루트 레이아웃
    private UfoAnimationListener mListener;

    public UfoAnimation(ImageView mIvBattleItemUfoUsed, RelativeLayout mLayoutBattleItemUfoUsed, RelativeLayout mLayoutNoteRoot, UfoAnimationListener listener) {
        this.mIvBattleItemUfoUsed = mIvBattleItemUfoUsed;
        this.mLayoutBattleItemUfoUsed = mLayoutBattleItemUfoUsed;
        this.mLayoutNoteRoot = mLayoutNoteRoot;
        mListener = listener;
    }

    @Override
    public void PlayAnimation() {
        Log.e("kjw333", "UfoAnimation]] playAnimation()");
        // ufo 자체 애니메이션
        StartAnimationBattleItemUfo(true);
    }

    public void EndAnimation() {
        StartAnimationBattleItemUfo(false);
        mListener.onEndUfoAnimation();
    }

    // ufo 애니메이션
    private void StartAnimationBattleItemUfo (final boolean visiblity) {
        // 1. ufo 등장
        // 2. 배경 가리기
        Transition transitionBg = new Fade();
        transitionBg.setDuration(1000);
        transitionBg.setStartDelay(500);
        transitionBg.addTarget(mLayoutBattleItemUfoUsed);
        transitionBg.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.e("kjw333", "onTransitionStart()");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.e("kjw333", "onTransitionEnd()");
                if (visiblity) {
                    ufoAnimationStart();
                } else {
                    mListener.onEndUfoAnimation();
                }
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

        TransitionManager.beginDelayedTransition(mLayoutNoteRoot, transitionBg); // 부모뷰
        mLayoutBattleItemUfoUsed.setVisibility(visiblity ? View.VISIBLE : View.GONE);

        Transition transition = new Slide(Gravity.TOP);
        transition.setDuration(2000);
        transition.addTarget(mIvBattleItemUfoUsed);

        TransitionManager.beginDelayedTransition(mLayoutBattleItemUfoUsed, transition);
        mIvBattleItemUfoUsed.setVisibility(visiblity ? View.VISIBLE : View.GONE);

//        if (visiblity) {
//            EnableBattletleItemList(mLayoutBattleItemList, false); // 아이템 누르기 막기
//        } else {
//            EnableBattletleItemList(mLayoutBattleItemList, true); // 풀기
//        }


    }

    private void ufoAnimationStart() {
        YoYo.with(Techniques.Swing)
                .duration(1500)
                .repeat(6) // 10초
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        EndAnimation();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(mIvBattleItemUfoUsed);
    }

    public interface UfoAnimationListener {
        void onEndUfoAnimation();
    }
}
