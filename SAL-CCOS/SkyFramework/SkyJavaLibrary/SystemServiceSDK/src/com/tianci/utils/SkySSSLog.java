package com.tianci.utils;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.skyworth.api.utils.LogApi;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SkySSSLog {
    private static final String TAG_PREFIX = "SSS-";

    public static void e(String tag, String msg) {
        LogApi.e(TAG_PREFIX + tag, msg);
    }

    public static void w(String tag, String msg) {
        LogApi.w(TAG_PREFIX + tag, msg);
    }

    public static void i(String tag, String msg) {
        LogApi.i(TAG_PREFIX + tag, msg);
    }

    public static void d(String tag, String msg) {
        LogApi.d(TAG_PREFIX + tag, msg);
    }

    public static void d(String tag, String msg, Map<String, String> args) {
        if (args != null) {
            for (String key : args.keySet()) {
                msg += "[" + key + ":" + args.get(key) + "]";
            }
        } else {
            msg += "null";
        }
        LogApi.d(TAG_PREFIX + tag, msg);
    }

    public static void d(String tag, String msg, Cursor cursor) {
        if (cursor != null) {
            try {
                int count = cursor.getCount();
                if (count > 1) {
                    msg += "{count:" + count + "}";
                }
                if (cursor.moveToFirst()) {
                    for (String key : cursor.getColumnNames()) {
                        int index = cursor.getColumnIndex(key);
                        int type = cursor.getType(index);
                        String value = null;
                        switch (type) {
                            case Cursor.FIELD_TYPE_BLOB:
                                value = String.valueOf(cursor.getBlob(index));
                                break;
                            case Cursor.FIELD_TYPE_INTEGER:
                                value = String.valueOf(cursor.getInt(index));
                                break;
                            case Cursor.FIELD_TYPE_FLOAT:
                                value = String.valueOf(cursor.getFloat(index));
                                break;
                            case Cursor.FIELD_TYPE_STRING:
                                value = cursor.getString(index);
                                break;
                        }
                        msg += "[" + key + ":" + value + "]";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                msg += "Exception";
            }
        } else {
            msg += "null";
        }
        LogApi.d(TAG_PREFIX + tag, msg);
    }

    public static void d(String tag, String msg, ContentValues values) {
        if (values != null) {
            Set<Map.Entry<String, Object>> valueSet = values.valueSet();
            if (valueSet != null) {
                Iterator<Map.Entry<String, Object>> it = valueSet.iterator();
                Map.Entry<String, Object> keyValue;
                while (it.hasNext()) {
                    keyValue = it.next();
                    if (keyValue != null) {
                        msg += "[" + keyValue.getKey() + ":" + keyValue.getValue() + "]";
                    }
                }
            }
        } else {
            msg += "null";
        }
        LogApi.d(TAG_PREFIX + tag, msg);
    }

    public static void d(String tag, String msg, Bundle bundle) {
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                msg += "[" + key + ":" + bundle.get(key) + "]";
            }
        } else {
            msg += "null";
        }
        LogApi.d(TAG_PREFIX + tag, msg);
    }

    public static void d(String tag, String msg, Intent intent) {
        if (intent != null) {
            msg += intent.getAction();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    msg += "[" + key + ":" + bundle.get(key) + "]";
                }
            }
        } else {
            msg += "null";
        }
        LogApi.d(TAG_PREFIX + tag, msg);
    }
}
