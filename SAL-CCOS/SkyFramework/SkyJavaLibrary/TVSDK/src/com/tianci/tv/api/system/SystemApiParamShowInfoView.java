package com.tianci.tv.api.system;

import com.tianci.tv.framework.api.SkyTvApiParams;

import java.util.List;

public class SystemApiParamShowInfoView extends SkyTvApiParams
{
    /**
     * Description:
     */
    private static final long serialVersionUID = -8236722676582980724L;

    public static class InfoViewData extends SkyTvApiParams
    {
        /**
         * Description:
         */
        private static final long serialVersionUID = -4254521820262968261L;
        public String title, info;

        public InfoViewData(String title, String info)
        {
            this.title = title;
            this.info = info;
        }
    }

    public String title = null;
    public List<InfoViewData> data = null;

    public SystemApiParamShowInfoView(String title, List<InfoViewData> data)
    {
        this.title = title;
        this.data = data;
    }
}