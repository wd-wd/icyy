package com.lanxiang.icyy.tabbar;

import com.lanxiang.icyy.R;

public class TabBarHelper {
    public static final String LABEL_NAME_HOME = "首页";
    public static final String LABEL_NAME_CATEGORY = "分类";
    public static final String LABEL_NAME_SHOPPINGCAR = "购物车";
    public static final String LABEL_NAME_MINE = "我的";
    public static final String LABEL_NAME_MEMBER = "会员";
    public static int getDrawableIdByLabel(String label, boolean highlight) {
        if (highlight) {
            if (LABEL_NAME_HOME.equals(label)) {
                return R.drawable.main_bottom_tab_home_normal;
            } else if (LABEL_NAME_CATEGORY.equals(label)) {
                return R.drawable.main_bottom_tab_category_normal;
            } else if (LABEL_NAME_SHOPPINGCAR.equals(label)) {
                return R.drawable.main_bottom_tab_cart_normal;
            } else if (LABEL_NAME_MINE.equals(label)) {
                return R.drawable.main_bottom_tab_personal_normal;
            } else if (LABEL_NAME_MEMBER.equals(label)) {
                return R.drawable.main_bottom_tab_vip_large_normal;
            }
        } else {
            if (LABEL_NAME_HOME.equals(label)) {
                return R.drawable.main_bottom_tab_home_focus;
            } else if (LABEL_NAME_CATEGORY.equals(label)) {
                return R.drawable.main_bottom_tab_category_focus;
            } else if (LABEL_NAME_SHOPPINGCAR.equals(label)) {
                return R.drawable.main_bottom_tab_cart_focus;
            } else if (LABEL_NAME_MINE.equals(label)) {
                return R.drawable.main_bottom_tab_personal_focus;
            } else if (LABEL_NAME_MEMBER.equals(label)) {
                return R.drawable.main_bottom_tab_vip_large_focus;
            }
        }

        return R.drawable.main_bottom_tab_home_normal;
    }
}
