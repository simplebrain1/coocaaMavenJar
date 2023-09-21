/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-11-22         xingkong207
 *
 */

package com.tianci.system.data;

import android.os.Bundle;

import com.skyworth.framework.skysdk.util.SkyDataComposer;
import com.skyworth.framework.skysdk.util.SkyDataDecomposer;

import java.io.Serializable;

/**
 * <p>Description:</p> 
 * <p>write something</p>
 * @ClassName TCRetData
 * @author wei li
 * @date 2013-11-22
 * @version V1.0.0
 */
public class TCRetData extends TCSetData implements Serializable
{
    boolean mSuccess = false;
    
    public TCRetData(){
        super(SkyConfigType.SKY_CONFIG_RET.toString());
    }
    
    public TCRetData(boolean success){
        super(SkyConfigType.SKY_CONFIG_RET.toString());
        this.mSuccess = success;
    }
    
    public TCRetData(byte[] bytes){
        super(SkyConfigType.SKY_CONFIG_RET.toString());
        if(bytes != null){
            deserialize(new String(bytes));
        }
    }
    
    public TCRetData(String value)
    {
        super(SkyConfigType.SKY_CONFIG_RET.toString());
        deserialize(value);
    }

    public TCRetData(Bundle value)
    {
        super(SkyConfigType.SKY_CONFIG_RET.toString());
        deserialize(value);
    }
    
    private void deserialize(Bundle data)
    {
        this.pluginValue = data;
        this.type = data.getString("type");
        this.mSuccess = data.getBoolean("success");
    }
    
    private void deserialize(String value)
    {
        SkyDataDecomposer data = new SkyDataDecomposer(value);
        this.value = value;
        this.type = data.getStringValue("type");
        this.name = data.getStringValue("name");
        this.mSuccess = data.getBooleanValue("success");
        if(data.getStringValue("start") != null){
            this.startProcessTimestamp = Long.valueOf(data.getStringValue("start"));
            this.endProcessTimestamp = Long.valueOf(data.getStringValue("end"));
        }
    }
    
    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        SkyDataComposer composer = new SkyDataComposer();
        composer.addValue("type", this.type);
        composer.addValue("name", this.name);
        composer.addValue("success", this.mSuccess);
        composer.addValue("start", String.valueOf(this.startProcessTimestamp));
        composer.addValue("end", String.valueOf(this.endProcessTimestamp));
        return composer.toString();
    }

    /* (non-Javadoc)
     * @see com.skyworth.system.data.TCSetData#toBytes()
     */
    @Override
    public byte[] toBytes()
    {
        // TODO Auto-generated method stub
        String temp = this.toString();
        if(temp != null){
            return temp.getBytes();
        }
        return null;
    }


    public boolean isSuccess()
    {
        return mSuccess;
    }


    public void setSuccess(boolean success)
    {
        this.mSuccess = success;
    }
}
