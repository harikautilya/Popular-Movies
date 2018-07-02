package com.movies.book.views;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movies.book.R;
import com.movies.book.adapter.PageFragmentAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kautilya on 19/01/18.
 */

public class ViewPagerIndicator extends LinearLayout implements PageIndicator {
    private static final String TAG = ViewPagerIndicator.class.getName();
    private float expansionHeight;
    private ViewPager mViewPager;
    private int mCurrentPage;
    private float mPageOffset;
    private ViewPager.OnPageChangeListener mPageListener;
    private int mScrollState;
    private View tabVIew;
    private int countPages;
    float mWidth;
    float mHeight;
    float mIndicatorPadding;
    private Context context;
    private final Rect mTmpContainerRect = new Rect();
    private final Rect mTmpChildRect = new Rect();
    private ArrayList<View> views;
    private int mDuration;
    private float initialHeight, targetHeight;
    private int tabCount;
    private int selectedColor;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 1);
    }

    @SuppressLint("InflateParams")
    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        tabVIew = LayoutInflater.from(context).inflate(R.layout.item_tab, null, false);
        countPages = 0;
        Display display = (scanForActivity(context)).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        tabVIew.measure(size.x, size.y);
        mWidth = tabVIew.getMeasuredWidth();
        mHeight = tabVIew.getMeasuredHeight();
        mIndicatorPadding = 0;
        expansionHeight = convertdptopx(10);
        initialHeight = mHeight;
        mDuration = 500;
        targetHeight = mHeight + (2 * expansionHeight);
        setGravity(VERTICAL);

    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    private Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        final int count = countPages;

        // These are the far left and right edges in which we are performing layout.
        int leftPos = getPaddingLeft();
        int rightPos = right - left - getPaddingRight();


        // These are the top and bottom edges in which we are performing layout.
        final int parentTop = getPaddingTop();
        final int parentBottom = bottom - top - getPaddingBottom();

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                mTmpChildRect.left = leftPos + lp.leftMargin;
                mTmpChildRect.right = (int) (leftPos + mWidth + lp.rightMargin);
                if (mCurrentPage == i) {
                    mTmpChildRect.top = (int) (Math.round(parentTop + lp.topMargin) - expansionHeight);
                    mTmpChildRect.bottom = Math.round(parentBottom - lp.bottomMargin + expansionHeight);
                } else {
                    mTmpChildRect.top = Math.round(parentTop + lp.topMargin);
                    mTmpChildRect.bottom = Math.round(parentBottom - lp.bottomMargin);

                }

                leftPos = mTmpChildRect.right;
                setViewData(mTmpChildRect, child);


                // Place the child.
                child.layout(mTmpChildRect.left, mTmpChildRect.top,
                        mTmpChildRect.right, mTmpChildRect.bottom);
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int height;
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int desiredWidth = Math.round((countPages) * (mWidth + getPaddingRight() + getPaddingLeft()) + (mIndicatorPadding * countPages));
        int desiredHeight = Math.round(mHeight + (2 * expansionHeight)) + getPaddingTop() + getPaddingBottom();


        if (widthMode == MeasureSpec.EXACTLY) {

            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {

            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }
        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }
        if (countPages > 0) {
            mWidth = width / countPages;
            setMeasuredDimension(width, height);
        } else {
            setMeasuredDimension(0, 0);
        }


    }


    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
    }

    @Override
    public void setUpViewPager(ViewPager pager) {
        if (pager == mViewPager) {
            return;
        }
        if (pager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = pager;
        countPages = mViewPager.getAdapter().getCount();
        mViewPager.addOnPageChangeListener(this);
        countPages = mViewPager.getAdapter().getCount();
        addViewTabs();
        invalidate();
        setCurrentItem(0);
    }

    private void addViewTabs() {
        views = new ArrayList<>();
        List<Integer> colors = ((PageFragmentAdapter) mViewPager.getAdapter()).getColors();
        for (int i = 0; i < countPages; i++) {
            View view = TabStrip.getView(context, i, this, mViewPager.getAdapter().getPageTitle(i).toString(), getResources().getColor(colors.get(i)));
            views.add(view);
            addView(view);
            Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
            animation1.setDuration(mDuration);
            animation1.setFillAfter(true);
            view.startAnimation(animation1);
        }
        requestLayout();
        invalidate();
    }


    @Override
    public void setUpViewPager(ViewPager pager, int initialPosition) {
        setUpViewPager(pager);
        setCurrentItem(initialPosition);
    }


    @Override
    public void setCurrentItem(int position) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }

        if (mCurrentPage != position) {
            collapse(views.get(mCurrentPage));
            mCurrentPage = position;
            mViewPager.setCurrentItem(position);
            expand(views.get(mCurrentPage));
        } else
            expand(views.get(position));

    }


    @Override
    public void setupOnPageChangeListener(ViewPager.OnPageChangeListener pageChangeListener) {
        mPageListener = pageChangeListener;
    }

    @Override
    public void notifyDataSetChanged() {
        invalidate();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG, "onPageScrolled: positionOffset: " + positionOffset + " positionOffSetPixels: " + positionOffsetPixels);
        mPageOffset = positionOffset;
        invalidate();
        if (mPageListener != null)
            mPageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        if (mScrollState == ViewPager.SCROLL_STATE_SETTLING) {
            setCurrentItem(position);
            invalidate();
        }
        if (mPageListener != null)
            mPageListener.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mScrollState = state;
        if (mPageListener != null)
            mPageListener.onPageScrollStateChanged(state);

    }


    float convertdptopx(int dp) {
        Resources r = getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }


    public void expand(final View v) {
        ObjectAnimator colorFade = ObjectAnimator.ofObject(v.findViewById(R.id.item_details), "backgroundColor" /*view attribute name*/, new ArgbEvaluator(), getContext().getResources().getColor(R.color.colorPrimary) /*from color*/, Color.WHITE /*to color*/);
        colorFade.setDuration(mDuration);
        colorFade.start();

        v.findViewById(R.id.colorBorder).setVisibility(VISIBLE);
        ObjectAnimator colorFade2 = ObjectAnimator.ofObject(v.findViewById(R.id.tab_head_line), "textColor", new ArgbEvaluator(), Color.WHITE, getContext().getResources().getColor(R.color.colorPrimary));
        colorFade2.setDuration(mDuration);
        colorFade2.start();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? (int) targetHeight
                        : (int) ((initialHeight) + ((targetHeight - initialHeight) * interpolatedTime));
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(mDuration);
        Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        animation1.setDuration(mDuration);
        animation1.setFillAfter(true);
        v.startAnimation(animation1);
        v.startAnimation(a);
    }

    public void collapse(final View v) {
        ObjectAnimator colorFade = ObjectAnimator.ofObject(v.findViewById(R.id.item_details), "backgroundColor", new ArgbEvaluator(), Color.WHITE, getContext().getResources().getColor(R.color.colorPrimary));
        colorFade.setDuration(mDuration);
        colorFade.start();
        ObjectAnimator colorFade2 = ObjectAnimator.ofObject(v.findViewById(R.id.tab_head_line), "textColor" /*view attribute name*/, new ArgbEvaluator(), getContext().getResources().getColor(R.color.colorPrimary) /*from color*/, Color.WHITE /*to color*/);
        colorFade2.setDuration(mDuration);
        colorFade2.start();
        v.findViewById(R.id.colorBorder).setVisibility(GONE);

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.getLayoutParams().height = (int) initialHeight;
                    v.setBackground(getResources().getDrawable(R.drawable.background_white_border));
                } else {
                    v.getLayoutParams().height = (int) (targetHeight - ((targetHeight - initialHeight) * interpolatedTime));
                    v.requestLayout();
                }

            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration(mDuration);


        Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        animation1.setDuration(mDuration);
        animation1.setFillAfter(true);
        v.startAnimation(a);
        v.startAnimation(animation1);
    }

    public int getTabCount() {
        return countPages;
    }

    @SuppressLint({"InflateParams", "StaticFieldLeak"})
    static class TabStrip {
        static View view;
        private static ViewPagerIndicator indicator;


        private static String text;


        static View getView(Context context, int i, ViewPagerIndicator viewPagerIndicator) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tab, null, false);
            indicator = viewPagerIndicator;
            addPosition(i);
            return view;
        }

        static View getView(Context context, int i, ViewPagerIndicator viewPagerIndicator, String text) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tab, null, false);
            ((TextView) view.findViewById(R.id.tab_head_line)).setText(text);
            indicator = viewPagerIndicator;
            addPosition(i);
            return view;
        }

        static View getView(Context context, int i, ViewPagerIndicator viewPagerIndicator, String text, int color) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tab, null, false);
            ((TextView) view.findViewById(R.id.tab_head_line)).setText(text);
            view.findViewById(R.id.colorBorder).setBackgroundColor(color);
            indicator = viewPagerIndicator;
            addPosition(i);
            return view;
        }

        private static void addPosition(final int position) {
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    indicator.setCurrentItem(position);
                }
            });
        }
    }

    void setViewData(Rect rect, View view) {

        view.setLayoutParams(new LayoutParams(rect.right - rect.left, rect.bottom - rect.top - (int) (2 * expansionHeight)));


    }
}
