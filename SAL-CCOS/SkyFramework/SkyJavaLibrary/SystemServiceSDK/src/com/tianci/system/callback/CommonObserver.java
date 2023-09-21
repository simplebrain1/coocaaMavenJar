package com.tianci.system.callback;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;

import com.tianci.utils.SkySSSLog;

public abstract class CommonObserver extends ContentObserver {
    public static String TAG = "CommonObserver";
    ContentResolver contentResolver;

    public CommonObserver() {
        super(null);
    }

    public void setContentResolver(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        if (!selfChange) {
            String valueStr = uri.getQueryParameter("arg");
            SkySSSLog.d(TAG, "onChange selfChange=" + selfChange + ",valueStr=" + valueStr);
            onChange(valueStr);
        }

    }

    public abstract void onChange(String value);

    public void unregister() {
        if (contentResolver != null)
            contentResolver.unregisterContentObserver(this);
    }

}