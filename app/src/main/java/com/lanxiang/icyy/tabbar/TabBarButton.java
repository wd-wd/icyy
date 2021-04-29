package com.lanxiang.icyy.tabbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatRadioButton;

import com.lanxiang.comlib.DpUtil;
import com.lanxiang.icyy.R;

public class TabBarButton extends AppCompatRadioButton {

    private StateController stateController = new StateController();// 状态控制器

    private TabShowNew tabShowNew = new TabShowNew();

    Context context;
    int drawableMarginSize;
    Paint numP;
    Paint rRectP;
    String text;


    public TabBarButton(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TabBarButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        drawableMarginSize = DpUtil.dip2px(context, 3);

        numP = new Paint(Paint.ANTI_ALIAS_FLAG);
        //白色
        numP.setColor(Color.rgb(255, 255, 255));
        numP.setStyle(Paint.Style.FILL);
        numP.setTextSize(DpUtil.dip2px(this.context, 10)); //iphone 6plus 26px dpi 400
//        numP.setTypeface(Typeface.DEFAULT_BOLD);
//        numP.setFakeBoldText(true);
        numP.setTextAlign(Paint.Align.CENTER);
        numP.setAntiAlias(true);


        rRectP = new Paint(Paint.ANTI_ALIAS_FLAG);
        rRectP.setStyle(Paint.Style.FILL);
        rRectP.setColor(Color.parseColor("#E31414"));
    }

    public void setState(String label, String imagePath, boolean isBig) {
        //处理 本地存储icon，非程序内保存
        RadioStateDrawable offDrawable = new RadioStateDrawable(context, imagePath, label, false, isBig, RadioStateDrawable.SHADE_GRAY);
        RadioStateDrawable onDrawable = new RadioStateDrawable(context, imagePath, label, true, isBig, RadioStateDrawable.SHADE_YELLOW);
        offDrawable.setStateController(stateController);
        onDrawable.setStateController(stateController);
        offDrawable.setTabShowNew(tabShowNew);
        onDrawable.setTabShowNew(tabShowNew);
        setStateImageDrawables(onDrawable, offDrawable);
    }

    public void setState(String label, String imagePath, int offState, int onState, boolean isBig) {
        //处理 本地存储icon，非程序内保存
        RadioStateDrawable offDrawable = new RadioStateDrawable(context, imagePath, label, false, isBig, offState);
        RadioStateDrawable onDrawable = new RadioStateDrawable(context, imagePath, label, true, isBig, onState);
        offDrawable.setStateController(stateController);
        onDrawable.setStateController(stateController);
        offDrawable.setTabShowNew(tabShowNew);
        onDrawable.setTabShowNew(tabShowNew);
        setStateImageDrawables(onDrawable, offDrawable);
    }

    public void setState(String label, String offPath, String onPath, boolean isDefault, boolean isBig) {
        if (isDefault) {
            this.setState(label, Integer.parseInt(offPath), Integer.parseInt(onPath));
            return;
        }
        RadioStateDrawable offDrawable = new RadioStateDrawable(context, offPath, label, false, isBig, RadioStateDrawable.SHADE_GRAY);
        RadioStateDrawable onDrawable = new RadioStateDrawable(context, onPath, label, true, isBig, RadioStateDrawable.SHADE_YELLOW);
        offDrawable.setStateController(stateController);
        onDrawable.setStateController(stateController);
        offDrawable.setTabShowNew(tabShowNew);
        onDrawable.setTabShowNew(tabShowNew);
        setStateImageDrawables(onDrawable, offDrawable);
    }

    public void setState(String label, int imageId) {
        RadioStateDrawable offDrawable = new RadioStateDrawable(context, imageId, label, false, RadioStateDrawable.SHADE_GRAY);
        RadioStateDrawable onDrawable = new RadioStateDrawable(context, imageId, label, true, RadioStateDrawable.SHADE_YELLOW);
        offDrawable.setStateController(stateController);
        onDrawable.setStateController(stateController);
        offDrawable.setTabShowNew(tabShowNew);
        onDrawable.setTabShowNew(tabShowNew);
        setStateImageDrawables(onDrawable, offDrawable);
    }

    public void setState(String label, int imageId, int offState, int onState) {
        RadioStateDrawable offDrawable = new RadioStateDrawable(context, imageId, label, false, offState);
        RadioStateDrawable onDrawable = new RadioStateDrawable(context, imageId, label, true, onState);
        offDrawable.setStateController(stateController);
        onDrawable.setStateController(stateController);
        offDrawable.setTabShowNew(tabShowNew);
        onDrawable.setTabShowNew(tabShowNew);
        setStateImageDrawables(onDrawable, offDrawable);
    }

    public void setState(String label, int onId, int offId) {
//        RadioStateDrawable offDrawable = new RadioStateDrawable(context, offId, label, false, RadioStateDrawable.SHADE_GRAY);
//        RadioStateDrawable onDrawable = new RadioStateDrawable(context, onId, label, true, RadioStateDrawable.SHADE_YELLOW);
//        offDrawable.setStateController(stateController);
//        onDrawable.setStateController(stateController);
//        offDrawable.setTabShowNew(tabShowNew);
//        onDrawable.setTabShowNew(tabShowNew);
        Resources resource = this.getResources();
        Drawable offDrawable = resource.getDrawable(offId);
        Drawable onDrawable = resource.getDrawable(onId);
        setStateImageDrawables(onDrawable, offDrawable);
        setText(label);
        text = label;
    }

    public void setState(String label, Drawable onDrawable, Drawable offDrawable){
        setStateImageDrawables(onDrawable, offDrawable);
        setText(label);
        text = label;
    }


    public void setStateImageDrawables(Drawable onDrawable, Drawable offDrawable) {
        if(onDrawable==null || offDrawable==null) {
            return;
        }
        StateListDrawable drawables = new StateListDrawable();

        int stateChecked = android.R.attr.state_checked;
        int stateFocused = android.R.attr.state_focused;
        int statePressed = android.R.attr.state_pressed;
        int stateWindowFocused = android.R.attr.state_window_focused;

        Resources resource = this.getResources();
        Drawable xDrawable = resource.getDrawable(R.drawable.bottom_bar_highlight);

        drawables.addState(new int[]{stateChecked, -stateWindowFocused}, onDrawable);
        drawables.addState(new int[]{-stateChecked, -stateWindowFocused}, offDrawable);
        drawables.addState(new int[]{stateChecked, statePressed}, onDrawable);
        drawables.addState(new int[]{-stateChecked, statePressed}, onDrawable);
        drawables.addState(new int[]{stateChecked, stateFocused}, onDrawable);
        drawables.addState(new int[]{-stateChecked, stateFocused}, offDrawable);
        drawables.addState(new int[]{stateChecked}, onDrawable);
        drawables.addState(new int[]{-stateChecked}, offDrawable);
        drawables.addState(new int[]{}, xDrawable);
        int paddingTop = DpUtil.dip2px(context, 3);
        //下移6dp。不用padding是因为，有可能数字，等要用到这6dp的空间。
        drawables.setBounds(0, paddingTop,
                onDrawable.getIntrinsicWidth(), paddingTop + onDrawable.getIntrinsicHeight());
        setCompoundDrawables(null, drawables, null, null);
//        setCompoundDrawablePadding(DpUtil.dip2px(context, 3));
//        this.setButtonDrawable(drawables);
    }

    public void setStateColor(int onColor,int offColor){
        int stateChecked = android.R.attr.state_checked;
        int stateFocused = android.R.attr.state_focused;
        int statePressed = android.R.attr.state_pressed;
        int stateWindowFocused = android.R.attr.state_window_focused;
        int [][] states = {
                {stateChecked, -stateWindowFocused},
                {-stateChecked, -stateWindowFocused},
                {stateChecked, statePressed},
                {-stateChecked, statePressed},
                {stateChecked, stateFocused},
                {-stateChecked, stateFocused},
                {stateChecked},
                {-stateChecked},
                {}
        } ;
        int [] colors = {
                onColor,offColor,onColor,onColor,onColor,offColor,onColor,offColor,onColor
        };
        ColorStateList colorStateList = new ColorStateList(states,colors);
        setTextColor(colorStateList);
    }

    public StateController getStateController() {
        return stateController;
    }

    public TabShowNew getTabShowNew() {
        return tabShowNew;
    }

    public void setTabShowNew(TabShowNew tabShowNew) {
        this.tabShowNew = tabShowNew;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas = getTopCanvas(canvas);
        canvas.save();
        super.onDraw(canvas);
        canvas.restore();
//        // new 展示
//        if (tabShowNew != null && tabShowNew.getIsShowNew() != null && tabShowNew.getIsShowNew()) {
//            int redPointWidth = context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_item_red_point);
//            int redPointHeight = redPointWidth;
//            int rightMargin = (int) (width * 0.25f);
//            int topMargin = DpUtil.dip2px(context,7.5f);
//            Rect rectNew = new Rect(width - rightMargin - redPointWidth, topMargin, width - rightMargin, topMargin + redPointHeight);
//            canvas.drawBitmap(redPointBmp, null, rectNew, iconPaint);
//        }
//
//        // 红点展示
        if (tabShowNew != null && tabShowNew.getIsShowRedPoint() != null && tabShowNew.getIsShowRedPoint()) {
            int redPointWidth = context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_item_red_point);
            int redPointHeight = redPointWidth;
            int rightMargin = (int) (getWidth() * 0.3f);
            int topMargin = DpUtil.dip2px(this.context, 4f);

            float cY = topMargin + redPointHeight / 2f;
            float cX = getWidth() - rightMargin - redPointWidth / 2f;
            canvas.drawCircle(cX,cY,redPointWidth/2f,rRectP);
        }

        if (null != stateController && null != stateController.getNum()) {
            String num = stateController.getNum();
            Drawable[] drawables = getCompoundDrawables();
            Drawable topDrawable = drawables[1];// 上面的drawable
//            // 画数字准备
            float centerX = (getWidth() + topDrawable.getIntrinsicWidth()) / 2;
            float centerY = 0;
            // 计算数字的宽度
            float textWidth = 0;
            float[] widths = new float[num.length()];
            numP.getTextWidths(num, widths);
            for (int i = 0; i < widths.length; i++) {
                textWidth += widths[i];
            }
            // 计算数字的高度
            Paint.FontMetrics fm = numP.getFontMetrics();// 得到系统默认字体属性
            float textHeight = fm.descent - fm.top;// 获得字体高度
            centerY = centerY + textHeight / 2 + DpUtil.dip2px(context, 6);

            // 画圆角矩形准备
            float rRectWidth = Math.max(textHeight, textWidth + DpUtil.dip2px(this.context, 5));
            float rRectHeight = textHeight;


            rRectP.setAntiAlias(true);

            RectF rectF = new RectF();
            rectF.left = centerX - rRectWidth / 2;
            rectF.top = centerY - DpUtil.dip2px(this.context, 4) - rRectHeight / 2;
            rectF.right = rectF.left + rRectWidth;
            rectF.bottom = rectF.top + rRectHeight;
            float rx = rRectHeight / 2;// 圆角
            float ry = rx;// 圆角

            canvas.drawRoundRect(rectF, rx, ry, rRectP);
//            // 画 > 数字
            canvas.drawText(num, centerX, centerY, numP);
        }
    }

    private Canvas getTopCanvas(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables == null) {
            return canvas;
        }
        Drawable drawable = drawables[1];// 上面的drawable
        if (drawable == null) {
            drawable = drawables[3];// 下面的drawable
        }
        Paint.FontMetrics fm = getPaint().getFontMetrics();
        float textSize = (float) Math.ceil(fm.descent - fm.ascent);
        ;
        int drawHeight = drawable.getIntrinsicHeight();
        int drawPadding = getCompoundDrawablePadding();
        float contentHeight = textSize + drawHeight + drawPadding;
        int topPadding = (int) (getHeight() - contentHeight);
        setPadding(0, topPadding, 0, 0);
        float dy = (contentHeight - getHeight()) / 2;
        canvas.translate(0, dy);
        return canvas;
    }

    public class StateController {
        private Integer num;
        private static final int MAX_NUMBER = 100;
        private static final String MAX_STR = "99+";

        public String getNum() {
            if (num == null) {
                return null;
            }
            if (num >= MAX_NUMBER) {
                return MAX_STR;
            } else {
                return num.toString();
            }
        }

        public void setNum(Integer num) {
            this.num = num;
            invalidate();
        }

        public void addNum() {
            setNum(null == num ? 1 : num + 1);
        }

    }

    public    class TabShowNew {
        private Boolean isShowNew;

        private Boolean isShowRedPoint;

        public Boolean getIsShowNew() {
            return isShowNew;
        }

        public void setIsShowNew(Boolean isShowNew) {
            this.isShowNew = isShowNew;
            invalidate();
        }

        public Boolean getIsShowRedPoint() {
            return isShowRedPoint;
        }

        public void setIsShowRedPoint(Boolean isShowRedPoint) {
            this.isShowRedPoint = isShowRedPoint;
            invalidate();
        }
    }

}
