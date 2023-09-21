package com.tianci.system.callback;

import com.tianci.loader.LoaderViewStatus;

public interface UpgradeCallback {
    void onUIChanged(LoaderViewStatus status);
}
