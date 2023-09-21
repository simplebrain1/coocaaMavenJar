package com.tianci.tv.define.object;

import java.io.Serializable;

/**
 * Created by hq on 2017/11/10.
 */

public class ChannelInfo  implements Serializable, Cloneable{
	
    /**
     * Description:
     */
    private static final long serialVersionUID = 7962133867380986197L;

    public static final int TV = 0;
    public static final int RADIO = 1;

    public static final int INVALID_ID = -1;
    public static final int DEFAULT_ID = -2;
    public static final int BOOT_CHANNEL_ID_NULL = -3;

    public int switchChannelType = 0;  // 0:use server id mode  1:use local id mode
    public int id;
    public int index;
    public String name;
    public int channelType = TV;

    public static ChannelInfo buildDefault() {
        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.id = DEFAULT_ID;
        return channelInfo;
    }

    @Override
    public String toString() {
        return "id:" + id + ", name:" + name;
    }
}
