package com.kevin.viewlibrary.switchs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import static com.blankj.utilcode.util.SizeUtils.dp2px;

/**
 * 选择开关
 */
public class Switch extends View implements View.OnClickListener {

    private Paint mPaint;
    private float width, height;
    private boolean isOpen = true;
    private RectF oval;
    private int mOpenColor;
    private int mCloseColor;
    private int mOverColor;

    public Switch(Context context) {
        this(context, null);
    }

    public Switch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Switch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        width = dp2px(52);
        height = width / 2;

        setOnClickListener(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oval = new RectF(0, 0, width, height);

        mOpenColor = Color.parseColor("#34a9ff");
        mCloseColor = Color.parseColor("#e8e8eb");
        mOverColor = Color.parseColor("#ffffff");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //根据控件当前状态设置画笔颜色  
        if (isOpen) {
            mPaint.setColor(mOpenColor);
        } else {
            mPaint.setColor(mCloseColor);
        }
        mPaint.setStyle(Paint.Style.FILL);// 充满

        //画底层圆角矩形  
        canvas.drawRoundRect(oval, height / 2, width / 4, mPaint);// 第二个参数是x半径，第三个参数是y半径

        //画开关圆圈 将画笔颜色设为白色   
        mPaint.setColor(mOverColor);
        //根据控件当前状态判断将圆圈画在左边还是右边  
        if (isOpen) {
            canvas.drawCircle(width / 4 * 3, height / 2, height / 2 - dp2px(2), mPaint);
        } else {
            canvas.drawCircle(width / 4, height / 2, height / 2 - dp2px(2), mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //重新测量控件的大小，主要在控件宽高属性设置为wrap_content时，将控件大小设置为实际大小
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    @Override
    public void invalidate() {
        mCloseColor = Color.parseColor("#e8e8eb");
        super.invalidate();
    }

    //测量宽度
    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int i = dp2px(52);
            result = paddingLeft + paddingRight;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    //测量高度
    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            int i = dp2px(52) / 2;
            result = i + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 点击切换isOpen值，调用postInvalidate重绘view,可用于异步线程
     */
    @Override
    public void onClick(View view) {
        isOpen = !isOpen;
        postInvalidate();
    }

    /**
     * 打开控件开关
     */
    public void open() {
        if (!isOpen) {
            isOpen = true;
            postInvalidate();
        }
    }

    /**
     * 关闭控件开关
     */
    public void close() {
        if (isOpen) {
            isOpen = false;
            postInvalidate();
        }
    }

    /**
     * 获取控件状态
     *
     * @return
     */
    public boolean isOpen() {
        return isOpen;
    }
}  