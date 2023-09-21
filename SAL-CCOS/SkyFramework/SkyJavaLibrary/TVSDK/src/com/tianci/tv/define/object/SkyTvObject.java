package com.tianci.tv.define.object;

import java.io.Serializable;
import java.util.UUID;

public abstract class SkyTvObject implements Serializable, Cloneable
{
    /**
     * Description:
     */
    private static final long serialVersionUID = 7962133867380986197L;

    public String id = null;
    public String name = null;
    public String displayName = null;
    public int index = 0;
    /**
     * @Fields invalid 是否有效
     */
    public boolean invalid = false;

    protected transient boolean bAfterDeserialize = false;

    private transient Object object = null;

    public SkyTvObject()
    {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        setDisplayName(name);
    }

    public SkyTvObject(String name)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        setDisplayName(name);
    }

    public SkyTvObject(String id, String name)
    {
        this.id = id;
        this.name = name;
        setDisplayName(name);
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setObject(Object obj)
    {
        object = obj;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> clazz)
    {
        return (T) object;
    }

    @Override
    public boolean equals(Object obj)
    {
        // TODO Auto-generated method stub
        try
        {
            SkyTvObject o = (SkyTvObject) obj;
            return o.id.equals(id);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public final synchronized void afterDeserialize()
    {
        if (!bAfterDeserialize)
        {
            doAfterDeserialize();
            bAfterDeserialize = true;
        }
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public SkyTvObject clone() throws CloneNotSupportedException
    {
        return (SkyTvObject) super.clone();
    }

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return getClass().getSimpleName() + "/" + getDisplayName();
    }

    protected abstract void doAfterDeserialize();
}
