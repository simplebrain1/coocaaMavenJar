package com.tianci.tv.api.system;

import com.tianci.tv.define.SkyTvDefine.SwitchSourceType;
import com.tianci.tv.define.object.Source;
import com.tianci.tv.framework.api.SkyTvApiParams;

public class SystemApiParamsOnSwitchSourceDone extends SkyTvApiParams
{

    /**
     * Description:
     */
    private static final long serialVersionUID = -6608628661942670137L;

    public Source from = null;
    public Source to = null;
    public SwitchSourceType type = SwitchSourceType.BY_COMMON;

    public SystemApiParamsOnSwitchSourceDone(Source from, Source to)
    {
        this.from = from;
        this.to = to;
    }
}
