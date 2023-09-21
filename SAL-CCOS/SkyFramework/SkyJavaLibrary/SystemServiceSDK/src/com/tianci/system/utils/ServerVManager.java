package com.tianci.system.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.coocaa.server.verification.api.ServerVerificationApi;
import com.skyworth.framework.skysdk.properties.SkyGeneralProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerVManager {
    private ExecutorService singleTask = Executors.newSingleThreadExecutor();
    private final String TAG = "ServerVManager";
    private static final String HTTP_PROTOCOL = "http://";
    private static final String HTTPS_PROTOCOL = "https://";
    private static ServerVManager instance = null;
    private List<String> serverNameKeyList = new ArrayList<String>();
    private HashMap<String,String>  serverNameListMap = new HashMap<String,String>();
    public interface ServiceNameCallBack {
        void notifyChanged();
    }

    public static String CURRENT_SERVER;
    public static String CURRENT_UPGRADE_SERVER;
    public static String CURRENT_TVINFO_SERVER;
    public static String CURRENT_DEVICE_SERVER;
    public final static String SERVICE_AGREEMENT = "https://webapp.skysrt.com/cc7.0/trans/index3.html";
    public final static String PRIVACY_POLICY = "https://webapp.skysrt.com/cc7.0/trans/index4.html";
    public final static String MEMBERSHIP_CARD = "wx.coocaa.com/qrcode/getTmpQrcode.coocaa";

    private static final String ACTION_ON_SERVER_CONFIG_UPDATE = "coocaa.broadcast.action.ON_SERVER_CONFIG_UPDATE";

    private List<ServiceNameCallBack> serviceNameCallbackList = new ArrayList<ServiceNameCallBack>();

    private ServerVManager(Context context)
    {
        CURRENT_SERVER = SkyGeneralProperties.getProperty(SkyGeneralProperties.GeneralPropKey.CURRENT_SERVER);
        if(!TextUtils.isEmpty(CURRENT_SERVER)) {
            serverNameKeyList.add(CURRENT_SERVER);
            String  currentServerSp = ServerVSharedPrfs.getString(context,CURRENT_SERVER,HTTP_PROTOCOL + CURRENT_SERVER);
            serverNameListMap.put(CURRENT_SERVER,currentServerSp);
        }

        CURRENT_UPGRADE_SERVER = SkyGeneralProperties.getProperty(SkyGeneralProperties.GeneralPropKey.CURRENT_UPGRADE_SERVER);
        if(!TextUtils.isEmpty(CURRENT_UPGRADE_SERVER)) {
            serverNameKeyList.add(CURRENT_UPGRADE_SERVER);
            String currentUpgradeServerSp = ServerVSharedPrfs.getString(context,CURRENT_UPGRADE_SERVER,HTTP_PROTOCOL + CURRENT_UPGRADE_SERVER);
            serverNameListMap.put(CURRENT_UPGRADE_SERVER,currentUpgradeServerSp);
        }
        CURRENT_TVINFO_SERVER = SkyGeneralProperties.getProperty(SkyGeneralProperties.GeneralPropKey.CURRENT_TVINFO_SERVER);
        if(!TextUtils.isEmpty(CURRENT_TVINFO_SERVER)) {
            serverNameKeyList.add(CURRENT_TVINFO_SERVER);
            String currentTvInfoServerSp = ServerVSharedPrfs.getString(context,CURRENT_TVINFO_SERVER,HTTPS_PROTOCOL + CURRENT_TVINFO_SERVER);
            serverNameListMap.put(CURRENT_TVINFO_SERVER,currentTvInfoServerSp);
        }
        CURRENT_DEVICE_SERVER = SkyGeneralProperties.getProperty(SkyGeneralProperties.GeneralPropKey.CURRENT_DEVICE_SERVER);
        if(!TextUtils.isEmpty(CURRENT_DEVICE_SERVER)) {
            serverNameKeyList.add(CURRENT_DEVICE_SERVER);
            String currentDeviceServerSp = ServerVSharedPrfs.getString(context,CURRENT_DEVICE_SERVER,HTTP_PROTOCOL + CURRENT_DEVICE_SERVER);
            serverNameListMap.put(CURRENT_DEVICE_SERVER,currentDeviceServerSp);
        }
        {
            serverNameKeyList.add(SERVICE_AGREEMENT);
            String serviceAgreementSp = ServerVSharedPrfs.getString(context,SERVICE_AGREEMENT,SERVICE_AGREEMENT);
            serverNameListMap.put(SERVICE_AGREEMENT,serviceAgreementSp);
        }
        {
            serverNameKeyList.add(PRIVACY_POLICY);
            String privacyPolicySp = ServerVSharedPrfs.getString(context,PRIVACY_POLICY,PRIVACY_POLICY);
            serverNameListMap.put(PRIVACY_POLICY,privacyPolicySp);
        }
        {
            serverNameKeyList.add(MEMBERSHIP_CARD);
            String membershipCardSp = ServerVSharedPrfs.getString(context,MEMBERSHIP_CARD,HTTPS_PROTOCOL+MEMBERSHIP_CARD);
            serverNameListMap.put(MEMBERSHIP_CARD,membershipCardSp);
        }
        for (String key : serverNameListMap.keySet()) {
            SysLog.info(TAG,"first time,serverNameListMap key= "+ key + " and value= " + serverNameListMap.get(key));
        }
    }
    public static synchronized ServerVManager getInstance(Context context)
    {
        if(instance == null) {
            instance = new ServerVManager(context);
        }
        return instance;
    }
    public void handleCheckTask(Context context)
    {
        SysLog.error(TAG,"ServerVManager handleCheckTask");
        startGettingAllRunnable(context);
    }
    public String getServerName(final String key){
        String serverName = serverNameListMap.get(key);
        SysLog.info(TAG,"getServerName key:"+key+" res:"+serverName);
        return serverName;
    }

    public void addCallBack(ServiceNameCallBack serviceNameCallBack){
        if(serviceNameCallBack == null){
            return ;
        }
        serviceNameCallbackList.add(serviceNameCallBack);
    }
    private void startGettingAllRunnable(final Context context){
        checkTonewThreadPool();
        singleTask.execute(new Runnable()
        {
            @Override
            public void run()
            {
                SysLog.info(TAG,"begin getAllConfig");
                Map<String, List<String>>  config = ServerVerificationApi.getApi(context).getAllConfig();
            // shen 20191019  ,  NXXT-63935  ,print  config can cause java.util.ConcurrentModificationException at random ,comment it out
//              SysLog.info(TAG,"getAllConfig: "+config);
                if(config == null) {
                    return;
                }
                boolean hasChange = false;
                for(String serverNameKey: serverNameKeyList ) {
                    List<String> address = config.get(serverNameKey);
                    SysLog.info(TAG,"serverNameKey address: "+address);
                    if(address != null && !address.isEmpty() && !TextUtils.isEmpty(address.get(0))) {
                        String serverNameTmp = serverNameListMap.get(serverNameKey);
                            if(address.get(0).equals(serverNameTmp)){
                                SysLog.info(TAG," equals serverNameKey:"+ serverNameKey);
                                continue;
                            }
                            hasChange = true;
                            SysLog.info(TAG,"hasChange,serverNameKey:"+serverNameKey+"  value:"+address.get(0));
                            serverNameListMap.put(serverNameKey, address.get(0));
                            ServerVSharedPrfs.setString(context,serverNameKey,address.get(0));
                    }else {
                        SysLog.info(TAG,"serverNameKey "+serverNameKey+"  use default");
                    }
                }
                 if(hasChange && serviceNameCallbackList!=null){
                     for(ServiceNameCallBack serviceNameCallBack:serviceNameCallbackList){
                         serviceNameCallBack.notifyChanged();
                     }
                 }
            }
        });
    }


    private ServerConfigUpdateReceiver serverConfigUpdateReceiver = null;
    private class ServerConfigUpdateReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            SysLog.error(TAG, "onReceive "+action);
            if (action.equals(ACTION_ON_SERVER_CONFIG_UPDATE))
            {
                startGettingAllRunnable(context);
            }
        }
    }
    public void registerServerConfigUpdateReceiver(Context context)
    {
        if (serverConfigUpdateReceiver == null)
        {
            serverConfigUpdateReceiver = new ServerConfigUpdateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ON_SERVER_CONFIG_UPDATE);
        context.registerReceiver(serverConfigUpdateReceiver, filter);
    }

    public void unregisterServerConfigUpdateReceiver(Context context)
    {
        if (serverConfigUpdateReceiver != null)
        {
            try
            {
                context.unregisterReceiver(serverConfigUpdateReceiver);
                serverConfigUpdateReceiver = null;
            } catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void checkTonewThreadPool()
    {
        if (singleTask == null)
        {
            SysLog.info(TAG, "newThreadPool");
            singleTask = Executors.newSingleThreadExecutor();
        }
    }

    public void stopThreadPool()
    {
        if (singleTask != null)
        {
            SysLog.info(TAG, "stopThreadPool");
            singleTask.shutdownNow();
            singleTask = null;
        }
    }
}