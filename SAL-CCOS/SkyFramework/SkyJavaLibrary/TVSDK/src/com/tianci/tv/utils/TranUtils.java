package com.tianci.tv.utils;

import android.database.Cursor;
import android.text.TextUtils;

import com.skyworth.framework.skysdk.ipc.SkyContext;
import com.tianci.tv.define.SkyTvDefine;
import com.tianci.tv.define.object.CECDeviceInfo;
import com.tianci.tv.define.object.CaCardInfo;
import com.tianci.tv.define.object.CaEntitle;
import com.tianci.tv.define.object.CaOperator;
import com.tianci.tv.define.object.ChannelInfo;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.define.object.SourceInfo;

/**
 * Created by hq on 2017/11/28.
 */

public class TranUtils {
    public static CaCardInfo tranCursorToCardInfo(Cursor cursor, int[] index_list) {
        int cardType = CaCardInfo.CA_TYPE_UNKNOWN;
        if (index_list[0] >= 0) {
            cardType = cursor.getInt(index_list[0]);
        }
        int cardStatus = CaCardInfo.CA_STATUS_UNKNOWN;
        if (index_list[1] >= 0) {
            cardStatus = cursor.getInt(index_list[1]);
        }
        String cardNumber = "";
        if (index_list[2] >= 0) {
            cardNumber = cursor.getString(index_list[2]);
        }
        CaCardInfo value = new CaCardInfo();
        value.cardType = cardType;
        value.cardStatus = cardStatus;
        value.cardNumber = cardNumber;
        return value;
    }

    public static SkyTvDefine.SOURCE_SIGNAL_STATE tranSignalStateToSDK(int value)
    {
        SkyTvDefine.SOURCE_SIGNAL_STATE sdkValue = SkyTvDefine.SOURCE_SIGNAL_STATE.NOSIGNAL;
        if (value == SkyTvDefine.STATE_SIGNALED || value == SkyTvDefine.STATE_UNSUPPORT) {
            sdkValue = SkyTvDefine.SOURCE_SIGNAL_STATE.PLAY;
        }
        return sdkValue;
    }

    public static Source tranCursorToSource(Cursor cursor, int[] index_list) {
        String name = null;
        if (index_list[0] >= 0) {
            name = cursor.getString(index_list[0]);
        }
        int index = Source.DEFAULT_INDEX;
        if (index_list[1] >= 0) {
            index = cursor.getInt(index_list[1]);
        }
        String displayName = name;
        if (index_list[2] >= 0) {
            displayName = cursor.getString(index_list[2]);
        }
        SkyTvDefine.SOURCE_SIGNAL_STATE signalState = SkyTvDefine.SOURCE_SIGNAL_STATE.NOSIGNAL;
        if (index_list[3] >= 0) {
            signalState = tranSignalStateToSDK(cursor.getInt(index_list[3]));
        }
        String sourceId = null;
        if (index_list[4] >= 0) {
            sourceId = cursor.getString(index_list[4]);
        }
        Source source = new Source(name, index);
        if (source == null) {
            return null;
        }
        source.displayName = displayName;
        source.signalState = signalState;
        if(sourceId!=null){
            source.id = sourceId;
        }
        if ("HDMI".equals(source.name)){
            String addonDisplayName = AddonTextUtils.getInstance(SkyContext.context).getText("SKY_CFG_TV_SOURCE_HDMI" + (source.index+1));
            if (!TextUtils.isEmpty(addonDisplayName)){
                int kIndex = addonDisplayName.indexOf("K");
                int dotIndex = addonDisplayName.indexOf(".");
                source.bHDMIResolution = kIndex == -1 ? ""
                        : addonDisplayName.substring(kIndex-1,kIndex+1);
                source.isHDMISupportARC =  addonDisplayName.contains("ARC");
                source.bHDMIVersionName = dotIndex == -1 ? ""
                        : addonDisplayName.substring(dotIndex-1,dotIndex+2);
            }
        }
        return source;
    }

    public static SourceInfo tranCursorToSourceInfo(Cursor cursor, int[] index_list) {
        String name = null;
        if (index_list[0] >= 0) {
            name = cursor.getString(index_list[0]);
        }
        int index = Source.DEFAULT_INDEX;
        if (index_list[1] >= 0) {
            index = cursor.getInt(index_list[1]);
        }
        String displayName = name;
        if (index_list[2] >= 0) {
            displayName = cursor.getString(index_list[2]);
        }
       int signalState = SkyTvDefine.STATE_NOSIGNAL;
        if (index_list[3] >= 0) {
            signalState = cursor.getInt(index_list[3]);
        }
        SourceInfo sourceInfo = new SourceInfo(name, index);
        if (sourceInfo == null) {
            return null;
        }
        sourceInfo.displayName = displayName;
        sourceInfo.signalState = signalState;
        return sourceInfo;
    }

    public static Source tranCursorToBootSource(Cursor cursor, int[] index_list) {
        String name = null;
        if (index_list[0] >= 0) {
            name = cursor.getString(index_list[0]);
        }
        int index = Source.DEFAULT_INDEX;
        if (index_list[1] >= 0) {
            index = cursor.getInt(index_list[1]);
        }
        String displayName = name;
        if (index_list[2] >= 0) {
            displayName = cursor.getString(index_list[2]);
        }
        String sourceId = null;
        if (index_list[3] >= 0) {
            sourceId = cursor.getString(index_list[3]);
        }
        Source source = new Source(name, index);
        if (source == null) {
            return null;
        }
        source.displayName = displayName;
        if(sourceId!=null){
            source.id = sourceId;
        }
        return source;
    }

    public static ChannelInfo tranCursorToChannelInfo(Cursor cursor, int[] index_list) {
        int id = ChannelInfo.INVALID_ID;
        if (index_list[0] >= 0) {
            id = cursor.getInt(index_list[0]);
        }
        String name = null;
        if (index_list[1] >= 0) {
            name = cursor.getString(index_list[1]);
        }
        int index = 0;
        if (index_list[2] >= 0) {
            index = cursor.getInt(index_list[2]);
        }
        int channelType = 0;
        if (index_list.length>=4 && index_list[3] >= 0) {
            channelType = cursor.getInt(index_list[3]);
        }
        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.id = id;
        channelInfo.name = name;
        channelInfo.index = index;
        channelInfo.channelType = channelType;
        return channelInfo;
    }

    public static CaOperator tranCursorToCaOperator(Cursor cursor, int[] index_list) {
        String id = null;
        if (index_list[0] >= 0) {
            id = cursor.getString(index_list[0]);
        }
        String name = null;
        if (index_list[1] >= 0) {
            name = cursor.getString(index_list[1]);
        }
        CaOperator caOperator = new CaOperator();
        caOperator.id = id;
        caOperator.name = name;
        return caOperator;
    }

    public static CaEntitle tranCursorToCaEntitle(Cursor cursor, int[] index_list) {
        String id = null;
        if (index_list[0] >= 0) {
            id = cursor.getString(index_list[0]);
        }
        String name = null;
        if (index_list[1] >= 0) {
            name = cursor.getString(index_list[1]);
        }
        String startTime = null;
        if (index_list[2] >= 0) {
            startTime = cursor.getString(index_list[2]);
        }
        String endTime = null;
        if (index_list[3] >= 0) {
            endTime = cursor.getString(index_list[3]);
        }
        String canTape = null;
        if (index_list[4] >= 0) {
            canTape = cursor.getString(index_list[4]);
        }
        CaEntitle caEntitle = new CaEntitle();
        caEntitle.id = id;
        caEntitle.name = name;
        caEntitle.startTime = startTime;
        caEntitle.endTime = endTime;
        caEntitle.canTape = canTape;
        return caEntitle;
    }

    public static CECDeviceInfo tranCursorToCECDeviceInfo(Cursor cursor, int[] index_list) {
        CECDeviceInfo info = new CECDeviceInfo();
        if (index_list[0] >= 0) {
            info.logAddr = cursor.getInt(index_list[0]);
        }
        if (index_list[1] >= 0) {
            info.hdmiDevPort = cursor.getInt(index_list[1]);
        }
        if (index_list[2] >= 0) {
            info.phyAddr = cursor.getString(index_list[2]);
        }
        if (index_list[3] >= 0) {
            info.devName = cursor.getString(index_list[3]);
        }
        if (index_list[4] >= 0) {
            info.hdmiListText = cursor.getString(index_list[4]);
        }
        if (index_list[5] >= 0) {
            info.vendorId = cursor.getInt(index_list[5]);
        }
        return info;
    }
}
