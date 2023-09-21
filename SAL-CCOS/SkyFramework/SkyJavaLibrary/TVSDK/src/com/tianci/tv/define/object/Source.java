package com.tianci.tv.define.object;

import com.tianci.tv.define.SkyTvDefine.SOURCE_SIGNAL_STATE;
import com.tianci.tv.utils.SkyTvCache;

import java.util.List;

public class Source extends SkyTvObject
{
    /**
     * Description:
     */
    private static final long serialVersionUID = 5022920626572037799L;

    // 为外部扩展通道使用
    public static final int EXTERNAL_TYPE_MASK = 0xFFFF0000;
    // 媒体通道
    public static final int EXTERNAL_TYPE_MEDIA = 0xFFFF0000;
    // 腾龙通道
    public static final int EXTERNAL_TYPE_DRAGONTEC = 0x10000;
    public static final int EXTERNAL_DRAGONTEC_ALL = EXTERNAL_TYPE_DRAGONTEC + 0;
    public static final int EXTERNAL_DRAGONTEC_T = EXTERNAL_TYPE_DRAGONTEC + 1;
    public static final int EXTERNAL_DRAGONTEC_BS = EXTERNAL_TYPE_DRAGONTEC + 2;
    public static final int EXTERNAL_DRAGONTEC_BS4K = EXTERNAL_TYPE_DRAGONTEC + 3;
    public static final int EXTERNAL_DRAGONTEC_CS = EXTERNAL_TYPE_DRAGONTEC + 4;
    public static final int EXTERNAL_DRAGONTEC_CS4K = EXTERNAL_TYPE_DRAGONTEC + 5;

    public enum SOURCE_NAME_ENUM
    {
        HDMI, AV, ATV, DVBC, DTMB, YUV, VGA, VOD, REMEMBER, EXTERNAL, IPLIVE    
        // IPLIVE corresponding to independent Apk. shen 2016,0921
    }

    public static final int FLAG_CEC_NORMAL = 0x00;
    public static final int FLAG_CEC_SWITCH = 0x01;
    public static final int FLAG_CEC_RENAME = 0x02;

    private static SkyTvCache<String, Source> sourceCache = new SkyTvCache<String, Source>();
    public boolean bILiveSource = false;
    public boolean bRecognizedSource = false;
    public boolean bOnlySourceNameEnum = false;
    public String bHDMIResolution = "";
    public boolean isHDMISupportARC = false;
    public String bHDMIVersionName = "";
    public int flag = FLAG_CEC_NORMAL;

    private synchronized static Source getSource(String name)
    {
        Source source = sourceCache.get(name);
        if (source == null)
        {
            source = new Source(name);
            sourceCache.add(name, source);
        }

        return source;
    }

    private static Source getSource(String name, int index)
    {
        Source source = sourceCache.get(name + index);
        if (source == null)
        {
            source = new Source(name, index);
            sourceCache.add(name + index, source);
        }
        return source;
    }

    private static Source getSource(String name, String tag)
    {
        Source source = sourceCache.get(name + tag);
        if (source == null)
        {
            source = new Source(name, tag);
            sourceCache.add(name + tag, source);
        }
        return source;
    }

    public static boolean isInternalSource(Source source)
    {
        SOURCE_NAME_ENUM[] names = SOURCE_NAME_ENUM.values();
        if (source.equals(Source.External("iplive")))
            return true;
        for (SOURCE_NAME_ENUM name : names)
        {
            if (name != SOURCE_NAME_ENUM.EXTERNAL && name.toString().equals(source.name))
                return true;
        }
        return false;
    }

    public static boolean isExternalSource(Source source)
    {
        return (SOURCE_NAME_ENUM.valueOf(source.name) == SOURCE_NAME_ENUM.EXTERNAL);
    }

    public static Source getSourceByName(String name)
    {
        int i = name.lastIndexOf("@");
        String n = name;
        int index = -1;
        if (i != -1)
        {
            index = Integer.valueOf(name.substring(i + 1));
            n = name.substring(0, i);
        }
        SOURCE_NAME_ENUM name_enum = SOURCE_NAME_ENUM.ATV;
        try
        {
            name_enum = SOURCE_NAME_ENUM.valueOf(n);
        } catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            return null;
        }
        switch (name_enum)
        {
            case ATV:
                return ATV();
            case DVBC:
                return DVBC();
            case DTMB:
                return DTMB();
            case AV:
                if (index != -1)
                    return AV(index);
                else
                    return AV(DEFAULT_INDEX);
            case HDMI:
                if (index != -1)
                    return HDMI(index);
                else
                    return HDMI(DEFAULT_INDEX);
            case YUV:
                if (index != -1)
                    return YUV(index);
                else
                    return YUV(DEFAULT_INDEX);
            case VGA:
                if (index != -1)
                    return VGA(index);
                else
                    return VGA(DEFAULT_INDEX);
            case IPLIVE:
                return IPLive();
            default:
                return null;
        }
    }

    public static Source ATV()
    {
        return getSource(SOURCE_NAME_ENUM.ATV.toString());
    }

    public static Source DVBC()
    {
        return getSource(SOURCE_NAME_ENUM.DVBC.toString());
    }

    public static Source DTMB()
    {
        return getSource(SOURCE_NAME_ENUM.DTMB.toString());
    }

    public static Source AV(int index)
    {
        return getSource(SOURCE_NAME_ENUM.AV.toString(), index);
    }

    public static Source HDMI(int index)
    {
        return getSource(SOURCE_NAME_ENUM.HDMI.toString(), index);
    }

    public static Source YUV(int index)
    {
        return getSource(SOURCE_NAME_ENUM.YUV.toString(), index);
    }

    public static Source VGA(int index)
    {
        return getSource(SOURCE_NAME_ENUM.VGA.toString(), index);
    }

    public static Source IPLive()
    {
        return getSource(SOURCE_NAME_ENUM.IPLIVE.toString());
    }

    //
    // public static Source IPTV()
    // {
    // return getSource(SOURCE_NAME_ENUM.IPTV.toString());
    // }

    public static Source Remember()
    {
        return getSource(SOURCE_NAME_ENUM.REMEMBER.toString());
    }

    public static Source VOD()
    {
        Source ret = getSource(SOURCE_NAME_ENUM.VOD.toString());
        ret.isFakeSource = true;
        return ret;
    }

    /**
     * 概述：生成外部通道<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     * 
     * @param tag
     *            外部通道自定义得tag，最好特殊一点，以防重复
     * @return Source 生成得外部通道
     * @date 2014-2-26
     */

    public static final String EXTERNAL_DEFAULT = "external_default";

    public static Source External(String tag)
    {
        return getSource(SOURCE_NAME_ENUM.EXTERNAL.toString(), tag);
    }

    public static final int DEFAULT_INDEX = -1;

    public boolean isApk = false;

    public String pkgName = null;
    public List<String> dependencyPkgName = null;

    public int index = DEFAULT_INDEX;

    public SOURCE_SIGNAL_STATE signalState = SOURCE_SIGNAL_STATE.NOSIGNAL;

    /**
     * Description:该通道是否是假通道，如果是，则即使开启， 信号源列表也不会显示，切换到这个通道之后，系统当前通道 不会被置为此通道，而是保留之前得通道。VOD回看点播通道
     * 即使这种通道！
     * 
     */
    public boolean isFakeSource = false;

    public Source(String name)
    {
        super(Source.class.getName() + name + DEFAULT_INDEX, name);
        this.bILiveSource = isILiveSource();
        this.bRecognizedSource = isRecognizedSource();
        this.bOnlySourceNameEnum = false;
    }

    public Source(String name, int index)
    {
        super(Source.class.getName() + name + index, name);
        this.index = index;
        this.bILiveSource = isILiveSource();
        this.bRecognizedSource = isRecognizedSource();
        this.bOnlySourceNameEnum = false;
    }

    public Source(String name, String tag)
    {
        super(Source.class.getName() + name + tag + DEFAULT_INDEX, name);
        this.bILiveSource = isILiveSource();
        this.bRecognizedSource = isRecognizedSource();
        this.bOnlySourceNameEnum = false;
    }

    private boolean isILiveSource()
    {
        SOURCE_NAME_ENUM sourceNameEnum = getSourceNameEnum();
        switch (sourceNameEnum)
        {
            case ATV:
            case DVBC:
            case DTMB:
            case IPLIVE:
            case VOD:
                bILiveSource = true;
                break;
            case AV:
            case HDMI:
            case YUV:
            case VGA:
            default:
                bILiveSource = false;
                break;
        }
        return bILiveSource;
    }

    private boolean isRecognizedSource()
    {
        SOURCE_NAME_ENUM sourceNameEnum = getSourceNameEnum();
        switch (sourceNameEnum)
        {
            case ATV:
                bRecognizedSource = true;
                break;
            case DVBC:
            case DTMB:
            case IPLIVE:
            case VOD:
            case AV:
            case HDMI:
            case YUV:
            case VGA:
            default:
                bRecognizedSource = false;
                break;
        }
        return bRecognizedSource;
    }

    public void setPackageName(String pkgName)
    {
        this.pkgName = pkgName;
        if (pkgName != null)
            isApk = true;
        else
            isApk = false;
    }
    public SOURCE_NAME_ENUM getSourceNameEnum()
    {
        try
        {
            return SOURCE_NAME_ENUM.valueOf(name);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void doAfterDeserialize()
    {
        // TODO Auto-generated method stub

    }
    public static Source IndependentApk(String tag)
    {
        return getSource(SOURCE_NAME_ENUM.IPLIVE.toString(), tag);
    }
}
