package com.tianci.tv.framework.implement.setting;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.tianci.tv.define.SkyTvCommand;
import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.utils.TranUtils;

import java.util.ArrayList;
import java.util.List;

import static com.tianci.tv.define.SkyTvCommand.URI_PATH_QUICK_SETTING_ENABLE;
import static com.tianci.tv.define.SkyTvCommand.URI_PATH_QUICK_SETTING_GET;
import static com.tianci.tv.define.SkyTvCommand.URI_PATH_QUICK_SETTING_GET_LIST;
import static com.tianci.tv.define.SkyTvCommand.URI_PATH_QUICK_SETTING_NEED_CHECK;
import static com.tianci.tv.define.SkyTvCommand.URI_PATH_QUICK_SETTING_SET;
import static com.tianci.tv.define.SkyTvCommand.URI_PATH_QUICK_SETTING_SUPPORT;

/**
 * Created by hq on 2017/12/2.
 */

public class TVQuickSettingAPIImpl implements QuickSettingAPI {
    private ContentResolver mContentResolver;

    public TVQuickSettingAPIImpl(Context context) {
        mContentResolver = context.getContentResolver();
    }

    public boolean isSupport(String tableName){
        if (mContentResolver == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(URI_PATH_QUICK_SETTING_SUPPORT + tableName), SkyTvCommand.ATTRIBUTE_IS_SUPPORT, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return false;
        }
        int result = 0;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_SUPPORT[0]);
            if (index >= 0) {
                result = cursor.getInt(index) == 0 ? 0 : 1;
            }
        }
        cursor.close();
        return result == 1;
    }

    public boolean isEnable(String tableName){
        if (mContentResolver == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(URI_PATH_QUICK_SETTING_ENABLE + tableName), SkyTvCommand.ATTRIBUTE_IS_ENABLE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return false;
        }
        int result = 0;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_ENABLE[0]);
            if (index >= 0) {
                result = cursor.getInt(index) == 0 ? 0 : 1;
            }
        }
        cursor.close();
        return result == 1;
    }

    public boolean isNeedCheck(String tableName){
        if (mContentResolver == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(URI_PATH_QUICK_SETTING_NEED_CHECK + tableName), SkyTvCommand.ATTRIBUTE_IS_NEED_CHECK, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return false;
        }
        int result = 0;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_NEED_CHECK[0]);
            if (index >= 0) {
                result = cursor.getInt(index) == 0 ? 0 : 1;
            }
        }
        cursor.close();
        return result == 1;
    }

    public int getCurrentIndex(String tableName){
        if (mContentResolver == null) {
            return -1;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(URI_PATH_QUICK_SETTING_GET + tableName), SkyTvCommand.ATTRIBUTE_CURRENT_INDEX, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return -1;
        }
        int result = 0;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_CURRENT_INDEX[0]);
            if (index >= 0) {
                result = cursor.getInt(index);
            }
        }
        cursor.close();
        return result;
    }

    public List<String> getSettingList(String tableName){
        if (mContentResolver == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(URI_PATH_QUICK_SETTING_GET_LIST + tableName), SkyTvCommand.ATTRIBUTE_OPTIONAL, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        if (cursor.moveToFirst() == false) {
            cursor.close();
            return null;
        }
        int length = SkyTvCommand.ATTRIBUTE_OPTIONAL.length;
        int[] index_list = new int[length];
        for (int i = 0; i < length; i++) {
            index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_OPTIONAL[i]);
        }
        int count = cursor.getCount();
        List<String> list = new ArrayList<String>(count);
        for (int i = 0; i < count; i++) {
            String option = cursor.getString(index_list[0]);
            list.add(option);
            if (cursor.moveToNext() == false) {
                break;
            }
        }
        cursor.close();
        return list;
    }

    public boolean setCurrentByIndex(String tableName,int index){
        if (mContentResolver == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.ATTRIBUTE_CURRENT_INDEX[0], index);
        try {
            mContentResolver.update(Uri.parse(URI_PATH_QUICK_SETTING_SET + tableName), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
