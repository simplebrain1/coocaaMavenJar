package com.skyworth.framework.config;

import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.skyworth.framework.skysdk.android.SkySystemUtil;
import com.skyworth.framework.skysdk.properties.SkyGeneralProperties;
import com.skyworth.framework.skysdk.properties.SkyXmlHelper;
import com.skyworth.framework.utils.SystemPropertiesUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 2021/8/6
 *
 * @author whw
 */
public class SettingConfig {
    private static final String TAG = "SettingConfig whw";
    private static Set<String> mConfigMap = loadConfig();

    private static Set<String> loadConfig() {
        long beginTime = System.currentTimeMillis();
        Set<String> configs = new HashSet<String>();
        loadGeneralConfigXml(configs, getGeneralConfigPath());
        Log.d(TAG, (System.currentTimeMillis() - beginTime) + "ms");
        return configs;
    }

    private static String getGeneralConfigPath() {
        Log.d(TAG, "init general config path.");
        String generalConfigPath = "";
        StringBuilder sb;
        if (SkyGeneralProperties.isFactoryConfigExist()) {
            sb = new StringBuilder("/factory/pcfg/");
        } else {
            sb = new StringBuilder("/system/pcfg/");
        }
        String skyBaseModel = SystemProperties.get("ro.base.skymodel");
        String skyModel = TextUtils.isEmpty(skyBaseModel) ? SystemProperties.get("ro.build.skymodel") : skyBaseModel; //机芯，eg：8H81、
        String skyBaseType = SystemProperties.get("ro.base.skytype");
        String skyType = TextUtils.isEmpty(skyBaseType) ? SystemProperties.get("ro.build.skytype") : skyBaseType; //机型，eg：G9200、14A55
        sb.append(skyModel);
        sb.append("_");
        sb.append(skyType);
        String panelSize = SkySystemUtil.getTvPanelSize();
        Log.d(TAG, "panelSize=" + panelSize);
        if (!TextUtils.isEmpty(panelSize))
        {
            StringBuilder tmpPathSb = new StringBuilder();
            tmpPathSb.append(sb);
            tmpPathSb.append(File.separator + panelSize);
            if (new File(tmpPathSb.toString()).exists())
                sb = tmpPathSb;
            String panelParams = SystemPropertiesUtil.getProperty("third.get.panel.param");
            Log.d(TAG, "panel.param=" + panelParams);
            if(!TextUtils.isEmpty(panelParams)){
                StringBuilder tmpPathSbWithPanelParams = new StringBuilder();
                tmpPathSbWithPanelParams.append(tmpPathSb);
                tmpPathSbWithPanelParams.append("_");
                tmpPathSbWithPanelParams.append(panelParams);
                if (new File(tmpPathSbWithPanelParams.toString()).exists())
                    sb = tmpPathSbWithPanelParams;
            }
        }
        sb.append("/config");
        generalConfigPath = sb.toString();
        Log.d(TAG, "generalConfigPath:" + generalConfigPath);
        return generalConfigPath;
    }

    private static void loadGeneralConfigXml(Set<String> mProperties, String filePath) {
        String keyName = "name";
        String generalConfigFilePath = filePath.endsWith(File.separator) ? filePath + "setting_config.xml" : filePath + File.separator + "setting_config.xml";
        Log.d(TAG, "loadGeneralConfigXml from : " + generalConfigFilePath);
        File productfile = new File(generalConfigFilePath);
        if (!productfile.exists())
            return;
        Document generalConfigDoc = SkyXmlHelper.createDocFromFile(generalConfigFilePath);
        Node generalConfigRoot = generalConfigDoc.getFirstChild();
        while (true) {
            if (generalConfigRoot == null) {
                break;
            }
            if (generalConfigRoot.getNodeName().equals("#text") || generalConfigRoot.getNodeName().equals("#comment")) {
                generalConfigRoot = generalConfigRoot.getNextSibling();
            } else {
                break;
            }
        }
        if (generalConfigRoot == null) {
            return;
        }
        NodeList generalConfigItems = generalConfigRoot.getChildNodes();
        for (int i = 0; i < generalConfigItems.getLength(); i++) {
            Node item = generalConfigItems.item(i);
            if (item.getNodeName() != null && !(item.getNodeName().equals("#text") || item.getNodeName().equals("#comment"))) {
                if (item.getAttributes().getNamedItem(keyName) == null) {
                    continue;
                }
                if (mProperties != null) {
                    if (mProperties.contains(item.getAttributes().getNamedItem(keyName).getNodeValue())) {
                        Log.d("Same ", item.getAttributes().getNamedItem(keyName).getNodeValue());
                    }
                    mProperties.add(item.getAttributes().getNamedItem(keyName).getNodeValue());
                }
            }
        }
        Log.d(TAG, "total : " + mProperties.size());
    }

    /**
     * 获取系统配置值
     *
     * @param key 配置名称
     */
    public static boolean haveXmlConfig(String key) {
        return mConfigMap.contains(key);
    }

    /**
     * 获取系统配置值
     *
     * @param keys 配置名称
     */
    public static boolean haveXmlConfigOne(String... keys) {
        if (keys != null && keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                if (mConfigMap.contains(keys[i])) return true;
            }
        }
        return false;
    }

}
