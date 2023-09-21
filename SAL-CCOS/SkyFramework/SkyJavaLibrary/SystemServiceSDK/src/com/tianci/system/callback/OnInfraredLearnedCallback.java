package com.tianci.system.callback;

/**
 * 红外学习的回调
 */
public interface OnInfraredLearnedCallback {

    void onInfraredLearnedFailed(int reason);

    void onInfraredLearnedSuccess(byte[] code);
}
