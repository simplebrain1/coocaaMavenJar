package com.tianci.tv.framework.implement.setting;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.skyworth.framework.skysdk.properties.SkySystemProperties;
import com.tianci.tv.define.SkyTvCommand;
import com.tianci.tv.define.SkyTvDefine;
import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.utils.TranUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hq on 2017/12/2.
 */

public class SysContentProviderSettingAPIImpl extends SettingAPI {
    private ContentResolver mContentResolver;

    public SysContentProviderSettingAPIImpl(Context context) {
        mContentResolver = context.getContentResolver();
    }

    @Override
    public boolean setBootsource(Source source) {
        if (mContentResolver == null) {
            return false;
        }
        if (source == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[0], source.name);
        contentValues.put(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[1], source.index);
        contentValues.put(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[2], source.displayName);
        if(source.id!=null){
            contentValues.put(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[3], source.id);
        }
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.TVP_URI_PATH_SETTING_BOOT_SOURCE), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Source getBootsource() {
        if (mContentResolver == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_SETTING_BOOT_SOURCE), SkyTvCommand.ATTRIBUTE_BOOT_SOURCE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        Source value = null;
        if (cursor.moveToFirst()) {
            int length = SkyTvCommand.ATTRIBUTE_BOOT_SOURCE.length;
            int[] index_list = new int[length];
            for (int i = 0; i < length; i++) {
                index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[i]);
            }
            value = TranUtils.tranCursorToBootSource(cursor, index_list);
        }
        cursor.close();
        return value;
    }

    @Override
    public List<Source> getBootsourceList() {
        if (mContentResolver == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_SETTING_SOURCE_LIST), SkyTvCommand.ATTRIBUTE_BOOT_SOURCE, null, null, null);
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
        int length = SkyTvCommand.ATTRIBUTE_BOOT_SOURCE.length;
        int[] index_list = new int[length];
        for (int i = 0; i < length; i++) {
            index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[i]);
        }
        int count = cursor.getCount();
        List<Source> list = new ArrayList<Source>(count);
        for (int i = 0; i < count; i++) {
            Source source = TranUtils.tranCursorToBootSource(cursor, index_list);
            list.add(source);
            if (cursor.moveToNext() == false) {
                break;
            }
        }
        cursor.close();
        return list;
    }


    @Override
    public Source getTvModeSource() {
        if (mContentResolver == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SETTING_TVMODE_SOURCE), SkyTvCommand.ATTRIBUTE_BOOT_SOURCE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        Source value = null;
        if (cursor.moveToFirst()) {
            int length = SkyTvCommand.ATTRIBUTE_BOOT_SOURCE.length;
            int[] index_list = new int[length];
            for (int i = 0; i < length; i++) {
                index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[i]);
            }
            value = TranUtils.tranCursorToBootSource(cursor, index_list);
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean setTvModeSource(Source source) {
        if (mContentResolver == null) {
            return false;
        }
        if (source == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[0], source.name);
        contentValues.put(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[1], source.index);
        contentValues.put(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[2], source.displayName);
        if(source.id!=null){
            contentValues.put(SkyTvCommand.ATTRIBUTE_BOOT_SOURCE[3], source.id);
        }
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.URI_PATH_SETTING_TVMODE_SOURCE), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean setBootChannel(ChannelInfo channelInfo) {
        if (mContentResolver == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        if (channelInfo == null) {
            contentValues.put(SkyTvCommand.ATTRIBUTE_CHANNEL[0], ChannelInfo.BOOT_CHANNEL_ID_NULL);
        }else {
            contentValues.put(SkyTvCommand.ATTRIBUTE_CHANNEL[0], channelInfo.id);
            contentValues.put(SkyTvCommand.ATTRIBUTE_CHANNEL[1], channelInfo.name);
            contentValues.put(SkyTvCommand.ATTRIBUTE_CHANNEL[2], channelInfo.index);
            contentValues.put(SkyTvCommand.ATTRIBUTE_CHANNEL[3], channelInfo.channelType);
        }
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.URI_PATH_SETTING_BOOT_CHANNEL), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ChannelInfo getBootChannel() {
        if (mContentResolver == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SETTING_BOOT_CHANNEL), SkyTvCommand.ATTRIBUTE_CHANNEL, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        ChannelInfo value = null;
        if (cursor.moveToFirst()) {
            int length = SkyTvCommand.ATTRIBUTE_CHANNEL.length;
            int[] index_list = new int[length];
            for (int i = 0; i < length; i++) {
                index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_CHANNEL[i]);
            }
            value = TranUtils.tranCursorToChannelInfo(cursor, index_list);
        }
        cursor.close();
        return value;
    }

    @Override
    public List<ChannelInfo> getBootChannelList() {
        if (mContentResolver == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SETTING_CHANNEL_LIST), SkyTvCommand.ATTRIBUTE_CHANNEL, null, null, null);
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
        int length = SkyTvCommand.ATTRIBUTE_CHANNEL.length;
        int[] index_list = new int[length];
        for (int i = 0; i < length; i++) {
            index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_CHANNEL[i]);
        }
        int count = cursor.getCount();
        List<ChannelInfo> list = new ArrayList<ChannelInfo>(count);
        for (int i = 0; i < count; i++) {
            ChannelInfo channelInfo = TranUtils.tranCursorToChannelInfo(cursor, index_list);
            list.add(channelInfo);
            if (cursor.moveToNext() == false) {
                break;
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public boolean setAutoSwitchSource(boolean on) {
        if (mContentResolver == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.ATTRIBUTE_AUTO_SWITCH_SOURCE[0], on);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.URI_PATH_SETTING_AUTO_SWITCH_SOURCE), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean getAutoSwitchSource() {
        if (mContentResolver == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SETTING_AUTO_SWITCH_SOURCE), SkyTvCommand.ATTRIBUTE_AUTO_SWITCH_SOURCE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_AUTO_SWITCH_SOURCE[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean setSourceTypePurpose(Source source, int purpose) {
        if (mContentResolver == null) {
            return false;
        }
        if (source == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.TVP_ATTRIBUTE_SOURCE_TYPE_SOURCE_PURPOSE[0], source.name);
        contentValues.put(SkyTvCommand.TVP_ATTRIBUTE_SOURCE_TYPE_SOURCE_PURPOSE[1], source.index);
        contentValues.put(SkyTvCommand.TVP_ATTRIBUTE_SOURCE_TYPE_SOURCE_PURPOSE[2], source.displayName);
        if(source.id!=null){
            contentValues.put(SkyTvCommand.TVP_ATTRIBUTE_SOURCE_TYPE_SOURCE_PURPOSE[3], source.id);
        }
        contentValues.put(SkyTvCommand.TVP_ATTRIBUTE_SOURCE_TYPE_SOURCE_PURPOSE[4], purpose);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.TVP_URI_PATH_SOURCE_TYPE_PURPOSE), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int getSourceTypePurpose(Source source) {
        if (mContentResolver == null) {
            return 0;
        }
        if (source == null) {
            return 0;
        }
        String selection = "sourceName=? and sourceIndex=?";
        String[] selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_SOURCE_TYPE_PURPOSE), SkyTvCommand.TVP_ATTRIBUTE_SOURCE_TYPE_PURPOSE, selection, selectionArgs, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return 0;
        }
        int purpose = 0;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_SOURCE_TYPE_PURPOSE[0]);
            if (index >= 0) {
                purpose = cursor.getInt(index) == 0 ? 0 : 1;
            }
        }
        cursor.close();
        return purpose;
    }

    @Override
    public boolean setSourceTypeName(Source source, String name) {
        try {
            SkySystemProperties.setProperty(getNameProp(source), name);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getSourceTypeName(Source source) {
        String sourceTypeName = "";
        try {
            String sourceTypeNameTmp = SkySystemProperties.getProperty(getNameProp(source));
            if (!TextUtils.isEmpty(sourceTypeNameTmp)) {
                sourceTypeName = sourceTypeNameTmp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceTypeName;
    }
    private String getNameProp(Source source) {
        return "persist.name." + source.id;
    }

    @Override
    public boolean setDisPlayerQuickKey(boolean isBack, int key) {
        try {
            if (isBack){
                SkySystemProperties.setProperty("persist.shortcut.back", key+"");
                return true;
            }else{
                SkySystemProperties.setProperty("persist.shortcut.remotecontrol", key+"");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public int getDisPlayerQuickKey(boolean isBack) {
        String index = null;
        try {
            if (isBack){
                index = SkySystemProperties.getProperty("persist.shortcut.back");
            }else{
                index = SkySystemProperties.getProperty("persist.shortcut.remotecontrol");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(index)?0:Integer.parseInt(index);
    }

    @Override
    public int[] getDisplayModes(Source source) {
        if (mContentResolver == null) {
            return null;
        }
        if (source == null) {
            return null;
        }
        String selection = "sourceName=? and sourceIndex=?";
        String[] selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_SOURCE_TYPE_DISPLAY_MODE_LIST), SkyTvCommand.TVP_ATTRIBUTE_DISPLAY_MODE, selection, selectionArgs, null);
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
        int count = cursor.getCount();
        int[] list = new int[count];
        int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_DISPLAY_MODE[0]);
        if (index >= 0) {
            for (int i = 0; i < count; i++) {
                list[i] = cursor.getInt(index);
                if (cursor.moveToNext() == false) {
                    break;
                }
            }
        }
        cursor.close();
        return list;
    }


    @Override
    public int getDisplayMode() {
        if (mContentResolver == null) {
            return SkyTvDefine.DISPLAY_MODE_AUTO;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_COMMON_DISPLAY_MODE), SkyTvCommand.TVP_ATTRIBUTE_DISPLAY_MODE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return SkyTvDefine.DISPLAY_MODE_AUTO;
        }
        int mode = SkyTvDefine.DISPLAY_MODE_AUTO;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_DISPLAY_MODE[0]);
            if (index >= 0) {
                mode = cursor.getInt(index);
            }
        }
        cursor.close();
        return mode;
    }

    @Override
    public boolean setDisplayMode(int mode) {
        if (mContentResolver == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.TVP_ATTRIBUTE_DISPLAY_MODE[0], mode);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.TVP_URI_PATH_COMMON_DISPLAY_MODE), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean sourceRename(Source source,String newName){
        if (mContentResolver == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.ATTRIBUTE_SOURCE_RENAME[0], source.name);
        contentValues.put(SkyTvCommand.ATTRIBUTE_SOURCE_RENAME[1], source.index);
        contentValues.put(SkyTvCommand.ATTRIBUTE_SOURCE_RENAME[2], newName);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.TVP_URI_SETTING_SOURCE_RENAME), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getSourceRename(Source source){
        if (mContentResolver == null) {
            return null;
        }
        if (source == null) {
            return null;
        }
        String selection = "sourceName=? and sourceIndex=?";
        String[] selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_SETTING_SOURCE_RENAME), SkyTvCommand.ATTRIBUTE_SOURCE_RENAME, selection, selectionArgs, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        String value = null;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_SOURCE_RENAME[2]);
            if (index >= 0){
                value = cursor.getString(index);
            }
        }
        cursor.close();
        return value;
    }
}
