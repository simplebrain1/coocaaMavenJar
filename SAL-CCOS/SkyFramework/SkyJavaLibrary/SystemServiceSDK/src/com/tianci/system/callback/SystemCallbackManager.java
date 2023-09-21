package com.tianci.system.callback;

public class SystemCallbackManager
{
    private SystemCallback systemCallback;

    public synchronized SerializableBinder registerSystemCallback(OnSystemCallback callback)
    {
        SerializableBinder binder = null;

        if (callback == null)
        {
            return null;
        }
        if (systemCallback == null)
        {
            systemCallback = new SystemCallback();
            binder = systemCallback;
        }
        systemCallback.register(callback);
        return binder;
    }

    public synchronized SerializableBinder unregisterSystemCallback(OnSystemCallback callback)
    {
        SerializableBinder binder = null;
        if (systemCallback.unregister(callback) == 0)
        {
            binder = systemCallback;
            systemCallback = null;
        }
        return binder;
    }
}
