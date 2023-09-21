package com.tianci.tv.define.object;

import java.util.ArrayList;
import java.util.List;

public class Channel extends SkyTvObject
{
    /**
     * Description:
     */
    private static final long serialVersionUID = 7376786160341585049L;

    private transient static final int FLAG_TRUE = 1;
    private transient static final int FLAG_FALSE = 0;

    private enum FLAG_CHANNEL
    {
        FLAG_ENABLE, FLAG_SKIP
    }

    public enum CHANNEL_TYPE
    {
        TV, RADIO
    }

    public Source source = Source.ATV();
    public String icon = "";
    public int ch_type = 0;
    public String search_key_word = "";
    public int service_id = -1;
    public CHANNEL_TYPE type = CHANNEL_TYPE.TV;
    public int mapindex = -1;
    public boolean isDeleted = false;

    /**
     * @Fields liveOnlineResourceList TODO 直播源地址
     */
    public List<OnlineResource> liveOnlineResourceList = new ArrayList<OnlineResource>();
    /**
     * @Fields seekResourceList TODO 时移地址
     */
    public List<OnlineResource> seekResourceList = new ArrayList<OnlineResource>();
    /**
     * Description: atv使用的跳台标志
     */
    public boolean bSkip = false;
    /**
     * Description: atv频道手动频率微调后，该频道不能自动频率控制了
     */
    public boolean bAfcEnable = true;

    private int flag = 0;

    public Channel(String name)
    {
        super(name);
        setDefaultFlag();
    }

    public Channel(String id, String name)
    {
        super(id, name);
        setDefaultFlag();
    }

    private void setDefaultFlag()
    {
        setEnable(true);
        setSkip(false);
    }

    public Source getSource()
    {
        return source;
    }

    public void setEnable(boolean value)
    {
        setFlag(value, FLAG_CHANNEL.FLAG_ENABLE);
    }

    public boolean getEnable()
    {
        return getFlag(FLAG_CHANNEL.FLAG_ENABLE);
    }

    public void setSkip(boolean value)
    {
        setFlag(value, FLAG_CHANNEL.FLAG_SKIP);
    }

    public boolean getSkip()
    {
        return getFlag(FLAG_CHANNEL.FLAG_SKIP);
    }

    private synchronized void setFlag(boolean val, FLAG_CHANNEL f)
    {
        int value = flag & (~(FLAG_TRUE << f.ordinal()));
        if (val)
            flag = value | (FLAG_TRUE << f.ordinal());
        else
            flag = value | (FLAG_FALSE << f.ordinal());
    }

    private synchronized boolean getFlag(FLAG_CHANNEL f)
    {
        int value = flag & (FLAG_TRUE << f.ordinal());
        value = value >> f.ordinal();
        return (value == FLAG_TRUE);
    }

    @Override
    protected void doAfterDeserialize()
    {
        // TODO Auto-generated method stub
        source.afterDeserialize();
    }
}
