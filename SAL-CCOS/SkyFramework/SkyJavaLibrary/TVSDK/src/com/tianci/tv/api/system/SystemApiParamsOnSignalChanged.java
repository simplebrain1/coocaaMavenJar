package com.tianci.tv.api.system;

import com.tianci.tv.define.SkyTvDefine.SOURCE_SIGNAL_STATE;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.framework.api.SkyTvApiParams;

public class SystemApiParamsOnSignalChanged extends SkyTvApiParams
{

    /**
     * Description:
     */
    private static final long serialVersionUID = 8893917186578723271L;

    public Source source = null;
    public SOURCE_SIGNAL_STATE state = null;

    public SystemApiParamsOnSignalChanged(Source source, SOURCE_SIGNAL_STATE state)
    {
        this.source = source;
        this.state = state;
    }
}
