package twobeone.com.strategytest;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import twobeone.com.strategytest.ItemOfBattle.BaseBattleItem;
import twobeone.com.strategytest.ItemOfBattle.SoundOff;
import twobeone.com.strategytest.ItemOfBattle.Star;
import twobeone.com.strategytest.ItemOfBattle.Ufo;

public class BattleItem implements BaseBattleItem.BaseItemListener {
    public static final int ITEM_ID_STAR = 0;
    public static final int ITEM_ID_HAMMER = 1;
    public static final int ITEM_ID_SHIELD = 2;
    public static final int ITEM_ID_DOUBLE = 3;
    public static final int ITEM_ID_UFO = 4;
    public static final int ITEM_ID_SOUNDOFF = 5;
    public static final int ITEM_ID_ERASER = 6;
    public static final int ITEM_ID_LOCK = 7;

    public int[] resDrawable = {
            R.drawable.img_battle_item_star_center,
            R.drawable.img_battle_item_hammer_center,
            R.drawable.img_battle_item_shield_center,
            R.drawable.img_battle_item_double_center,
            R.drawable.img_battle_item_ufo_center,
            R.drawable.img_battle_item_soundoff_center,
            R.drawable.img_battle_item_eraser_center,
            R.drawable.img_battle_item_lock_center
    };

    private Context mContext;

    private LinearLayout mLayoutDefenseItem, mLayoutAttackItem;
    private LinearLayout mLayoutBackground;
    private ImageView mIvClick;

    // 아이템 뷰
    private Star star;
    private Ufo ufo;
    private SoundOff soundOff;

    public BattleItem(Context context, LinearLayout mLayoutDefenseItem, LinearLayout mLayoutAttackItem, LinearLayout mLayoutBackground, ImageView mIvClick) {
        this.mContext = context;
        this.mLayoutDefenseItem = mLayoutDefenseItem;
        this.mLayoutAttackItem = mLayoutAttackItem;
        this.mLayoutBackground = mLayoutBackground;
        this.mIvClick = mIvClick;
    }

    public void SetStarView(ImageView mIvStar) {
        star = new Star(mContext, mLayoutBackground, mIvClick, mIvStar, this);
        star.SetBaseItemListener(this);
    }

    public void SetUfoView(ImageView mIvBattleItemUfoUsed, RelativeLayout mLayoutBattleItemUfoUsed, RelativeLayout mLayoutNoteRoot) {
        ufo = new Ufo(mContext, mIvClick, mIvBattleItemUfoUsed, mLayoutBattleItemUfoUsed, mLayoutNoteRoot, this);
    }

    public void SetSoundOffView() {
        soundOff = new SoundOff(mContext, mLayoutBackground, mIvClick, this);
    }


    // 아이템 동작 시작
    // 1. 레이아웃 추가
    // 2. 애니메이션 start
    public void onStartBattleItem(int itemId) {
        // 레이아웃 추가
        AddOrRemoveUsedItem(itemId, true);

        // 애니메이션 시작
        int effectDrawable = resDrawable[itemId];
        switch (itemId) {
            case ITEM_ID_STAR:
                star.StartClickAnimation(effectDrawable);
                break;
            case ITEM_ID_UFO:
                ufo.StartClickAnimation(effectDrawable);
                break;
            case ITEM_ID_SOUNDOFF:
                soundOff.StartClickAnimation(effectDrawable);
        }
    }

    private void AddOrRemoveUsedItem(int itemId, boolean isAdd) {
        LinearLayout mLayout;
        if (itemId < ITEM_ID_UFO) {
            mLayout = mLayoutDefenseItem;
        } else {
            mLayout = mLayoutAttackItem;
        }
        if (isAdd) {
            mLayout.addView(createUsedItemView(itemId));
        } else {
            int count = mLayout.getChildCount();
            if (count > 0) {
                for (int i=0; i<count; i++) {
                    if ((int) mLayout.getChildAt(i).getTag() == itemId) {
                        mLayout.removeViewAt(i);
                        break;
                    }
                }
            }
        }
    }


    @Override
    public void onEndAnimation(int itemId) {
        // 배틀 아이템 애니메이션 종료 -> 레이아웃 삭제
        AddOrRemoveUsedItem(itemId, false);
    }

    // 이미지뷰 동적 생성
    private ImageView createUsedItemView(int pos) {
        int[] resourceBg = {
                R.drawable.img_battle_item_using_star,
                R.drawable.img_battle_item_using_hammer,
                R.drawable.img_battle_item_using_shield,
                R.drawable.img_battle_item_using_double,
                R.drawable.img_battle_item_using_ufo,
                R.drawable.img_battle_item_using_soundoff,
                R.drawable.img_battle_item_using_eraser,
                R.drawable.img_battle_item_using_lock
        };

        ImageView usedItemView = new ImageView(mContext);
        usedItemView.setTag(pos);

        float width = 50.0f;
        float height = 50.0f;
        float marginright = 5.0f;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int)width, (int)height);
        lp.rightMargin = (int) marginright;
        lp.gravity = Gravity.CENTER_VERTICAL;

        usedItemView.setLayoutParams(lp);
        usedItemView.setBackgroundResource(resourceBg[pos]);

        return usedItemView;
    }
}
