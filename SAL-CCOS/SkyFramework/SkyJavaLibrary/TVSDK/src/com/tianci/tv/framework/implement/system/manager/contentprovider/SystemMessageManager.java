package com.tianci.tv.framework.implement.system.manager.contentprovider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.tianci.tv.define.object.CECDeviceInfo;
import com.tianci.tv.define.object.CaCardInfo;
import com.tianci.tv.framework.implement.system.listener.OnCACardInfoListener;
import com.tianci.tv.framework.implement.system.listener.OnCECDeviceChangedListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hq on 2017/11/28.
 */

public class SystemMessageManager {
    public static final String CA_CARD_INFO = "ca_card_info";
    public static final String CEC_DEVICE_CHANGED_INFO = "CEC_DEVICE_CHANGED_INFO";

    private static SystemMessageManager mInstance;

    private OnCACardInfoListener mOnCACardInfoListener;
    private OnCECDeviceChangedListener OnCecDeviceChangedListener;

    public static synchronized SystemMessageManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SystemMessageManager(context);
        }
        return mInstance;
    }

    private SystemMessageManager(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CA_CARD_INFO);
        intentFilter.addAction(CEC_DEVICE_CHANGED_INFO);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (CA_CARD_INFO.equals(intent.getAction())) {
                    if (mOnCACardInfoListener != null) {
                        int cardType = intent.getIntExtra("cardType", CaCardInfo.CA_TYPE_UNKNOWN);
                        int cardStatus = intent.getIntExtra("cardStatus", CaCardInfo.CA_STATUS_UNKNOWN);
                        String cardNumber = intent.getStringExtra("cardNumber");
                        CaCardInfo mCardInfo = new CaCardInfo();
                        mCardInfo.cardType = cardType;
                        mCardInfo.cardStatus = cardStatus;
                        mCardInfo.cardNumber = cardNumber;
                        mOnCACardInfoListener.onCACardInfoChanged(mCardInfo);
                    }
                }else if (CEC_DEVICE_CHANGED_INFO.equals(intent.getAction())){
                    if (OnCecDeviceChangedListener != null){
                        ArrayList<CECDeviceInfo> cecDevicesInfo
                                = intent.getParcelableArrayListExtra("CECDevicesInfo");
                        OnCecDeviceChangedListener.onChange(cecDevicesInfo);
                    }
                }
            }
        };
        context.registerReceiver(receiver, intentFilter);
    }

    public boolean setOnCaCardInfoListener(OnCACardInfoListener listener) {
        mOnCACardInfoListener = listener;
        return true;
    }

    public boolean setOnDeviceChangedListener(OnCECDeviceChangedListener listener){
        this.OnCecDeviceChangedListener = listener;
        return true;
    }
}
