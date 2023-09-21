/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-10-15         thinkhwa
 */

package com.skyworth.framework.utils.internel;

import com.skyworth.framework.utils.internel.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * <p>Description:自定义的数据反组装类，对组装成的字符进行反序列化</p>
 *
 * @author thinkhwa
 * @ClassName SkyDataDecomposer
 * @date 2013-10-23
 */
public class SkyDataDecomposer {
    private HashMap<String, String> values;
    private byte[] megaData;
    private int megaDataLength = 0;
    private boolean isMegaData = false;
    final String StringPrefix = "@str:";

    /**
     * TODO(默认构造函数，初始化相关变量)
     */
    public SkyDataDecomposer() {
        values = new HashMap<String, String>();
    }

    /**
     * TODO(构造函数，传入需要进行反序列化的组装过的字符)
     */
    public SkyDataDecomposer(String data) {
        values = new HashMap<String, String>();
        parserString(data);
    }

    /**
     * 概述：对组装过的字符data进行反序列化操作<br/>
     *
     * @param data 经过组装（序列化）后的字符串
     * @date 2013-10-23
     */
    public void parserString(String data) {
        if (data == null) {
            return;
        }
        if (data.startsWith(StringPrefix)) {
            values.clear();
            String realData = data.substring(StringPrefix.length());
            String valuePaar[] = realData.split(";");
            for (int i = 0; i < valuePaar.length; i++) {
                if (valuePaar[i].length() > 0) {
                    String temp[] = valuePaar[i].split("=");
                    if (temp.length == 2) {
                        values.put(temp[0].trim(), decode(temp[1]).trim());
                    } else {
                        values.put(temp[0], "");
                    }
                }
            }
        } else {
            Logger.e("SkyDataDecomposer", "INVALID SKY DATA FORMAT");
        }
    }

    /**
     * TODO(构造函数，传入需要进行反序列化的组装过的bp)
     *
     * @param data
     * @param dataLen
     */
    public SkyDataDecomposer(byte[] data, int dataLen) {
        for (int i = 0; i < StringPrefix.length(); i++) {
            if (data[i] != StringPrefix.charAt(i)) {
                isMegaData = true;
                break;
            }
        }
        if (isMegaData) {
            megaData = data;
            megaDataLength = dataLen;
        } else {
            String text = new String(data);
            text = text.substring(0, dataLen);
            parserString(text);
        }
    }

    /**
     * 概述：<br/>
     *
     * @param key
     * @return String
     * @date 2013-10-23
     * @see SkyDataComposer#getStringListValue(String)
     */
    public String getStringValue(String key) {
        if (values.get(key) == null) {
            return null;
        }
        return new String(values.get(key));
    }

    /**
     * 概述：<br/>
     *
     * @param key
     * @return boolean
     * @date 2013-10-23
     * @see SkyDataComposer#hasValue(String)
     */
    public boolean hasValue(String key) {
        return values.containsKey(key);
    }

    /**
     * 概述：<br/>
     *
     * @param key void
     * @date 2013-10-23
     * @see SkyDataComposer#removeValue(String)
     */
    public void removeValue(String key) {
        values.remove(key);
    }

    /**
     * 概述：<br/>
     *
     * @param key
     * @return int
     * @date 2013-10-23
     * @see SkyDataComposer#getIntValue(String)
     */
    public int getIntValue(String key) {
        return Integer.parseInt(getStringValue(key));
    }

    /**
     * 概述：<br/>
     *
     * @param key
     * @return boolean
     * @date 2013-10-24
     * @see SkyDataComposer#getBooleanValue(String)
     */
    public boolean getBooleanValue(String key) {
        String value = getStringValue(key);
        if (value.equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * 概述：得到键值key对应的byte[]值<br/>
     *
     * @param key
     * @return byte[]
     * @date 2013-10-24
     */
    public byte[] getBytesValue(String key) {
        String stringValue = values.get(key);
        if (stringValue == null) {
            return null;
        }
        return Base64.decode(stringValue);
    }

    /**
     * 概述：得到键值key对应的float值<br/>
     *
     * @param key
     * @return float
     * @date 2013-10-24
     */
    public float getFloatValue(String key) {
        return Float.parseFloat(getStringValue(key));
    }

    /**
     * 概述：得到键值key对应的double值<br/>
     *
     * @param key
     * @return double
     * @date 2013-10-24
     */
    public double getDoubleValue(String key) {
        return Double.parseDouble(getStringValue(key));
    }

    /**
     * 概述：得到键值key对应的List<String>值<br/>
     *
     * @param key
     * @return List<String>
     * @date 2013-10-24
     */
    public List<String> getStringListValue(String key) {
        List<String> results = new ArrayList<String>();
        String totalString = getStringValue(key);
        if (totalString == null) {
            return null;
        }
        if (totalString.length() <= 2) {
            return results;
        }
        totalString = totalString.substring(1, totalString.length() - 1);
        String[] subStrings = totalString.split(",");
        for (String item : subStrings) {
            results.add(decode(item));
        }
        return results;
    }

    /**
     * 概述：判断系列化的数据是否为MegaData<br/>
     *
     * @return boolean
     * @date 2013-10-24
     */
    public boolean isMegaData() {
        return isMegaData;
    }

    /**
     * 概述：得到megaData数值<br/>
     *
     * @return byte[]
     * @date 2013-10-24
     */
    public byte[] getMegaData() {
        if (isMegaData) {
            return megaData;
        } else {
            return null;
        }
    }

    /**
     * 概述：获取MegaData数值的长度<br/>
     *
     * @return int
     * @date 2013-10-24
     */
    public int getMegaDataLength() {
        return megaDataLength;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (isMegaData) {
            return "";
        }
        String result = "";
        String[] keys = new String[values.size()];
        values.keySet().toArray(keys);
        result = result + StringPrefix;
        for (int i = 0; i < keys.length; i++) {
            result = result + keys[i] + "=" + new String(values.get(keys[i])) + ";";
        }
        return result;
    }

    /**
     * 概述：存储完的容器数据按toString()中的方式拼装为Byte数据类型<br/>
     *
     * @return byte[]
     * @date 2013-10-24
     */
    public byte[] toByteArray() {
        if (isMegaData) {
            return megaData;
        }
        return toString().getBytes();
    }

    /**
     * 概述：对指定的字符进行字符反替换解码<br/>
     *
     * @param str
     * @return String
     * @date 2013-10-24
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
