package com.tianci.system.callback;

import android.content.Context;

public class SkySystemController {
    private static SkySystemController mInstance;
    private Context mContext;

    private SkySystemController(){

    }
    public static synchronized SkySystemController getInstance() {
        if (mInstance == null) {
            mInstance = new SkySystemController();
        }
        return mInstance;
    }
    public void init(Context context) {
        mContext = context.getApplicationContext();
        if (mContext == null) {
            mContext = context;
        }
    }
    public synchronized CommonAdapter getCommonAdapter() {
        if (mContext == null) {
            return null;
        }
        return CommonAdapter.getInstance(mContext);
    }

}
