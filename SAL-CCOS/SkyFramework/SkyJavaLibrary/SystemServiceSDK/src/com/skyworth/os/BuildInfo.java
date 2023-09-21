package com.skyworth.os;

import android.os.SystemProperties;

import com.skyworth.framework.utils.internel.SystemUtil;

/**
 * 酷开系统信息
 *
 * @since 1
 */
public class BuildInfo {
    private static String systemVersion;

    /**
     * 设备类型-电视
     *
     * @see #DEVICE
     * @since 1
     */
    public static final String TV = "TV";

    /**
     * 设备类型-盒子（OTT/STB）
     *
     * @see #DEVICE
     * @since 1
     */
    public static final String BOX = "BOX";

    /**
     * 机芯
     *
     * @since 1
     */
    public static final String MODEL = getString("ro.build.skymodel");

    /**
     * 机型
     *
     * @since 1
     */
    public static final String TYPE = getString("ro.build.skytype");

    /**
     * 设备类型
     * 取值范围 {@link #TV}, {@link #BOX}
     *
     * @since 1
     */
    public static final String DEVICE = getString("ro.build.skyform");

    /**
     * 芯片
     *
     * @since 1
     */
    public static final String CHIP = getString("ro.build.skymid");

    /**
     * 系统平台版本
     *
     * @return String 系统平台版本字符串
     * @since 1
     */
    public static String getSystemVersion() {
        if (systemVersion != null) {
            return systemVersion;
        }
        try {
            systemVersion = SystemUtil.getSystemVersion().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systemVersion;
    }

    /**
     * 系统编译版本
     *
     * @since 1
     */
    public static final String BUILD_VERSION = getString("ro.build.skyversion");

    private static String getString(String property) {
        return SystemProperties.get(property);
    }

    private static String[] getStringList(String property, String separator) {
        String value = SystemProperties.get(property);
        if (value.isEmpty()) {
            return new String[0];
        } else {
            return value.split(separator);
        }
    }

    private static long getLong(String property) {
        try {
            return Long.parseLong(SystemProperties.get(property));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
