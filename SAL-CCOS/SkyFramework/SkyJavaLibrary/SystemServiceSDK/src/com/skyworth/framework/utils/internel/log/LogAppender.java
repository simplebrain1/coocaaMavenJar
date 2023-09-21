package com.skyworth.framework.utils.internel.log;

/**
 * <p>Description:</p>
 * <p>Log基本接口，并添加了LogServer接口，将日志输出到pushService</p>
 *
 * @author CRuby
 * @version V1.0.0
 * @ClassName LogAppender
 * @date 2013-10-22
 */
public interface LogAppender {
    /**
     * 概述：输出Debug信息<br/>
     *
     * @param msg void
     * @date 2013-10-22
     */
    public void logDebug(LogInfo msg);

    /**
     * 概述：输出Info信息<br/>
     *
     * @param msg void
     * @date 2013-10-22
     */
    public void logInfo(LogInfo msg);

    /**
     * 概述：输出Warning信息<br/>
     *
     * @param msg void
     * @date 2013-10-22
     */
    public void logWarning(LogInfo msg);

    /**
     * 概述：输出error信息<br/>
     *
     * @param msg void
     * @date 2013-10-22
     */
    public void logError(LogInfo msg);
}
