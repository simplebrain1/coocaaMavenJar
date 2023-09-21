/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-11-22         xingkong207
 */

package com.tianci.system.data;

import android.os.Bundle;

import com.tianci.system.data.TCSetData.SkyConfigType;
import com.tianci.utils.SkySSSLog;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 *
 * @ClassName TCSetDataFactory
 * @author wei li
 * @date 2013-11-22
 * @version V1.0.0
 */
public class TCSetDataFactory {
    private static final String TAG = "TCSetDataFactory";

    public static TCSetData createSetData(Bundle data) {
        if (data == null) {
            SkySSSLog.w(TAG, "createSetData: null by param is null");
            return null;
        }

        TCSetData retData = null;
        try {
            String dataType = data.getString("type");
            if (dataType == null) {
                return retData;
            }

            SkyConfigType type = SkyConfigType.valueOf(dataType);
            switch (type) {
                case SKY_CONFIG_ENUM:
                    retData = new TCEnumSetData(data);
                    break;
                case SKY_CONFIG_SWITCH:
                    retData = new TCSwitchSetData(data);
                    break;
                case SKY_CONFIG_RANGE:
                    retData = new TCRangeSetData(data);
                    break;
                case SKY_CONFIG_INFO:
                    retData = new TCInfoSetData(data);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retData;
    }

    public static TCSetData createSetData(byte[] bytes) {
        TCSetData retData = null;
        try {
            SkyConfigType type = TCSetData.getType(bytes);
            if (type == null) {
                return null;
            }

            switch (type) {
                case SKY_CONFIG_ENUM:
                    retData = new TCEnumSetData(bytes);
                    break;
                case SKY_CONFIG_SWITCH:
                    retData = new TCSwitchSetData(bytes);
                    break;
                case SKY_CONFIG_RANGE:
                    retData = new TCRangeSetData(bytes);
                    break;
                case SKY_CONFIG_INFO:
                    retData = new TCInfoSetData(bytes);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retData;
    }
}
