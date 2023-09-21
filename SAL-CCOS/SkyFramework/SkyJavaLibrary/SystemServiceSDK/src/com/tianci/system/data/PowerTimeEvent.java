/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2016-11-29         jgui
 */

package com.tianci.system.data;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jgui
 * @version 1.0
 * @ClassName PowerTimeEvent
 * @Description 待机处理事件的结构类，用于描述关机广告、关机升级等事件，
 * 关机的时候一次只会执行一个待机事件，多个待机事件会根据优先级来处理；
 * @date 2016-11-30
 */
public class PowerTimeEvent implements Serializable
{
    private static final long serialVersionUID = 5474243051757571668L;
    /**
     * @desc BY_ACTION 通过action的方式启动
     */
    public static final int BY_ACTION = 1;
    /**
     * @desc BY_PKG_URI 通过BY_PKG_URI，通过uri的方式启动
     */
    public static final int BY_PKG_URI = 2;
    /**
     * @desc BY_BROADCAST 通过Android广播的方式启动
     */
    public static final int BY_BROADCAST = 3;
    /**
     * @desc BY_CLASSNAME 通过制定activity的方式启动
     */
    public static final int BY_CLASSNAME = 4;

    /**
     * @desc SYSTEM_UPGRADE_EVENT 系统升级event名称，优先级最高
     */
    public static String SYSTEM_UPGRADE_EVENT = "systemupgradeevent";

    /**
     * @desc LEVEL_0 当前事件的优先级
     */
    public static final int LEVEL_0 = 0;
    /**
     * @desc BLEVEL_1 当前事件的优先级
     */
    public static final int LEVEL_1 = 1;
    /**
     * @desc LEVEL_2 当前事件的优先级
     */
    public static final int LEVEL_2 = 2;


    /**
     * @Fields level 表示事件的优先级，值越小越先执行
     */
    private int level = LEVEL_2;

    /**
     * @Fields eventName 事件名称，
     */
    private String eventName = "";

    /**
     * @Fields packageName 启动事件的包名
     */
    private String packageName = "";

    private final static int DEFAULT_POWER_TIME = 15;
    /**
     * @Fields powerTime 完成该事件需要的时间，单位s
     */
    private int powerTime = DEFAULT_POWER_TIME;

    /**
     * @Fields byWhat 事件启动的方式，action、activity uri、broadcast
     */
    private int byWhat = BY_ACTION;
    /**
     * @Fields hasUsertActioned 针对需要交互的业务是否当前业务与用户进行了交互,默认false：没有进行交互；
     * false：默认没有进行交互，业务结束后待机，true：进行了交互，待机终止
     */
    private boolean hasUsertActioned = false;

    /**
     * @Fields byWhatValue 启动方式值，如aciton，uri等
     */
    private String byWhatValue = "";
    /**
     * @Fields needAction 当前业务是否有交互，默认没有；false：没有交互，业务执行完成后自动待机，true：有交互，业务在执行中，如果用户交互了，则终止此次待机。
     */
    private boolean needAction = false;
    /**
     * extraParam intent 附带的参数
     */
    private HashMap<String, String> extraParam = null;

    /**
     * @desc 待机处理事件的结构类的构造方法，用于描述关机广告、关机升级等事件，
     * 关机的时候一次只会执行一个待机事件，多个待机事件会根据优先级来处理；
     * @params eventName 事件名称，不同事件的名称最好不一样，但要确保唯一性
     * level 表示事件的优先级，值越小优先级越高 ，可选择：LEVEL_0、LEVEL_1、LEVEL_2，默认等级LEVEL_2
     * needAction 执行业务时，是否需要交互，默认不需要；false：没有交互，true：有交互，系统不立马待机，等待执行该业务结束后待机。
     * packageName 启动事件的包名
     * powerTime  完成该事件需要的时间，单位s，默认15S
     * byWhat 事件启动的方式：BY_ACTION、BY_PKG_URI、 BY_BROADCAST、BY_CLASSNAME，默认BY_ACTION方式启动
     * params 对应启动方式的启动参数
     * @retrun
     */
    public PowerTimeEvent(String eventName, int level, boolean needAction, String packageName,
                          int powerTime, int byWhat, String byWhatValue, HashMap<String, String>
                                  extraParam)
    {
        this.eventName = eventName;
        this.level = level;
        this.needAction = needAction;
        this.packageName = packageName;
        if (powerTime > 0 && powerTime <= DEFAULT_POWER_TIME)
        {
            this.powerTime = powerTime;
        } else
        {
            this.powerTime = DEFAULT_POWER_TIME;
        }
        //for test
//        this.powerTime = 19;
        this.byWhat = byWhat;
        this.byWhatValue = byWhatValue;
        this.extraParam = extraParam;
    }

    public boolean isHasUsertActioned()
    {

        return hasUsertActioned;
    }

    /**
     * @desc 针对需要交互的业务是否当前业务与用户进行了交互, 默认false：没有进行交互；
     * @params hasUsertActioned  false：默认没有进行交互，业务结束后待机，true：进行了交互，待机终止
     * @retrun null
     */
    public void setHasUsertActioned(boolean hasUsertAction)
    {

        this.hasUsertActioned = hasUsertAction;
    }

    public boolean isNeedAction()
    {
        return needAction;
    }

    public void setNeedAction(boolean needAction)
    {
        this.needAction = needAction;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getEventName()
    {
        return eventName;
    }

    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public int getPowerTime()
    {
        return powerTime;
    }

    public void setPowerTime(int powerTime)
    {
        this.powerTime = powerTime;
    }

    public int getByWhat()
    {
        return byWhat;
    }

    public void setByWhat(int byWhat)
    {
        this.byWhat = byWhat;
    }

    public String getByWhatValue()
    {
        return byWhatValue;
    }

    public void setByWhatValue(String byWhatValue)
    {
        this.byWhatValue = byWhatValue;
    }

    public HashMap<String, String> getExtraParam()
    {
        return extraParam;
    }

    public void setExtraParam(HashMap<String, String> extraParam)
    {
        this.extraParam = extraParam;
    }

    @Override
    public String toString()
    {
        SkyDataComposer skyDataComposer = new SkyDataComposer();
        skyDataComposer.addValue("level", level);
        skyDataComposer.addValue("eventName", eventName);
        skyDataComposer.addValue("packageName", packageName);
        skyDataComposer.addValue("powerTime", powerTime);
        skyDataComposer.addValue("byWhat", byWhat);
        skyDataComposer.addValue("byWhatValue", byWhatValue);
        skyDataComposer.addValue("hasUsertActioned", hasUsertActioned);
        skyDataComposer.addValue("needAction", needAction);
        skyDataComposer.addValue("extraParam", mapToString(extraParam));
        return skyDataComposer.toString();
    }

    public static PowerTimeEvent parse(String value)
    {
        try
        {
            SkyDataDecomposer sdd  = new SkyDataDecomposer(value);
            PowerTimeEvent pt = new PowerTimeEvent(sdd.getStringValue("eventName"), sdd
                    .getIntValue("level"), sdd.getBooleanValue("needAction"),
                    sdd.getStringValue("packageName"), sdd.getIntValue("powerTime"), sdd
                    .getIntValue("byWhat"), sdd.getStringValue("byWhatValue"),
                    stringToMap(sdd.getStringValue("extraParam")));
            pt.setHasUsertActioned(sdd.getBooleanValue("hasUsertActioned"));
            return pt;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUpgradeSystem()
    {
        boolean flag = false;
        //如果系统是system发送的事件，，同时启动的activity是升级的activity，则认为就是系统升级
        if (eventName != null && !"".equals(eventName) && eventName.equals(SYSTEM_UPGRADE_EVENT))
        {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean equals(Object obj)
    {
        PowerTimeEvent toCom = (PowerTimeEvent) obj;
        do
        {
            if (toCom.level != this.level)
            {
                break;
            }
            if (!toCom.eventName.equals(this.eventName))
            {
                break;
            }
            if (!toCom.packageName.equals(this.packageName))
            {
                break;
            }
            if (toCom.powerTime != this.powerTime)
            {
                break;
            }
            if (toCom.byWhat != this.byWhat)
            {
                break;
            }
            if (!toCom.byWhatValue.equals(this.byWhatValue))
            {
                break;
            }
            return true;
        }
        while (false);
        return false;
    }

//    public byte[] toBytes()
//    {
//        return this.toString().getBytes();
//    }

    /**
     * 定义分割常量 （#在集合中的含义是每个元素的分割，|主要用于map类型的集合用于key与value中的分割）
     */
    private static final String SEP1 = "#";
    private static final String SEP2 = "|";

    /**
     * Map转换String
     *
     * @param map :需要转换的Map
     * @return String转换后的字符串
     */
    private  String mapToString(Map<String, String> map)
    {
        StringBuffer sb = new StringBuffer();
        // 遍历map
        for (String obj : map.keySet())
        {
            if (obj == null)
            {
                continue;
            }
            String key = obj;
            String value = map.get(key);
            sb.append(key.toString() + SEP1 + value.toString());
            sb.append(SEP2);
        }
        return sb.toString();
    }

    /**
     * String转换Map
     *
     * @param mapText :需要转换的字符串
     * @return Map<String,String>
     */
    private static HashMap<String, String> stringToMap(String mapText)
    {

        if (mapText == null || mapText.equals(""))
        {
            return null;
        }
        mapText = mapText.substring(1);

        HashMap<String, String> map = new HashMap<String, String>();
        String[] text = mapText.split("\\" + SEP2); // 转换为数组
        for (String str : text)
        {
            String[] keyText = str.split(SEP1); // 转换key与value的数组
            if (keyText.length < 1)
            {
                continue;
            }
            String key = keyText[0]; // key
            String value = keyText[1]; // value
            map.put(key, value);
        }
        return map;
    }
}
