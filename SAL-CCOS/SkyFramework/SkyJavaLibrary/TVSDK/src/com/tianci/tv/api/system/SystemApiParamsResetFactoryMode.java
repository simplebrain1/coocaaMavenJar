package com.tianci.tv.api.system;

import com.tianci.tv.framework.api.SkyTvApiParams;

public class SystemApiParamsResetFactoryMode extends SkyTvApiParams
{
    /**
	 * 
	 */
    private static final long serialVersionUID = -8788635938769964659L;

    public boolean restorePresetChannel;

    public SystemApiParamsResetFactoryMode(boolean restorePresetChannel)
    {
        this.restorePresetChannel = restorePresetChannel;
    }
}
