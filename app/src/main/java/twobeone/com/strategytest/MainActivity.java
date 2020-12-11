package twobeone.com.strategytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnStar, mBtnUfo, mBtnSoundoff;

    private RelativeLayout mLayoutRoot;

    // 나에게 사용한 아이템
    private LinearLayout mLayoutMyDefenseItem, mLayoutMyAttackItem;

    // 적에게 사용한 아이템
    private LinearLayout mLayoutEnemyDefenseItem, mLayoutEnemyAttackItem;

    private LinearLayout mLayoutBackground;
    private ImageView mIvEffect;
    private TextView mTvCurrentItem;
    private ImageView mIvStar;

    private BattleItem mBattleItemMe;
    private BattleItem mBattleItemEnemy;

    // ufo
    private RelativeLayout mLayoutBattleItemUfoUsed;
    private ImageView mIvBattleItemUfoUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStar = findViewById(R.id.btn_star);
        mBtnUfo = findViewById(R.id.btn_ufo);
        mBtnSoundoff = findViewById(R.id.btn_soundoff);
        mIvStar = findViewById(R.id.iv_star);

        mLayoutRoot = findViewById(R.id.layout_root);

        mBtnStar.setOnClickListener(this);
        mBtnUfo.setOnClickListener(this);
        mBtnSoundoff.setOnClickListener(this);

        mBtnStar.setTag(BattleItem.ITEM_ID_STAR);
        mBtnUfo.setTag(BattleItem.ITEM_ID_UFO);
        mBtnSoundoff.setTag(BattleItem.ITEM_ID_SOUNDOFF);

        mLayoutMyDefenseItem = findViewById(R.id.layout_my_defense_item);
        mLayoutMyAttackItem = findViewById(R.id.layout_my_attack_item);

        mLayoutEnemyDefenseItem = findViewById(R.id.layout_enemy_defense_item);
        mLayoutEnemyAttackItem = findViewById(R.id.layout_enemy_attack_item);
        mLayoutBackground = findViewById(R.id.layout_background);

        mTvCurrentItem = findViewById(R.id.txt_current_item);

        mIvEffect = findViewById(R.id.iv_effect);

        // ufo 레이아웃
        mLayoutBattleItemUfoUsed = findViewById(R.id.layout_activity_battle_song_item_ufo);
        mIvBattleItemUfoUsed = findViewById(R.id.iv_battle_item_ufo_used);

        mBattleItemMe = new BattleItem(this, mLayoutMyDefenseItem, mLayoutMyAttackItem, mLayoutBackground, mIvEffect);
        mBattleItemMe.SetStarView(mIvStar);
        mBattleItemMe.SetUfoView(mIvBattleItemUfoUsed, mLayoutBattleItemUfoUsed, mLayoutRoot);
        mBattleItemMe.SetSoundOffView();

        mBattleItemEnemy = new BattleItem(this, mLayoutEnemyDefenseItem, mLayoutEnemyAttackItem, mLayoutBackground, mIvEffect);
        mBattleItemMe.SetStarView(mIvStar);
        mBattleItemMe.SetUfoView(mIvBattleItemUfoUsed, mLayoutBattleItemUfoUsed, mLayoutRoot);
        mBattleItemMe.SetSoundOffView();
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        mBattleItemMe.onStartBattleItem(tag);
    }
}