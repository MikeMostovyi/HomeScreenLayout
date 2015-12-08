package com.layouttest.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;


public class HomeScreenLayout extends ViewGroup {

    private static final int VIEW_SIZE = 200;
    private static final int VIEW_MARGIN = 25;
    private static final int VIEW_SIZE_WITH_MARGINS = VIEW_SIZE + VIEW_MARGIN * 2;
    private static final int MARGIN_TOP = 100;
    private static final int MARGIN_BOTTOM = 100;

    public HomeScreenLayout(Context context) {
        super(context);
    }

    public HomeScreenLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeScreenLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(
                MeasureSpec.makeMeasureSpec(VIEW_SIZE, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(VIEW_SIZE, MeasureSpec.EXACTLY));

        int itemsPerRow = countItemsPerRow(MeasureSpec.getSize(widthMeasureSpec));
        int height = (int) Math.ceil(getChildCount() / (float) itemsPerRow) * VIEW_SIZE_WITH_MARGINS + MARGIN_TOP + MARGIN_BOTTOM;

        int measuredWidth = resolveSize(widthMeasureSpec, widthMeasureSpec);
        int measuredHeight = resolveSize(height, heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int countItemsPerRow(int width) {
        return Math.min(width / VIEW_SIZE_WITH_MARGINS, getChildCount());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width = r - l;
        //final int height = b - t;
        final int itemsPerRow = countItemsPerRow(width);
        final int childCount = getChildCount();
        int currentLeft = (width - itemsPerRow * VIEW_SIZE_WITH_MARGINS) / 2;
        int currentTop = MARGIN_TOP;
        int rowItem = 0;
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            final int left = currentLeft + VIEW_MARGIN;
            final int top = currentTop + VIEW_MARGIN;
            final int right = left + VIEW_SIZE + VIEW_MARGIN;
            final int bottom = top + VIEW_SIZE + VIEW_MARGIN;
            child.layout(left, top, right, bottom);
            rowItem++;
            if (rowItem % itemsPerRow == 0) {
                int remainingItemCount = childCount - i - 1;
                if (remainingItemCount < itemsPerRow) {
                    currentLeft = (width - remainingItemCount * VIEW_SIZE_WITH_MARGINS) / 2;
                } else {
                    currentLeft = (width - itemsPerRow * VIEW_SIZE_WITH_MARGINS) / 2;
                }
                currentTop += VIEW_SIZE_WITH_MARGINS;
            } else {
                currentLeft += VIEW_SIZE_WITH_MARGINS;
            }
        }
    }

    public void setItemCount(int itemCount) {
        removeAllViews();
        final Random random = new Random();
        final Context context = getContext();
        final MarginLayoutParams lp = new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.leftMargin = VIEW_MARGIN;
        lp.topMargin = VIEW_MARGIN;
        lp.rightMargin = VIEW_MARGIN;
        lp.bottomMargin = VIEW_MARGIN;
        for (int i = 0; i < itemCount; i++) {
            TextView view = new TextView(context);
            view.setLayoutParams(lp);
            view.setText("This is home view #" + i);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundColor(random.nextInt());
            addView(view);
        }
    }
}
