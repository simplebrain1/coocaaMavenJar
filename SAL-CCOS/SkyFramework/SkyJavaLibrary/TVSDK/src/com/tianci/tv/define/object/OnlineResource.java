package com.tianci.tv.define.object;

import com.skyworth.framework.skysdk.util.Base64;
import com.tianci.tv.utils.SkyTvUtils;

import java.io.Serializable;
import java.util.UUID;

public class OnlineResource implements Serializable, Cloneable
{
    public enum RES_TYPE
    {
        TIMESHIFT, LOOKBACK,
    }

    /**
     * Description:
     */
    private static final long serialVersionUID = 7687795479714320147L;

    public String title = null;
    public String id = null;
    public String source_from = null;
    public String play_url = null;
    public int duration = 0;
    public int code = 0;
    public String extra = "";
    public RES_TYPE type = null;

    public OnlineResource(String title, String play_url)
    {
        id = UUID.randomUUID().toString();
        this.source_from = play_url;
        this.title = title;
        this.play_url = parsePlayUrl(play_url);
    }

    public OnlineResource(String title, String source_from, String play_url)
    {
        id = UUID.randomUUID().toString();
        this.source_from = source_from;
        this.title = title;
        this.play_url = parsePlayUrl(play_url);
    }

    public OnlineResource(String id, String title, String source_from, String play_url)
    {
        this.id = id;
        this.source_from = source_from;
        this.title = title;
        this.play_url = parsePlayUrl(play_url);
    }

    private String parsePlayUrl(String url)
    {
        try
        {
            String[] result = url.split("@");
            if (result != null && result.length > 0)
            {
                if (result.length == 2)
                {
                    System.out.println("result[0]:" + result[0] + "  result[1]:" + result[1]);
                    String param = result[1];
                    String[] params = param.split("\\?");
                    if (params != null)
                    {
                        for (String p : params)
                        {
                            String[] pp = p.split("=");
                            if (pp != null && pp.length == 2)
                            {
                                String p_title = pp[0];
                                String p_value = pp[1];
                                if (p_title.equals("duration"))
                                    duration = Integer.valueOf(p_value);
                            }
                        }
                    }
                }
                return result[0];
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";

    }

    @Override
    public boolean equals(Object obj)
    {
        // TODO Auto-generated method stub
        try
        {
            return ((OnlineResource) obj).id.equals(id);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        byte[] bytes = SkyTvUtils.toBytes(this);
        return Base64.encodeToString(bytes);
    }

    public OnlineResource clone()
    {
        OnlineResource o = null;
        try
        {
            o = (OnlineResource) super.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return o;
    }

    public static OnlineResource toOnlineResource(String str)
    {
        byte[] bytes = Base64.decode(str);
        return SkyTvUtils.toObject(bytes, OnlineResource.class);
    }

    public static void main(String[] args)
    {
        String pg_url = "";
        String[] pg_url_list = pg_url.split("#");
        System.out.println("parse pg_url_list.length:" + pg_url_list.length);
        if (pg_url_list != null)
        {
            for (String str : pg_url_list)
            {
                System.out.println("parse:" + str);
                OnlineResource a = new OnlineResource("", str);
                System.out.println(a.play_url + "/" + a.duration);
            }
        }
    }
}
