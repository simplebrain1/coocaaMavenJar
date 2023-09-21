package com.tianci.net.interfaces;

public interface ISkyMobileNet {
    public long TIMESTAMP_MS_BASE = 1595001600000L;  // 20200718  to judge is the system time right. Especially net is not connected
     enum SIM_OPERATOR {
        CHINA_MOBILE,CHINA_TELECOM,CHINA_UNICOM ,NO_SIM_CARD ,SIM_CARD_ERROR,UNKNOWN_SIM_CARD,
    }
    enum SIM_STATE {
        SIM_STATE_READY,SIM_STATE_ABSENT,SIM_STATE_NETWORK_LOCKED,SIM_STATE_PUK_REQUIRED,SIM_STATE_PIN_REQUIRED,SIM_STATE_UNKNOWN
    }
    SIM_STATE getSimCardState();
    SIM_OPERATOR getPhoneOperator() ;
}
