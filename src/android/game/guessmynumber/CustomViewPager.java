package android.game.guessmynumber;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    private static boolean swipe ;

    public boolean isSwipe() {
        return swipe;
    }

    public void setSwipe(boolean swipe) {
    	CustomViewPager.swipe = swipe;
    }

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (swipe) {
            return super.onInterceptTouchEvent(arg0);
        }

        // Never allow swiping to switch between pages
        return false;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
    	if (swipe) {
    		  return super.onTouchEvent(event);
        }

        // Never allow swiping to switch between pages
        return false;
    }

}