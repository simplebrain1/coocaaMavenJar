package com.skyworth.framework.utils.internel.log;

import android.util.Log;

import java.util.Hashtable;

/**
 * <p>Description:</p>
 * <p>Android log输出</p>
 *
 * @author CRuby
 * @version V1.0.0
 * @ClassName AndroidLogAppender
 * @date 2013-10-21
 */
public class AndroidLogAppender implements LogAppender {
    private Hashtable<String, String> nameMap;
    //private String tag;

    /**
     * TODO(初始化了一个hashtable<String,String>，用来缓存class name，以便快速的获取)<br/>
     * (创建hashtable的本意是在没有设置tag的时候，以类名作为tag打印android日志)<br/>
     */
    public AndroidLogAppender() {
        nameMap = new Hashtable<String, String>();
        //tag = logTag;
    }

    private String getClassName(String fullClassname) {
        String className = nameMap.get(fullClassname);
        if (className == null && fullClassname != null && fullClassname.length() > 0) {
            int pos = fullClassname.lastIndexOf('.');
            if (pos > 0) {
                className = fullClassname.substring(pos + 1);
            } else {
                className = fullClassname;
            }
            nameMap.put(fullClassname, className);
        }
        if (className == null) {
            className = String.valueOf(android.os.Process.myPid());
            nameMap.put(fullClassname, className);
        }
        return className;
    }

    /* (non-Javadoc)
     * @see com.skyworth.framework.skysdk.logger.LogAppender#logDebug(com.skyworth.framework.skysdk.logger.LogInfo)
     */
    public void logDebug(LogInfo msg) {
        // TODO Auto-generated method stub
        if (msg.tag != null) {
            Log.d(msg.tag, msg.toString());
        } else {
            Log.d(getClassName(msg.className), msg.toString());
        }
    }

    /* (non-Javadoc)
     * @see com.skyworth.framework.skysdk.logger.LogAppender#logError(com.skyworth.framework.skysdk.logger.LogInfo)
     */
    public void logError(LogInfo msg) {
        // TODO Auto-generated method stub
        if (msg.tag != null) {
            Log.e(msg.tag, msg.toString());
        } else {
            Log.e(getClassName(msg.className), msg.toString());
        }
    }

    /* (non-Javadoc)
     * @see com.skyworth.framework.skysdk.logger.LogAppender#logInfo(com.skyworth.framework.skysdk.logger.LogInfo)
     */
    public void logInfo(LogInfo msg) {
        // TODO Auto-generated method stub
        if (msg.tag != null) {
            Log.i(msg.tag, msg.toString());
        } else {
            Log.i(getClassName(msg.className), msg.toString());
        }
    }

    /* (non-Javadoc)
     * @see com.skyworth.framework.skysdk.logger.LogAppender#logWarning(com.skyworth.framework.skysdk.logger.LogInfo)
     */
    public void logWarning(LogInfo msg) {
        // TODO Auto-generated method stub
        if (msg.tag != null) {
            Log.w(msg.tag, msg.toString());
        } else {
            Log.w(getClassName(msg.className), msg.toString());
        }
    }
}

