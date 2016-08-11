/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */

/**
 * 文件名 : LoopViewPager.java
 * 包含类名列表 : LoopViewPager
 * 版本信息 : Ver 1.0
 * 创建日期 : 2016年08月11日 12:53
 */
package cn.keepking.viewpagerkeep.LoopViewPager;

import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * 类名 : LoopViewPager
 * 作者 : wangying
 * 实现的主要功能 :
 * 创建日期 : 2016年08月11日 12:53
 */
public class LoopViewPager extends ViewPager {

    private LoopViewPagerListener mListener;
    private List<View> mViews;

    public void setListener(LoopViewPagerListener listener){
        this.mListener = listener;
    }

    public LoopViewPager(Context context) {
        super(context);
        init(context, null);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    /**
     * 给Adapter设置视图
     * @param views 视图列表
     * @param newStartView 第一个视图的深拷贝
     * @param newEndView 最后一个视图的深拷贝
     */
    public void setViews(final List<View> views,View newStartView,View newEndView) {
        mViews = views;
        mViews.add(0,newEndView);
        mViews.add(newStartView);
        LoopPagerAdapter adapter = new LoopPagerAdapter(mViews);
        setAdapter(adapter);
        setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //其实是digimonViews.size() - 2，也就是digimonArray - 1 + 1;
                //第二个参数false。默认true是平滑过渡，但现在其实已经过渡完成，自然应当是false
                if (position == 0 && positionOffset == 0) {
                    setCurrentItem(mViews.size() - 2, false);
                } else if (position == mViews.size() - 1 && positionOffset == 0) {
                    setCurrentItem(1, false);
                } else if (positionOffset == 0) {
                    if(mListener != null){
                        mListener.onItemSelected(mViews.get(position),position - 1,position - 1 == 0,position ==
                                mViews.size() - 2);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setCurrentItem(1);
    }

    public interface LoopViewPagerListener {
        void onItemSelected(View v,int position,boolean isFirst,boolean isLast);
    }
}