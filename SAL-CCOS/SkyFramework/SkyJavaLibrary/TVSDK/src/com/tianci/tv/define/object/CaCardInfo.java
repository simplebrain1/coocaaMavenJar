package com.tianci.tv.define.object;

/**
 * Created by hq on 2017/11/28.
 */

public class CaCardInfo {
    // CA Type
    public static final int CA_TYPE_UNKNOWN = -1;
    public static final int CA_TYPE_NO = 0;
    public static final int CA_TYPE_IRDETO = 1;
    public static final int CA_TYPE_QHTF = 2;
    public static final int CA_TYPE_CTI = 3;
    public static final int CA_TYPE_NDS = 4;
    public static final int CA_TYPE_BETACRYPT = 5;
    public static final int CA_TYPE_VIACCESS = 6;
    public static final int CA_TYPE_SECA = 7;
    public static final int CA_TYPE_NAGRA = 8;
    public static final int CA_TYPE_CONAX = 9;
    public static final int CA_TYPE_SMSX = 10;
    public static final int CA_TYPE_HTDM = 11;
    public static final int CA_TYPE_LATENS = 12;
    public static final int CA_TYPE_DVN = 13;
    public static final int CA_TYPE_COMVENIENT = 14;
    public static final int CA_TYPE_OTHER = 15;

    // CA Status
    public static final int CA_STATUS_UNKNOWN = -1;
    public static final int CA_STATUS_SMC_IN = 1;
    public static final int CA_STATUS_SMC_OUT = 2;
    public static final int CA_STATUS_SMC_ERROR = 3;

    public int cardType = CA_TYPE_UNKNOWN;
    public int cardStatus = CA_STATUS_UNKNOWN;
    public String cardNumber = "";

    @Override
    public String toString() {
        return "cardType:" + cardType + ", cardStatus:" + cardStatus + ", cardNumber:" + cardNumber;
    }
}
