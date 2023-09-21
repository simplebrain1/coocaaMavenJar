package com.tianci.system.callback;

import android.content.ContentProviderClient;
import android.content.Context;
import android.os.Bundle;

import com.tianci.system.define.SystemProviderDefines;
import com.tianci.system.utils.ApiUtil;
import com.tianci.utils.SkySSSLog;

public class CommonAdapter
{
    private static final String TAG = "CommonAdapter";
    private static CommonAdapter mInstance;
    private SystemCallbackManager mSystemCallbackManager;

    private CommonAdapter(Context context)
    {
        ApiUtil.setContext(context);
        mSystemCallbackManager = new SystemCallbackManager();
    }

    public static synchronized CommonAdapter getInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new CommonAdapter(context);
        }
        return mInstance;
    }

    public boolean registerSystemCallback(OnSystemCallback callback)
    {
        if (callback == null)
        {
            return false;
        }
        SerializableBinder binder = mSystemCallbackManager.registerSystemCallback(callback);
        if (binder == null)
        {
            return true;
        }
        ContentProviderClient client = ApiUtil.getClient();
        if (client == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("binder", binder);
        Bundle ret = null;
        try
        {
            SkySSSLog.i(TAG, "registerSystemCallback: ");
            ret = client.call(SystemProviderDefines.METHOD_COMMON_REG_SYSTEM_CB, null, bundle);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        if (ret == null)
        {
            return false;
        }
        return ret.getBoolean("ret");
    }

    public boolean unregisterSourceCallback(OnSystemCallback callback)
    {
        if (callback == null)
        {
            return false;
        }
        SerializableBinder binder = mSystemCallbackManager.unregisterSystemCallback(callback);
        if (binder == null)
        {
            return true;
        }
        ContentProviderClient client = ApiUtil.getClient();
        if (client == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("binder", binder);
        Bundle ret = null;
        try
        {
            ret = client.call(SystemProviderDefines.METHOD_COMMON_UNREG_SYSTEM_CB, null, bundle);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        if (ret == null)
        {
            return false;
        }
        return ret.getBoolean("ret");
    }
}
