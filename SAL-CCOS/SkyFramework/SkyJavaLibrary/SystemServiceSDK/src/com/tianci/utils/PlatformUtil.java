package com.tianci.utils;

import com.skyworth.framework.skysdk.properties.SkySystemProperties;

public class PlatformUtil {
    //ro.Business.product.type=3	会议机-触摸	用于区分专用触摸会议机
    private static final String BUSINESS_TYPE_CONFERENCE_MACHINE = "3";
    //ro.Business.product.type=2	会议机-非触摸，用于区分Q70定制机
    private static final String BUSINESS_TYPE_CONFERENCE_MACHINE2 = "2";
    /**
     * 酒店机
     */
    private static final String BUSINESS_TYPE_HOTEL_MACHINE = "4";

    /**
     * 用于判断是否是双WIFI，解决AP热点相关Bug
     *
     * @return
     */
    public static boolean isSupportDoubleWifi() {
        String type = SkySystemProperties.getProperty("ro.Business.product.type");
        return BUSINESS_TYPE_CONFERENCE_MACHINE.equals(type) || BUSINESS_TYPE_CONFERENCE_MACHINE2.equals(type);
    }

    /**
     * 用于判断是否是会议机
     *
     * @return
     */
    public static boolean isConferenceMachine() {
        return BUSINESS_TYPE_CONFERENCE_MACHINE.equals(SkySystemProperties.getProperty("ro.Business.product.type"));
    }

    /**
     * 用于判断是否是轻会议机
     *
     * @return
     */
    public static boolean isLightMachine() {
        return BUSINESS_TYPE_CONFERENCE_MACHINE2.equals(SkySystemProperties.getProperty("ro.Business.product.type"));
    }

    /**
     * 用于判断是否是酒店机器
     *
     * @return
     */
    public static boolean isHotelMachine() {
        return BUSINESS_TYPE_HOTEL_MACHINE.equals(SkySystemProperties.getProperty("ro.Business.product.type"));
    }


}
