package com.tianci.tv.api.system;

import com.tianci.tv.define.object.Source;
import com.tianci.tv.framework.api.SkyTvApiParams;

public class SystemApiParamEnableExternalSource extends SkyTvApiParams
{

    /**
     * Description:
     */
    private static final long serialVersionUID = 4215228008690105090L;

    public Source source = null;
    public boolean val = false;

    public SystemApiParamEnableExternalSource(Source source, boolean val)
    {
        this.source = source;
        this.val = val;
    }
}
