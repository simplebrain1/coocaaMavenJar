package com.tianci.system.callback;

public class SystemCallback extends ISystemCallback.Stub {
    private static final long serialVersionUID = -4955980993953605281L;
    @Override
    public synchronized void onPictureModeChange(boolean isAIMode){
        for (OnSystemCallback c:mCallbackList)
        {
            if (c != null)
            {
                c.onPictureModeChange(isAIMode);
            }     
        }
    }

    @Override
    public synchronized void onSoundModeChange(boolean isAIMode){
        for (OnSystemCallback c:mCallbackList)
        {
            if (c != null)
            {
                c.onSoundModeChange(isAIMode);
            }
        }
    }
}
