package com.tianci.net.data;

import java.io.Serializable;

/**
 * Created by whw on 2021/3/25
 */
public class AppUsageInfo implements Serializable {
    static final long serialVersionUID = 731010835061697353L;
    public String packageName;
    public long interval_daily;
    public long interval_weekly;

    @Override
    public String toString() {
        return "AppUsageInfo{" +
                "packageName='" + packageName + '\'' +
                ", interval_daily=" + interval_daily +
                ", interval_weekly=" + interval_weekly +
                '}';
    }
}
