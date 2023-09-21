package com.tianci.net.plugin;

import com.skyworth.framework.skysdk.plugins.SkyPlugin;
import com.tianci.net.define.NetworkDefs;
import com.tianci.net.interfaces.NetPluginListener;
import com.tianci.net.netnode.TCNetNode;
import com.tianci.system.pluginFunction.BaseFunction;
import com.tianci.system.pluginFunction.PluginFunctionDefine;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * write something
 * </p>
 *
 * @author mikan
 * @version V1.0.0
 * @ClassName NetPlugin
 * @date 15/4/15
 */
public abstract class NetPlugin extends SkyPlugin
{
    /**
     * 概述：网络部分listener<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param listener
     *            void
     * @date 2014-6-26
     */
    public abstract void startPluginNetListener(NetPluginListener listener);

    /**
     * 概述：获取各连网节点功能类<br/>
     * 适用条件：<br/>
     * 执行流程：<br/>
     * 使用方法：<br/>
     * 注意事项：<br/>
     *
     * @param device
     * @return TCBaseNetNode
     * @date 2014-10-23
     */
    public abstract TCNetNode getNetNode(NetworkDefs.NetworkDevices device);

	/**
	 * 概述：获取plugin中各个工具类<br/>
	 *
	 * @return BaseFunction
	 * date 16/1/13
	 */
    public abstract BaseFunction getFunction(PluginFunctionDefine.FunctionName functionName);
}
