package com.tianci.system.interfaces;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.skyworth.framework.skysdk.properties.SkyGeneralProperties;
import com.skyworth.framework.skysdk.properties.SkySystemProperties;
import com.skyworth.framework.utils.SystemPropertiesUtil;

/**
@Date : 2015年11月23日
@Author : Zhan Yu
@Description : TODO
 */
public class BluetoothRcHelper
{
	
	protected Context context;	
	protected final String TAG = "BT_RC";

	private final String BLUETOOTH_RC_BCT = "com.skyworth.broadcast.bluetooth.rc";

	public void setContext(Context c)
	{
		this.context = c;
	}
	
	public void active()
	{
		
	}
	
	public void deactive()
	{

	}
	
	protected void checkRCState()
	{
		if(isRCConnected())
			onRCConnected();
		else
			onRCDisconnected();
	}

	protected boolean isRCConnected()
	{
		return false;
	}

	protected void onRCConnected()
	{
		Log.d(TAG, "onRCConnected");
		setRCProp("1");
		sendBroadcast(1);
	}
	
	protected void onRCDisconnected()
	{
		Log.d(TAG, "onRCDisconnected");
		setRCProp("0");
		sendBroadcast(0);
	}
	
	protected void setRCProp(String val)
	{
		Log.d(TAG, "setprop third.get.bluetooth.rc : " + val);
		SkySystemProperties.setProperty("third.get.bluetooth.rc", val);
	}
	
	protected void sendBroadcast(int state)
	{
		Log.d(TAG, "sendBroadcast BLUETOOTH_RC_BCT with state : " + state);
		Intent intent = new Intent(BLUETOOTH_RC_BCT);
		Log.d(TAG,"TCBtRcConnectedFunction    addFlag");
		intent.addFlags(0x01000000);
		intent.putExtra("STATE", state);
		context.sendBroadcast(intent);
	}
	
	protected boolean isRCDevice(String devName)
    {
	    Log.d(TAG, "devName : " + devName);
        if (devName == null)
        	return false;
        return devName.startsWith("Skyworth_RC") || devName.startsWith("coocaa_dev")
                || devName.startsWith("Skyworth_BT_RC") || isExternalConfigRc(devName);
    }

	private static final String BLE_RC_NAME_SPLIT = "\\|";
	private boolean isExternalConfigRc(String deviceName)
	{
		String names = SystemPropertiesUtil.getProperty(SkyGeneralProperties.GeneralPropKey.BLE_RC_NAME_PREFIX.toString());
		if(TextUtils.isEmpty(names))
		{
			return false;
		}

		String[] nameList = names.split(BLE_RC_NAME_SPLIT);
		for (String name : nameList)
		{
			if (deviceName.startsWith(name))
			{
				return true;
			}
		}
		return false;
	}
}