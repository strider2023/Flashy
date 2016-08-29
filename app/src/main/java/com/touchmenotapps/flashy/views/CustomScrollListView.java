package com.touchmenotapps.flashy.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ListView;

/**
 * Created by school on 29/8/16.
 */
public class CustomScrollListView extends ListView {

    private boolean isSingleScroll = false;
    private VelocityTracker velocity = null;
    final private float escapeVelocity = 2000.0f;
    final private int minDistanceMoved = 20;
    private float startY = 0;

    public CustomScrollListView(Context context) {
        super(context);
    }

    public CustomScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isSingleScroll() {
        return isSingleScroll;
    }

    public void setSingleScroll(boolean singleScroll) {
        isSingleScroll = singleScroll;
    }

    public int getVerticalScrollOffset() { return getFirstVisiblePosition(); }

    @Override
    public boolean dispatchTouchEvent(MotionEvent aMotionEvent) {
        if (aMotionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (isSingleScroll && velocity == null)
                velocity = VelocityTracker.obtain();
            startY = aMotionEvent.getY();
            return super.dispatchTouchEvent(aMotionEvent);
        }

        if (aMotionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (velocity != null) {
                if (Math.abs(aMotionEvent.getY() - startY) > minDistanceMoved) {
                    velocity.computeCurrentVelocity(1000);
                    float velocity = this.velocity.getYVelocity();

                    if (aMotionEvent.getY() > startY) {
                        // always lock
                        if (velocity > escapeVelocity)
                            smoothScrollToPosition(getFirstVisiblePosition());
                        else {
                            // lock if over half way there
                            View view = getChildAt(0);
                            if (view != null) {
                                if (view.getBottom() >= getHeight() / 2)
                                    smoothScrollToPosition(getFirstVisiblePosition());
                                else
                                    smoothScrollToPosition(getLastVisiblePosition());
                            }
                        }
                    }
                    else {
                        if (velocity < -escapeVelocity)
                            smoothScrollToPosition(getLastVisiblePosition());
                        else {
                            // lock if over half way there
                            View view = getChildAt(1);
                            if (view != null) {
                                if (view.getTop() <= getHeight() / 2)
                                    smoothScrollToPosition(getLastVisiblePosition());
                                else
                                    smoothScrollToPosition(getFirstVisiblePosition());
                            }
                        }
                    }
                }
                velocity.recycle();
            }
            velocity = null;

            if (isSingleScroll) {
                if (Math.abs(aMotionEvent.getY() - startY) > minDistanceMoved)
                    return super.dispatchTouchEvent(aMotionEvent);
            } else
                return super.dispatchTouchEvent(aMotionEvent);
        }

        if (isSingleScroll) {
            if (velocity == null) {
                velocity = VelocityTracker.obtain();
                startY = aMotionEvent.getY();
            }
            velocity.addMovement(aMotionEvent);
        }

        return super.dispatchTouchEvent(aMotionEvent);
    }
}
