package com.iwobanas.screenrecorder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public abstract class AbstractScreenOverlay implements IScreenOverlay {

    private static final String TAG = "scr_AbstractScreenOverlay";

    private Context mContext;

    private View mView;

    private boolean visible = false;

    public AbstractScreenOverlay(Context context) {
        mContext = context;
    }

    protected abstract View createView();

    protected abstract WindowManager.LayoutParams getLayoutParams();

    protected LayoutInflater getLayoutInflater() {
        return (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    protected Context getContext() {
        return mContext;
    }

    @Override
    public void show() {
        if (visible) {
            Log.e(TAG, "Trying to show the view which is already displayed");
            return;
        }
        if (mView == null) {
            mView = createView();
        }

        getWindowManager().addView(mView, getLayoutParams());
        visible = true;
    }

    private WindowManager getWindowManager() {
        return (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public void hide() {
        if (!visible) {
            Log.w(TAG, "Trying to hide the view which is not displayed");
            return;
        }
        getWindowManager().removeView(mView);
        visible = false;
    }
}
