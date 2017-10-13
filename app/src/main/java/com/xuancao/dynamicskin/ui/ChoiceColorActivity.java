package com.xuancao.dynamicskin.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.appthemeengine.ATE;
import com.afollestad.appthemeengine.Config;
import com.xuancao.dynamicskin.R;
import com.xuancao.dynamicskin.app.SkinHelper;
import com.xuancao.dynamicskin.base.SimpleBaseActivity;
import com.xuancao.dynamicskin.view.ColorPickerView;
import com.xuancao.dynamicskin.view.HorizontalListView;

/**
 * Created by xuancao on 2017/8/7.
 */

public class ChoiceColorActivity extends SimpleBaseActivity {

    private ImageView iv_change;
    private HorizontalListView horizontal_listview;
    private CustomArrayAdapter adapter;
    private LinearLayout ll_progressbar;
    private ImageView iv_back;
    private ColorPickerView colorPickerView;
    private ColorDrawable colorDrawable;
    private CustomData[] mCustomData;
    private int main_color = 0xFFcc413a;
    private int skin_type;
    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choice_color);
    }

    @Override
    protected void initView() {
        horizontal_listview = (HorizontalListView) findViewById(R.id.horizontal_listview);
        iv_change = (ImageView) findViewById(R.id.iv_change);
        colorPickerView = (ColorPickerView) findViewById(R.id.color_picker);
        ll_progressbar = (LinearLayout) findViewById(R.id.ll_progressbar);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        mCustomData = new CustomData[]{
                new CustomData(R.mipmap.skin_red_color,getResources().getColor(R.color.red_color)),
                new CustomData(R.mipmap.skin_orange_color,getResources().getColor(R.color.orange_color)),
                new CustomData(R.mipmap.skin_black_color,getResources().getColor(R.color.black_color)),
                new CustomData(R.mipmap.skin_green_color,getResources().getColor(R.color.green_color)),
                new CustomData(R.mipmap.skin_cyan_color,getResources().getColor(R.color.cyan_color)),
                new CustomData(R.mipmap.skin_blue_color,getResources().getColor(R.color.blue_color)),
                new CustomData(R.mipmap.skin_purple_color,getResources().getColor(R.color.purple_color)),
                new CustomData(R.mipmap.skin_custom_color,0),
        };
    }

    @Override
    protected void setListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_progressbar.setVisibility(View.GONE);
                horizontal_listview.setVisibility(View.VISIBLE);
            }
        });
        horizontal_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mCustomData.length - 1) {
                    ll_progressbar.setVisibility(View.VISIBLE);
                    horizontal_listview.setVisibility(View.GONE);
                } else {
                    main_color = mCustomData[i].getBackgroundColor();
                    if (colorDrawable == null) {
                        colorDrawable = new ColorDrawable(main_color);
                        iv_change.setImageDrawable(colorDrawable);
                    } else {
                        colorDrawable.setColor(main_color);
                    }
                }
                skin_type=i+1;
                adapter.setSelectedPosition(i);
                adapter.notifyDataSetChanged();


            }
        });
        colorPickerView.setColorPickerListener(new ColorPickerView.ColorPickerListener() {
            @Override
            public void onColorChanging(int color) {
                main_color = color;
                if (colorDrawable == null) {
                    colorDrawable = new ColorDrawable(color);
                    iv_change.setBackground(colorDrawable);
                } else {
                    colorDrawable.setColor(color);
                }
            }

            @Override
            public void onColorPicked(int color) {

            }
        });

    }

    @Override
    protected void onRightClick() {
        if (main_color != 0) {
            final Config config = ATE.config(context, getATEKey());
            config.primaryColor(main_color);
//                config.accentColor(Color.parseColor("#ADFF2F"));
            config.commit();
            SkinHelper.setSkinType(skin_type);
            finish();
            recreate(); // recreation needed to reach the checkboxes in the preferences layout

        }
    }

    @Override
    protected void initData() {
        adapter = new CustomArrayAdapter(this, mCustomData);
        horizontal_listview.setAdapter(adapter);
        adapter.setSelectedPosition(SkinHelper.getSkinType()-1);

        if (Config.getPrimaryColor() != 0) {
            main_color = Config.getPrimaryColor();
        }

        colorPickerView.setCurrentColor(main_color);
        colorDrawable = new ColorDrawable(main_color);
        iv_change.setBackground(colorDrawable);
    }
}
