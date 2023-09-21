/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-10-15         thinkhwa
 */

package com.skyworth.framework.utils.internel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * <p>Description:自定义的数据组装类，对各类数据进行组装用于不同端间的传输</p>
 *
 * @author thinkhwa
 * @ClassName SkyDataComposer
 * @date 2013-10-23
 */
public class SkyDataComposer {
    private HashMap<String, String> values;
    private byte[] megaData;
    private int megaDataLength;
    private boolean isMegaData = false;
    final String StringPrefix = "@str:";

    /**
     * TODO(构造函数，初始化相关变量)
     */
    public SkyDataComposer() {
        megaDataLength = 0;
        values = new HashMap<String, String>();
    }

    /**
     * 概述：得到键值key对应的String值<br/>
     *
     * @param key
     * @return String
     * @date 2013-10-23
     */
    public String getStringValue(String key) {
        if (values.get(key) == null) {
            return null;
        }
        return new String(values.get(key));
    }

    /**
     * 概述：判断系列化后的数据是否有键值key的数据<r/>
     *
     * @param key
     * @return boolean
     * @date 2013-10-23
     */
    public boolean hasValue(String key) {
        return values.containsKey(key);
    }

    /**
     * 概述：删除对应键值key的数值<br/>
     *
     * @param key void
     * @date 2013-10-23
     */
    public void removeValue(String key) {
        values.remove(key);
    }

    /**
     * 概述：得到键值key对应的Int值<br/>
     *
     * @param key
     * @return int
     * @date 2013-10-23
     */
    public int getIntValue(String key) {
        return Integer.parseInt(getStringValue(key));
    }

    /**
     * 概述：得到键值key对应的Boolean值<br/>
     *
     * @param key
     * @return boolean
     * @date 2013-10-23
     */
    public boolean getBooleanValue(String key) {
        String value = getStringValue(key);
        if (value.equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * 概述：得到键值key对应的Float值<br/>
     *
     * @param key
     * @return float
     * @date 2013-10-23
     */
    public float getFloatValue(String key) {
        return Float.parseFloat(getStringValue(key));
    }

    /**
     * 概述：得到键值key对应的Double值<br/>
     *
     * @param key
     * @return double
     * @date 2013-10-23
     */
    public double getDoubleValue(String key) {
        return Double.parseDouble(getStringValue(key));
    }

    /**
     * 概述：得到键值key对应的List<String>值<br/>
     *
     * @param key
     * @return List<String>
     * @date 2013-10-23
     */
    public List<String> getStringListValue(String key) {
        List<String> results = new ArrayList<String>();
        String totalString = getStringValue(key);
        if (!(totalString.startsWith("[") && totalString.endsWith("]"))) {
            return null;
        }
        totalString = totalString.substring(1, totalString.length() - 2);
        String[] subStrings = totalString.split(",");
        for (String item : subStrings) {
            results.add(decode(item));
        }
        return results;
    }

    /**
     * 概述：增加一个键值为key，数值为value的String值到系列化的数值中<br/>
     *
     * @param key
     * @param value void
     * @date 2013-10-23
     */
    public void addValue(String key, String value) {
        if (value == null) {
            value = "";
        }
        values.put(key, encode(value));
    }

    /**
     * 概述：增加一个键值为key，数值为value的byte[]值到系列化的数值中<br/>
     *
     * @param key
     * @param value void
     * @date 2013-10-23
     */
    public void addValue(String key, byte[] value) {
        if (value == null) {
            return;
        }
        addValue(key, Base64.encodeToString(value));
    }

    /**
     * 概述：增加一个键值为key，数值为value的List<String>值到系列化的数值中<br/>
     *
     * @param key
     * @param value void
     * @date 2013-10-23
     */
    public void addValue(String key, List<String> value) {
        StringBuffer realValue = new StringBuffer(10000);
        realValue.append("[");
        for (String item : value) {
            if (item != null) {
                realValue.append(encode(item));
                realValue.append(",");
            }
        }
        realValue.append("]");
        String result = realValue.toString();
        values.put(key, encode(result));
    }

    /**
     * 概述：增加一个键值为key，数值为value的int值到系列化的数值中<br/>
     *
     * @param key
     * @param value void
     * @date 2013-10-23
     */
    public void addValue(String key, int value) {
        values.put(key, String.valueOf(value));
    }

    /**
     * 概述：增加一个键值为key，数值为value的boolean值到系列化的数值中<br/>
     *
     * @param key
     * @param value void
     * @date 2013-10-23
     */
    public void addValue(String key, boolean value) {
        values.put(key, String.valueOf(value));
    }

    /**
     * 概述：增加一个键值为key，数值为value的float值到系列化的数值中<br/>
     *
     * @param key
     * @param value void
     * @date 2013-10-23
     */
    public void addValue(String key, float value) {
        values.put(key, String.valueOf(value));
    }

    /**
     * 概述：增加一个键值为key，数值为value的double值到系列化的数值中<br/>
     *
     * @param key
     * @param value void
     * @date 2013-10-23
     */
    public void addValue(String key, double value) {
        values.put(key, String.valueOf(value));
    }

    /**
     * 概述：设置MegaData的数值<br/>
     *
     * @param value void
     * @date 2013-10-24
     */
    public void setMegaData(byte[] value) {
        megaData = value;
        megaDataLength = value.length;
        isMegaData = true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (isMegaData) {
            return "";
        }
        StringBuffer result = new StringBuffer(10000);
        result.append(StringPrefix);
        Iterator<Entry<String, String>> iter = values.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String> entry = (Entry<String, String>) iter.next();
            String value = entry.getValue();
            String key = entry.getKey();
            result.append(key);
            result.append("=");
            result.append(value);
            result.append(";");
        } 
        /*
        String[] keys = new String[values.size()];
        values.keySet().toArray(keys);
        for (int i = 0; i < keys.length; i++)
        {
            result = result + keys[i] + "=" + new String(values.get(keys[i])) + ";";
        }*/
        return result.toString();
    }

    /**
     * 概述：对指定的字符进行字符替换编码<br/>
     *
     * @param str
     * @return String
     * @date 2013-10-23
     */
    private String encode(String str) {
        String result = new String(str);
        result = result.replace("%", "%25");
        result = result.replace(";", "%3B");
        result = result.replace("[", "%5B");
        result = result.replace("]", "%5D");
        result = result.replace(",", "%2C");
        result = result.replace("=", "%3D");
        return result;
    }

    /**
     * 概述：对指定的字符进行字符反替换解码<br/>
     *
     * @param str
     * @return String
     * @date 2013-10-23
     */
    private String decode(String str) {
        String result = new String(str);
        result = result.replace("%3D", "=");
        result = result.replace("%3B", ";");
        result = result.replace("%5D", "]");
        result = result.replace("%2C", ",");
        result = result.replace("%5B", "[");
        result = result.replace("%25", "%");
        return result;
    }

    /**
     * 概述：将存储完的容器数据按toString()中的方式拼装为Byte数据类型<br/>
     *
     * @return byte[]
     * @date 2013-10-23
     */
    public byte[] toByteArray() {
        if (isMegaData) {
            return megaData;
        }
        return toString().getBytes();
    }

    public static void main(String[] args) {

        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("url", "http://a?b=%5Bcd%3D");
        composer.addValue("f", 0.123);
        String dStr = composer.toString();
        System.out.println(dStr);
        SkyDataDecomposer decomposer = new SkyDataDecomposer(dStr);
        System.out.println(decomposer.getFloatValue("f"));
        System.out.println(decomposer.getStringValue("url"));
    }
}
