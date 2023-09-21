package com.skyworth.framework.utils.internel.log;

import android.os.SystemProperties;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 *
 * @author mikan
 * @version V1.0.0
 * @ClassName DebugUtil
 * @date 15/4/7
 */
public class DebugUtil {
    private static final String PROPERTY_DEVELOP_MODE = "third.get.facSingleKeyEnable";

    public static boolean isDebugMode() {
        String mode = SystemProperties.get(PROPERTY_DEVELOP_MODE);
        return mode != null && mode.equalsIgnoreCase("true");
    }
}
