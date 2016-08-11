/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */

/**
 * 文件名 : MainActivity.java
 * 包含类名列表 : MainActivity
 * 版本信息 : Ver 1.0
 * 创建日期 : 2016年08月10日 17:28
 */
package cn.keepking.viewpagerkeep;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.keepking.viewpagerkeep.LoopViewPager.LoopPagerAdapter;
import cn.keepking.viewpagerkeep.LoopViewPager.LoopViewPager;

/**
 * 类名 : MainActivity
 * 作者 : wangying
 * 实现的主要功能 :
 * 创建日期 : 2016年08月10日 17:28
 */
public class MainActivity extends Activity {

    LoopViewPager mViewPager;
    final ArrayList<View> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (LoopViewPager) findViewById(R.id.viewPager);

        views.add(getTextView("0"));
        views.add(getTextView("1"));
        views.add(getTextView("2"));
        views.add(getTextView("3"));
        views.add(getTextView("4"));
        views.add(getTextView("5"));

        mViewPager.setViews(views,getTextView("0"),getTextView("5"));
        mViewPager.setListener(new LoopViewPager.LoopViewPagerListener() {
            @Override
            public void onItemSelected(View v,int position,boolean isFirst,boolean isLast) {
                System.out.println((String)v.getTag());
                if(isLast){
                    finish();
                }
            }
        });
    }

    private View getTextView(String title){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.item_page,null,false);
        ImageView iv = (ImageView) view.findViewById(R.id.icon);
        TextView tv = (TextView) view.findViewById(R.id.text);
        tv.setText(title);
        iv.setImageResource(R.mipmap.ic_launcher);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setTag(title);
        return view;
    }
}