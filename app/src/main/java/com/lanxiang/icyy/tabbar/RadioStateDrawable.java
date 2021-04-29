package com.lanxiang.icyy.tabbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.lanxiang.comlib.DpUtil;
import com.lanxiang.icyy.R;

import java.io.InputStream;

public class RadioStateDrawable extends Drawable {

    private Bitmap bitmap;
    Context context;

    public static int width;
    private boolean highlight;
    private String label;
    private  boolean isBig;
    private TabBarButton.StateController stateController;// 状态控制器
    private TabBarButton.TabShowNew tabShowNew;

    private Bitmap newBmp;
    private Bitmap redPointBmp;
    Paint iconPaint;
    Paint textPaint;
    Paint numP;

    public static final int SHADE_GRAY = 0;
    public static final int SHADE_BLUE = 1;
    public static final int SHADE_MAGENTA = 2;
    public static final int SHADE_YELLOW = 3;
    public static final int SHADE_GREEN = 4;
    public static final int SHADE_RED = 5;
    public static final int SHADE_ORANGE = 6;

    /**
     * 处理本地存储icon
     *
     * @param context
     * @param imagePath
     * @param label
     * @param highlight
     * @param shade
     */
    public RadioStateDrawable(Context context, String imagePath, String label, boolean highlight, boolean isBig, int shade) {
        this.isBig = isBig;
        this.highlight = highlight;
        this.label = label;
        this.context = context;
        bitmap = BitmapFactory.decodeFile(imagePath);

        if (bitmap == null) {
            InputStream is = context.getResources().openRawResource(TabBarHelper.getDrawableIdByLabel(label, highlight));
            bitmap = BitmapFactory.decodeStream(is);
        }
        initPaint();
    }

    public RadioStateDrawable(Context context, String imagePath, String label, boolean highlight, boolean isBig, int startGradientColor, int endGradientColor) {
        super();
        this.highlight = highlight;
        this.isBig = isBig;
        this.context = context;
        this.label = label;
        bitmap = BitmapFactory.decodeFile(imagePath);

        if (bitmap == null) {
            InputStream is = context.getResources().openRawResource(TabBarHelper.getDrawableIdByLabel(label, highlight));
            bitmap = BitmapFactory.decodeStream(is);
        }
        initPaint();
    }

    public RadioStateDrawable(Context context, int imageID, String label, boolean highlight, int shade) {
        super();
        this.highlight = highlight;
        this.context = context;
        this.label = label;
        InputStream is = context.getResources().openRawResource(imageID);
        bitmap = BitmapFactory.decodeStream(is);
        initPaint();
    }
    private void initPaint() {

        iconPaint = new Paint();
        iconPaint.setAntiAlias(true);
        iconPaint.setFilterBitmap(true);
        iconPaint.setDither(true);

        textPaint = new Paint();
        textPaint.setTextSize(DpUtil.sp2px(context, 11));

        numP = new Paint();
        numP.setColor(Color.rgb(241, 83, 83));
        numP.setStyle(Paint.Style.FILL);
        numP.setTextSize(DpUtil.dip2px(this.context,10)); //iphone 6plus 26px dpi 400
        numP.setTypeface(Typeface.DEFAULT_BOLD);
        numP.setFakeBoldText(true);
        numP.setTextAlign(Align.CENTER);
        numP.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        //画底部文字
        if (highlight) {
            textPaint.setColor(Color.parseColor("#E63232"));
        } else {
            textPaint.setColor(Color.parseColor("#666666"));
        }


        int iconW = context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_icon_width);
        int iconH = 0;
        if (isBig) {
            iconH = context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_big_icon_height);
        } else {
            iconH = context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_icon_height);
        }
        int iconWidth = iconW; // xiangsu
        int iconHeight = iconH;
        /*
         * if (width==0) { if (screen_width==0) screen_width = 320; width=screen_width/5; }
         */
//        width = copyBounds().width();
        int x = ((width - iconWidth) / 2);
        int y = 0;

        canvas.drawColor(Color.TRANSPARENT);

        // 画数字准备
        Rect rect = new Rect(x, y, x + iconWidth, y + iconHeight);

        // 画图标

        canvas.drawBitmap(bitmap, null, rect, iconPaint);


    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    public void setStateController(TabBarButton.StateController stateController) {
        this.stateController = stateController;
    }

    public TabBarButton.TabShowNew getTabShowNew() {
        return tabShowNew;
    }

    public void setTabShowNew(TabBarButton.TabShowNew tabShowNew) {
        this.tabShowNew = tabShowNew;
    }

}
