package com.tianci.tv.framework.implement.system;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;

import com.tianci.tv.define.SkyTvCommand;
import com.tianci.tv.define.SkyTvDefine;
import com.tianci.tv.define.object.CECDeviceInfo;
import com.tianci.tv.define.object.CaCardInfo;
import com.tianci.tv.define.object.CaEntitle;
import com.tianci.tv.define.object.CaOperator;
import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.define.object.SourceInfo;
import com.tianci.tv.framework.implement.system.listener.OnCACardInfoListener;
import com.tianci.tv.framework.implement.system.listener.OnCECDeviceChangedListener;
import com.tianci.tv.framework.implement.system.manager.contentprovider.SystemMessageManager;
import com.tianci.tv.utils.SkyTvUtils;
import com.tianci.tv.utils.TVSDKDebug;
import com.tianci.tv.utils.TranUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hq on 2017/12/2.
 */

public class ContentProviderSystemAPIImpl extends SystemAPI {
    private Context mContext;
    private ContentResolver mContentResolver;
    private static SystemMessageManager mSystemMessageManager;
    private String processName;
    private boolean isOldTv= false;
    private boolean isC9OrUpper = false;
    private boolean isSkyTVProviderExist;

    public ContentProviderSystemAPIImpl(Context context, boolean isOldTv) {
        if (context == null) {
            return;
        }
        mContext = context;
        mContentResolver = context.getContentResolver();
        mSystemMessageManager = SystemMessageManager.getInstance(context);
        this.isOldTv = isOldTv;
        if (isOldTv) {
            processName = "com.tianci.tv";
        } else {
            processName = "com.skyworth.tv";
        }
        isC9OrUpper = SkyTvUtils.getSystemVersion().startsWith("9");
        isSkyTVProviderExist = SkyTvUtils.isAppInstalled(context, "com.skyworth.tv.provider");
        TVSDKDebug.error("is SkyTVProviderExist:" + isSkyTVProviderExist+"  isC9OrUpper:"+isC9OrUpper+
                "  isOldTv:"+isOldTv);
    }

    @Override
    public int getApiLevel() {
        if (mContentResolver == null) {
            return 0;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_API_LEVEL), SkyTvCommand.ATTRIBUTE_API_LEVEL, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return 0;
        }
        int value = 0;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_API_LEVEL[0]);
            if (index >= 0) {
                value = cursor.getInt(index);
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public int switchChannel(ChannelInfo channelInfo) {
        if (mContentResolver == null) {
            return SkyTvDefine.SWITCH_CHANNEL_PARAM_ERROR;
        }
        if (channelInfo == null) {
            return SkyTvDefine.SWITCH_CHANNEL_PARAM_ERROR;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("switchChannelType", channelInfo.switchChannelType);
        bundle.putInt("id", channelInfo.id);
        bundle.putInt("index", channelInfo.index);
        bundle.putString("name", channelInfo.name);
        bundle.putInt("channelType", channelInfo.channelType);
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_SWITCH_CHANNEL, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return SkyTvDefine.SWITCH_CHANNEL_EXCEPTION;
        }
        return ret.getInt("ret");
    }

    @Override
    public boolean setOnCaCardInfoListener(OnCACardInfoListener listener) {
        return mSystemMessageManager.setOnCaCardInfoListener(listener);
    }

    @Override
    public CaCardInfo getCaCardInfo() {
        if (mContentResolver == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_COMMON_CARD_INFO), SkyTvCommand.TVP_ATTRIBUTE_CARD_INFO, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        CaCardInfo value = null;
        if (cursor.moveToFirst()) {
            int length = SkyTvCommand.TVP_ATTRIBUTE_CARD_INFO.length;
            int[] index_list = new int[length];
            for (int i = 0; i < length; i++) {
                index_list[i] = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_CARD_INFO[i]);
            }
            value = TranUtils.tranCursorToCardInfo(cursor, index_list);
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean switchSource(Source source) {
        if (mContentResolver == null) {
            return false;
        }
        if (source == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", source.name);
        bundle.putInt("index", source.index);
        bundle.putInt("flag", source.flag);
        if(source.id != null){
            bundle.putString("sourceId", source.id);
        }
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_GOTO_SOURCE, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    @Override
    public boolean switchSourceBackGround(Source source) {
        if (mContentResolver == null) {
            return false;
        }
        if (source == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", source.name);
        bundle.putInt("index", source.index);
        if(source.id!=null){
            bundle.putString("sourceId", source.id);
        }
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_SWITCH_SOURCE, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    @Override
    public boolean holdSource(SourceInfo sourceInfo, ChannelInfo channelInfo, Surface surface
            , int x, int y, int w, int h, int flag) {
        if (mContentResolver == null) {
            return false;
        }
        if (sourceInfo == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("sourceName", sourceInfo.name);
        bundle.putInt("sourceIndex", sourceInfo.index);
        if (channelInfo != null) {
            bundle.putInt("channelID", channelInfo.id);
            bundle.putInt("channelIndex", channelInfo.index);
            bundle.putString("channelName", channelInfo.name);
        }
        bundle.putParcelable("surface", surface);
        bundle.putString("packageFrom", mContext.getPackageName());
        if(x != -1 && y != -1) {
            bundle.putInt("x",x);
            bundle.putInt("y",y);
            bundle.putInt("w",w);
            bundle.putInt("h",h);
            bundle.putInt("flag",flag);
        }
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_HOLD_SOURCE, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    @Override
    public boolean releaseSource(SourceInfo sourceInfo) {
        if (mContentResolver == null) {
            return false;
        }
        if (sourceInfo == null) {
            return false;
        }
        String uriStr = SkyTvCommand.URI_PATH_METHOD;
        if (!SkyTvUtils.checkProcessIsRunning(mContext, processName)) {
            if((isOldTv && !isC9OrUpper)||isSkyTVProviderExist)
                return true;
             else
                uriStr = SkyTvCommand.TVP_PATH_METHOD;
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", sourceInfo.name);
        bundle.putInt("index", sourceInfo.index);
        bundle.putString("packageFrom", mContext.getPackageName());
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(uriStr), SkyTvCommand.METHOD_RELEASE_SOURCE, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    @Override
    public boolean forceReleaseSource() {
        if (mContentResolver == null) {
            return false;
        }
        String uriStr = SkyTvCommand.URI_PATH_METHOD;
        if (!SkyTvUtils.checkProcessIsRunning(mContext, processName)) {
            if((isOldTv && !isC9OrUpper)||isSkyTVProviderExist)
                return true;
            else
                uriStr = SkyTvCommand.TVP_PATH_METHOD;
        }
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(uriStr), SkyTvCommand.METHOD_FORCE_RELEASE_SOURCE, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    @Override
    public Source getCurrentSource() {
        if (mContentResolver == null) {
            return null;
        }
        String uriStr;
        if((isOldTv && !isC9OrUpper)||isSkyTVProviderExist){
            uriStr = SkyTvCommand.URI_PATH_SYSTEM_CURRENT_SOURCE;
        }else {
            uriStr = SkyTvCommand.TVP_URI_PATH_SYSTEM_CURRENT_SOURCE;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(uriStr), SkyTvCommand.ATTRIBUTE_SOURCE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        Source value = null;
        if (cursor.moveToFirst()) {
            int length = SkyTvCommand.ATTRIBUTE_SOURCE.length;
            int[] index_list = new int[length];
            for (int i = 0; i < length; i++) {
                index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_SOURCE[i]);
            }
            value = TranUtils.tranCursorToSource(cursor, index_list);
        }
        cursor.close();
        return value;
    }

    @Override
    public SourceInfo getRealSource() {
        if (mContentResolver == null) {
            return null;
        }
        String uriStr;
//        if((isOldTv && !isC9OrUpper)||isSkyTVProviderExist){
//            uriStr = SkyTvCommand.URI_PATH_SYSTEM_REAL_SOURCE;
//        }else {
//            uriStr = SkyTvCommand.TVP_URI_PATH_SYSTEM_REAL_SOURCE;
//        }
        uriStr = SkyTvCommand.URI_PATH_SYSTEM_REAL_SOURCE;
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(uriStr), SkyTvCommand.ATTRIBUTE_SOURCE_INFO, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        SourceInfo value = null;
        if (cursor.moveToFirst()) {
            int length = SkyTvCommand.ATTRIBUTE_SOURCE_INFO.length;
            int[] index_list = new int[length];
            for (int i = 0; i < length; i++) {
                index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_SOURCE_INFO[i]);
            }
            value = TranUtils.tranCursorToSourceInfo(cursor, index_list);
        }
        cursor.close();
        return value;
    }

    @Override
    public List<Source> getSourceList() {
        if (mContentResolver == null) {
            return null;
        }
        String uriStr;
        if((isOldTv && !isC9OrUpper)||isSkyTVProviderExist){
            uriStr = SkyTvCommand.URI_PATH_SYSTEM_SOURCE_LIST;
        }else {
            uriStr = SkyTvCommand.TVP_URI_PATH_SYSTEM_SOURCE_LIST;
        }

        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(uriStr), SkyTvCommand.ATTRIBUTE_SOURCE, null, null, null);
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
        int length = SkyTvCommand.ATTRIBUTE_SOURCE.length;
        int[] index_list = new int[length];
        for (int i = 0; i < length; i++) {
            index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_SOURCE[i]);
        }
        int count = cursor.getCount();
        List<Source> list = new ArrayList<Source>(count);
        for (int i = 0; i < count; i++) {
            Source source = TranUtils.tranCursorToSource(cursor, index_list);
            list.add(source);
            if (cursor.moveToNext() == false) {
                break;
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public String getSourceResolution(Source source){
        if (mContentResolver == null) {
            return null;
        }
        if (source == null){
            return null;
        }
        if(!isOldTv && isSkyTVProviderExist){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return null;
            }
        }
        String selection = "sourceName=? and sourceIndex=?";
        String[] selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_SOURCE_RESOLUTION), SkyTvCommand.ATTRIBUTE_SOURCE_RESOLUTION, selection, selectionArgs, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        String value = "";
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_SOURCE_RESOLUTION[0]);
            if (index >= 0) {
                value = cursor.getString(index);
            }
        }
        cursor.close();
        return value;
    }
    @Override
    public boolean isReady() {
        if (mContentResolver == null) {
            return false;
        }
        if(!isOldTv  && !isSkyTVProviderExist)
            return true;
        if(isOldTv && isC9OrUpper){
            return true;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_IS_READY), SkyTvCommand.ATTRIBUTE_IS_READY, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_READY[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean isReleased() {
        if (mContentResolver == null) {
            return true;
        }
        String uriStr = SkyTvCommand.URI_PATH_SYSTEM_IS_RELEASED;
        if (!SkyTvUtils.checkProcessIsRunning(mContext, processName)) {
            if((isOldTv && !isC9OrUpper) ||isSkyTVProviderExist)
                 return true;
            else
                uriStr = SkyTvCommand.TVP_URI_PATH_SYSTEM_IS_RELEASED;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(uriStr), SkyTvCommand.ATTRIBUTE_IS_RELEASED, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_RELEASED[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean releaseAllWithStandby() {
        if (mContentResolver == null) {
            return false;
        }
        if(!isOldTv && !isSkyTVProviderExist){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return false;
            }
        }
        if(isOldTv && isC9OrUpper){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return false;
            }
        }
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_RELEASE_ALL, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    @Override
    public boolean backToTv() {
        if (mContentResolver == null) {
            return false;
        }
        if(!isOldTv && !isSkyTVProviderExist){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return false;
            }
        }
        if(isOldTv && isC9OrUpper){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return false;
            }
        }
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_BACK_TO_TV, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    @Override
    public boolean backToTvSource() {
        if (mContentResolver == null) {
            return false;
        }
        if(!isOldTv && !isSkyTVProviderExist){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return false;
            }
        }
        if(isOldTv && isC9OrUpper){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return false;
            }
        }
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_BACK_TO_TV_SOURCE, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    @Override
    public boolean canCaptureScreen() {
        if (mContentResolver == null || !SkyTvUtils.checkProcessIsRunning(mContext, processName)) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_CAN_SCREEN_CAPTURE), SkyTvCommand.ATTRIBUTE_CAN_SCREEN_CAPTURE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_CAN_SCREEN_CAPTURE[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean setNeedShowBootGuide(boolean setValue) {
        if (mContentResolver == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.ATTRIBUTE_SHOW_BOOT_GUIDE[0], setValue);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_SHOW_BOOT_GUIDE), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int setTimeUpdateFlag(int flag) {
        if (mContentResolver == null) {
            return 0;
        }
        if(!isOldTv && !isSkyTVProviderExist){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return 0;
            }
        }
        if(isOldTv && isC9OrUpper){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return 0;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("flag", flag);
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_SET_TIME_FLAG, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return 0;
        }
        return ret.getInt("ret");
    }

    @Override
    public boolean isTvVideoFocused() {
        if (mContentResolver == null || !SkyTvUtils.checkProcessIsRunning(mContext, processName)) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_HAS_OSD), SkyTvCommand.ATTRIBUTE_HAS_OSD, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_HAS_OSD[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public SkyTvDefine.SOURCE_SIGNAL_STATE signalState(Source source) {
        if (mContentResolver == null) {
            return SkyTvDefine.SOURCE_SIGNAL_STATE.NOSIGNAL;
        }
        String uriStr = SkyTvCommand.URI_PATH_SYSTEM_SIGNAL_STATE;
        if(!isOldTv && !isSkyTVProviderExist){
            if (!SkyTvUtils.checkProcessIsRunning(mContext, processName)) {

                uriStr = SkyTvCommand.TVP_URI_PATH_SYSTEM_SIGNAL_STATE;
            }
        }
        if(isOldTv && isC9OrUpper){
            if (!SkyTvUtils.checkProcessIsRunning(mContext, processName)) {

                uriStr = SkyTvCommand.TVP_URI_PATH_SYSTEM_SIGNAL_STATE;
            }
        }
        String selection = "sourceName=? and sourceIndex=?";
        String[] selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        if(source.id!=null){
            selection = "sourceName=? and sourceIndex=? and sourceId=?";
            selectionArgs = new String[]{source.name, String.valueOf(source.index),source.id};
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(uriStr), SkyTvCommand.ATTRIBUTE_SIGNAL_STATE, selection, selectionArgs, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return SkyTvDefine.SOURCE_SIGNAL_STATE.NOSIGNAL;
        }
        SkyTvDefine.SOURCE_SIGNAL_STATE value = SkyTvDefine.SOURCE_SIGNAL_STATE.NOSIGNAL;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_SIGNAL_STATE[0]);
            if (index >= 0) {
                int status = cursor.getInt(index);
                if (status == SkyTvDefine.STATE_SIGNALED || status == SkyTvDefine.STATE_UNSUPPORT) {
                    value = SkyTvDefine.SOURCE_SIGNAL_STATE.PLAY;
                }
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean canPopupInteractionNow() {
        if (mContentResolver == null || !SkyTvUtils.checkProcessIsRunning(mContext, processName)) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_CAN_INTERACTION), SkyTvCommand.ATTRIBUTE_CAN_INTERACTION, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_CAN_INTERACTION[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean isInteractionEnable() {
        if (mContentResolver == null || !SkyTvUtils.checkProcessIsRunning(mContext, processName)) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_INTERACTION_ENABLE), SkyTvCommand.ATTRIBUTE_INTERACTION_ENABLE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_INTERACTION_ENABLE[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean isSearchingChannel() {
        if (mContentResolver == null || !SkyTvUtils.checkProcessIsRunning(mContext, processName)) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_IS_SEARCHING), SkyTvCommand.ATTRIBUTE_IS_SEARCHING, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_SEARCHING[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean canSetWindowSize() {
        if (mContentResolver == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_COMMON_CAN_SET_WINDOW_SIZE), SkyTvCommand.TVP_ATTRIBUTE_CAN_SET_WINDOW_SIZE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_CAN_SET_WINDOW_SIZE[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }
    
    @Override
    public int setWindowSize(int x, int y, int w, int h, int flag) {
        if(mContentResolver == null) {
            return SkyTvDefine.SET_WINDOW_SIZE_EXCEPTION;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("x",x);
        bundle.putInt("y",y);
        bundle.putInt("w",w);
        bundle.putInt("h",h);
        bundle.putInt("flag",flag);
        Bundle ret=null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD),SkyTvCommand.METHOD_SET_WINDOW_SIZE,null,bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return SkyTvDefine.SET_WINDOW_SIZE_EXCEPTION;
        }
        return ret.getInt("ret");
    }

//    @Override
//    public void setStandbyReason(int reason) {
//        if (mContentResolver == null) {
//            return;
//        }
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SkyTvCommand.ATTRIBUTE_STANDBY_REASON[0], reason);
//        try {
//            mContentResolver.update(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_STANDBY_REASON), contentValues, null, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void setStandbyReason(int reason) {
        if (mContentResolver == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.ATTRIBUTE_STANDBY_REASON[0], reason);
        String uriStr;
        if((isOldTv && !isC9OrUpper)||isSkyTVProviderExist||SkyTvUtils.checkProcessIsRunning(mContext, processName)){
            uriStr = SkyTvCommand.URI_PATH_SYSTEM_STANDBY_REASON;
        }else {
            uriStr = SkyTvCommand.TVP_URI_PATH_SYSTEM_STANDBY_REASON;
        }
        try {
            mContentResolver.update(Uri.parse(uriStr), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Source> getExternalSourceList() {
        if (mContentResolver == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_EXTERNAL_SOURCE_LIST), SkyTvCommand.ATTRIBUTE_SOURCE, null, null, null);
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
        int length = SkyTvCommand.ATTRIBUTE_SOURCE.length;
        int[] index_list = new int[length];
        for (int i = 0; i < length; i++) {
            index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_SOURCE[i]);
        }
        int count = cursor.getCount();
        List<Source> list = new ArrayList<Source>(count);
        for (int i = 0; i < count; i++) {
            Source source = TranUtils.tranCursorToSource(cursor, index_list);
            list.add(source);
            if (cursor.moveToNext() == false) {
                break;
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public boolean enableExternalSource(Source source, boolean val) {
        if (mContentResolver == null) {
            return false;
        }
        String selection;
        String[] selectionArgs;
        if(source.id != null) {
            selection = "sourceName=? and sourceIndex=? and sourceId=?";
            selectionArgs = new String[]{source.name, String.valueOf(source.index), source.id};
        } else {
            selection = "sourceName=? and sourceIndex=?";
            selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.ATTRIBUTE_IS_ENABLE_EXTERNAL_SOURCE[0], val);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_IS_ENABLE_EXTERNAL_SOURCE), contentValues, selection, selectionArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean getExternalSourceEnable(Source source) {
        if (mContentResolver == null) {
            return false;
        }
        String selection;
        String[] selectionArgs;
        if(source.id != null) {
            selection = "sourceName=? and sourceIndex=? and sourceId=?";
            selectionArgs = new String[]{source.name, String.valueOf(source.index), source.id};
        } else {
            selection = "sourceName=? and sourceIndex=?";
            selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_IS_ENABLE_EXTERNAL_SOURCE), SkyTvCommand.ATTRIBUTE_IS_ENABLE_EXTERNAL_SOURCE, selection, selectionArgs, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_ENABLE_EXTERNAL_SOURCE[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean isOfflineCheckSupported(){
        if (mContentResolver == null) {
            return true;
        }
        String uriStr;
        if((isOldTv &!isC9OrUpper)||isSkyTVProviderExist){
            uriStr = SkyTvCommand.URI_PATH_SYSTEM_IS_OFFLINE_CHECK_SUPPORTED;
        }else {
            uriStr = SkyTvCommand.TVP_URI_PATH_SYSTEM_IS_OFFLINE_CHECK_SUPPORTED;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(uriStr), SkyTvCommand.ATTRIBUTE_IS_OFFLINE_CHECK_SUPPORTED, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return true;
        }
        boolean value = true;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_OFFLINE_CHECK_SUPPORTED[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean isEdidBySourceSupported(){
        if (mContentResolver == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_HDMI_EDID_SUPPORTED), SkyTvCommand.TVP_ATTRIBUTE_EDID_SUPPORTED, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_EDID_SUPPORTED[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    @Override
    public List<CaOperator> getCaOperatorList() {
        if (mContentResolver == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_CA_OPERATOR_LIST), SkyTvCommand.ATTRIBUTE_CA_OPERATOR, null, null, null);
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
        int length = SkyTvCommand.ATTRIBUTE_CA_OPERATOR.length;
        int[] index_list = new int[length];
        for (int i = 0; i < length; i++) {
            index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_CA_OPERATOR[i]);
        }
        int count = cursor.getCount();
        List<CaOperator> list = new ArrayList<CaOperator>(count);
        for (int i = 0; i < count; i++) {
            CaOperator value = TranUtils.tranCursorToCaOperator(cursor, index_list);
            list.add(value);
            if (cursor.moveToNext() == false) {
                break;
            }
        }
        cursor.close();
        return list;
    }

    @Override
    public List<CaEntitle> getCaEntitleList(String id) {
        if (mContentResolver == null) {
            return null;
        }
        if (id == null) {
            return null;
        }
        String selection = "id=?";
        String[] selectionArgs = new String[]{id};
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_CA_ENTITLE_LIST), SkyTvCommand.ATTRIBUTE_CA_ENTITLE, selection, selectionArgs, null);
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
        int length = SkyTvCommand.ATTRIBUTE_CA_ENTITLE.length;
        int[] index_list = new int[length];
        for (int i = 0; i < length; i++) {
            index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_CA_ENTITLE[i]);
        }
        int count = cursor.getCount();
        List<CaEntitle> list = new ArrayList<CaEntitle>(count);
        for (int i = 0; i < count; i++) {
            CaEntitle value = TranUtils.tranCursorToCaEntitle(cursor, index_list);
            list.add(value);
            if (cursor.moveToNext() == false) {
                break;
            }
        }
        cursor.close();
        return list;
    }

    public boolean sendMessage(int what, int arg1, int arg2, Bundle object) {
        if(mContentResolver == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("what", what);
        bundle.putInt("arg1", arg1);
        bundle.putInt("arg2", arg2);
        bundle.putBundle("object", object);
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_SEND_MESSAGE, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    public boolean getChannelSeachOrEditEnable() {
        if (mContentResolver == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_CHANNEL_SERACH_EDIT), SkyTvCommand.ATTRIBUTE_CHANNEL_SERACH_EDIT, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean value = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_CHANNEL_SERACH_EDIT[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }

    public void setChannelSeachOrEditEnable(boolean enable) {
        if (mContentResolver == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.ATTRIBUTE_CHANNEL_SERACH_EDIT[0], enable);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_CHANNEL_SERACH_EDIT), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean setAntennaVoltage(boolean mode) {
        if (mContentResolver == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.TVP_ATTRIBUTE_ANTENNA_VOLTAGE[0], mode);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.TVP_URI_PATH_COMMON_ANTENNA_VOLTAGE), contentValues, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean getAntennaVoltage() {
        if (mContentResolver == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_COMMON_ANTENNA_VOLTAGE), SkyTvCommand.TVP_ATTRIBUTE_ANTENNA_VOLTAGE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean status = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_ANTENNA_VOLTAGE[0]);
            if (index >= 0) {
                status = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return status;
    }

    @Override
    public String getResolution(){
        if (mContentResolver == null) {
            return null;
        }
        if(!isOldTv && !isSkyTVProviderExist){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return null;
            }
        }
        if(isOldTv && isC9OrUpper){
            if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
                return null;
            }
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_RESOLUTION), SkyTvCommand.ATTRIBUTE_RESOLUTION, null, null, null);
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
        String resolution = "";
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_RESOLUTION[0]);
            if (index >= 0) {
                resolution = cursor.getString(index);
            }
        }
        cursor.close();
        return resolution;
    }

    @Override
    public boolean isMEMCValid(){
        if (mContentResolver == null || !SkyTvUtils.checkProcessIsRunning(mContext, processName)) {
            return true;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_IS_MEMC_VALID), SkyTvCommand.ATTRIBUTE_IS_MEMC_VALID, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return true;
        }
        boolean value = true;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_MEMC_VALID[0]);
            if (index >= 0) {
                value = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return value;
    }


    @Override
    public boolean isTVForeground() {
        if (mContentResolver == null) {
            return false;
        }
        if(!SkyTvUtils.checkProcessIsRunning(mContext, processName)){
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_IS_TV_FOREGROUND), SkyTvCommand.ATTRIBUTE_IS_TV_FOREGROUND, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        if (cursor.moveToFirst() == false) {
            cursor.close();
            return false;
        }
        boolean isForeground = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_IS_TV_FOREGROUND[0]);
            if (index >= 0) {
                isForeground = cursor.getInt(index) == 1;
            }
        }
        cursor.close();
        return isForeground;
    }

    /**
     * 概述：获取FreeSync开关<br/>
     * 适用条件：getTVProviderVersion() >= 0x00007 <br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean true:开 false:关
     * @date 2019-09-26
     */
    public boolean getFreeSyncStatus(Source source) {
        if (mContentResolver == null) {
            return false;
        }
        String selection = "sourceName=? and sourceIndex=?";
        String[] selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_HDMI_FREESYNC_STATUS), SkyTvCommand.TVP_ATTRIBUTE_FREESYNC_STATUS, selection, selectionArgs, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean status = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_FREESYNC_STATUS[0]);
            if (index >= 0) {
                status = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return status;
    }

    /**
     * 概述：设置FreeSync开关<br/>
     * 适用条件：getTVProviderVersion() >= 0x00007 <br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean true:成功 false:失败
     * @date 2019-09-26
     */
    public boolean setFreeSyncStatus(Source source, boolean status) {
        if (mContentResolver == null) {
            return false;
        }
        String where = "sourceName=? and sourceIndex=?";
        String[] selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.TVP_ATTRIBUTE_FREESYNC_STATUS[0], status);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.TVP_URI_PATH_HDMI_FREESYNC_STATUS), contentValues, where, selectionArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 概述：是否支持FreeSync<br/>
     * 适用条件：getTVProviderVersion() >= 0x00007 <br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @return boolean true:支持 false:不支持
     * @date 2019-09-26
     */
    public boolean isFreeSyncSupported() {
        if (mContentResolver == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_HDMI_FREESYNC_SUPPORTED), SkyTvCommand.TVP_ATTRIBUTE_FREESYNC_SUPPORTED, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return false;
        }
        boolean status = false;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_FREESYNC_SUPPORTED[0]);
            if (index >= 0) {
                status = cursor.getInt(index) == 0 ? false : true;
            }
        }
        cursor.close();
        return status;
    }
	
	 @Override
    public boolean startSplitScreenTV(int x, int y, int w, int h, int flag) {
        if (this.mContentResolver == null) {
            return false;
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt("x", x);
            bundle.putInt("y", y);
            bundle.putInt("w", w);
            bundle.putInt("h", h);
            bundle.putInt("flag", flag);
            Bundle ret = null;

            try {
                ret = this.mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_START_SPLIT_SCREEN_TV, (String)null, bundle);
            } catch (Exception var9) {
                var9.printStackTrace();
            }

            return ret == null ? false : ret.getBoolean("ret");
        }
    }

    @Override
    public boolean exitSplitScreenTV() {
        if (this.mContentResolver == null) {
            return false;
        } else {
            Bundle ret = null;

            try {
                ret = this.mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_EXIT_SPLIT_SCREEN_TV, (String)null, (Bundle)null);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

            return ret == null ? false : ret.getBoolean("ret");
        }
    }

    @Override
    public boolean reSizeSplitScreenTV(int x, int y, int w, int h, int flag) {
        if (this.mContentResolver == null) {
            return false;
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt("x", x);
            bundle.putInt("y", y);
            bundle.putInt("w", w);
            bundle.putInt("h", h);
            bundle.putInt("flag", flag);
            Bundle ret = null;

            try {
                ret = this.mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_RESIZE_SPLIT_SCREEN_TV, (String)null, bundle);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

            return ret == null ? false : ret.getBoolean("ret");
        }
    }

    @Override
    public int getEdid(Source source) {
        if (mContentResolver == null) {
            return SkyTvDefine.EDID_1P4_2K;
        }
        String selection = "sourceName=? and sourceIndex=?";
        String[] selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_HDMI_EDID), SkyTvCommand.TVP_ATTRIBUTE_EDID, selection, selectionArgs, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return SkyTvDefine.EDID_1P4_2K;
        }
        int mode = SkyTvDefine.EDID_1P4_2K;
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_EDID[0]);
            if (index >= 0) {
                mode = cursor.getInt(index);
            }
        }
        cursor.close();
        return mode;
    }

    @Override
    public boolean setEdid(Source source, int edid) {
        if (mContentResolver == null) {
            return false;
        }
        String where = "sourceName=? and sourceIndex=?";
        String[] selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        ContentValues contentValues = new ContentValues();
        contentValues.put(SkyTvCommand.TVP_ATTRIBUTE_EDID[0], edid);
        try {
            mContentResolver.update(Uri.parse(SkyTvCommand.TVP_URI_PATH_HDMI_EDID), contentValues, where, selectionArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int[] getSupportedEdidList(Source source) {
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
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_HDMI_EDID_LIST), SkyTvCommand.TVP_ATTRIBUTE_EDID, selection, selectionArgs, null);
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
        int index = cursor.getColumnIndex(SkyTvCommand.TVP_ATTRIBUTE_EDID[0]);
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
    public List<ChannelInfo> getChannelInfoList(Source source){
        if (mContentResolver == null) {
            return null;
        }
        String selection;
        String[] selectionArgs;
        if(source.id != null) {
            selection = "sourceName=? and sourceIndex=? and sourceId=?";
            selectionArgs = new String[]{source.name, String.valueOf(source.index), source.id};
        } else
        {
            selection = "sourceName=? and sourceIndex=?";
            selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.URI_PATH_SYSTEM_CHANNEL_LIST_BY_SOURCE), SkyTvCommand.ATTRIBUTE_CHANNEL, selection, selectionArgs, null);
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
    public boolean gotoSourceWithChannel(Source sourceInfo, ChannelInfo channelInfo){
        if (mContentResolver == null) {
            return false;
        }
        if (sourceInfo == null || channelInfo == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("sourceName", sourceInfo.name);
        bundle.putInt("sourceIndex", sourceInfo.index);
        bundle.putInt("channelID", channelInfo.id);
        bundle.putInt("channelIndex", channelInfo.index);
        bundle.putInt("channelType", channelInfo.channelType);
        bundle.putString("channelName", channelInfo.name);
        Bundle ret = null;
        try {
            ret = mContentResolver.call(Uri.parse(SkyTvCommand.URI_PATH_METHOD), SkyTvCommand.METHOD_GOTO_SOURCE_WITH_CHANNEL, null, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return false;
        }
        return ret.getBoolean("ret");
    }

    @Override
    public CECDeviceInfo getHDMICECDeviceInfo(Source source) {
        if (mContentResolver == null) {
            return null;
        }
        String selection;
        String[] selectionArgs;
        if(source.id != null) {
            selection = "sourceName=? and sourceIndex=? and sourceId=?";
            selectionArgs = new String[]{source.name, String.valueOf(source.index), source.id};
        } else
        {
            selection = "sourceName=? and sourceIndex=?";
            selectionArgs = new String[]{source.name, String.valueOf(source.index)};
        }
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(Uri.parse(SkyTvCommand.TVP_URI_PATH_SETTING_GET_CEC_DEVICE_INFO), SkyTvCommand.ATTRIBUTE_CEC_DEVICE_INFO, selection, selectionArgs, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            return null;
        }
        CECDeviceInfo value = null;
        if (cursor.moveToFirst()) {
            int length = SkyTvCommand.ATTRIBUTE_CEC_DEVICE_INFO.length;
            int[] index_list = new int[length];
            for (int i = 0; i < length; i++) {
                index_list[i] = cursor.getColumnIndex(SkyTvCommand.ATTRIBUTE_CEC_DEVICE_INFO[i]);
            }
            value = TranUtils.tranCursorToCECDeviceInfo(cursor, index_list);
        }
        cursor.close();
        return value;
    }

    @Override
    public boolean setOnDeviceChangedListener(OnCECDeviceChangedListener listener) {
        return mSystemMessageManager.setOnDeviceChangedListener(listener);
    }
}
