package com.tianci.tv.framework.api;

import com.tianci.tv.utils.SkyTvUtils;

import java.io.Serializable;

public abstract class SkyTvApiParams implements Serializable
{

    /**
     * Description:
     */
    private static final long serialVersionUID = -3980967727952336364L;

    public SkyTvApiParams()
    {

    }

    public SkyTvApiParams(byte[] bytes)
    {
        SkyTvApiParams data = SkyTvUtils.toObject(bytes, SkyTvApiParams.class);
        formData(data);
    }

    protected void formData(SkyTvApiParams data)
    {

    }

    public final byte[] getBytes()
    {
        return SkyTvUtils.toBytes(this);
    }
}
