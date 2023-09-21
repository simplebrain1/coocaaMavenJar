package com.skyworth.framework.config;

import android.text.TextUtils;
import android.util.Log;

import com.skyworth.framework.utils.SystemPropertiesUtil;
import com.skyworth.framework.utils.XmlHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取系统配置值
 *
 * @since 1
 */
public class GeneralConfig {
    private static final String TAG = "pcfg";
    private static Boolean isFactoryConfigExist = null;
    private static Map<String, String> mConfigMap = loadConfig();

    /**
     * 各类型数据路径配置
     */
    public enum GeneralPropKey {
        /**
         * 可读写目录
         */
        RWDIR,

        /**
         * plugin路径
         */
        ROPLATFORMPLUGINDIR,

        /**
         * 只读数据库路径
         */
        RODBDIR,

        /**
         * 只读菜单路径
         */
        ROCONFIGDIR
    }

    private static Map<String, String> loadConfig() {
        Map<String, String> configs = loadDefaultDirDef();
        loadGeneralConfigXml(configs, configs.get(GeneralPropKey.ROCONFIGDIR.toString()));
        Log.d(TAG, "configs.size():" + configs.size());
        return configs;
    }

    private static Map<String, String> loadDefaultDirDef() {
        Map<String, String> defDirConfigs = new HashMap<String, String>();
        defDirConfigs.put(GeneralPropKey.RWDIR.toString(), "/data/ccos");
        defDirConfigs.put(GeneralPropKey.ROPLATFORMPLUGINDIR.toString(), "/system/plugins");
        defDirConfigs.put(GeneralPropKey.RODBDIR.toString(), "/system/etc");
        defDirConfigs.put(GeneralPropKey.ROCONFIGDIR.toString(), getGeneralConfigPath());
        Log.d(TAG, "defDirConfigs:" + defDirConfigs);
        Log.d(TAG, "defDirConfigs.size():" + defDirConfigs.size());
        return defDirConfigs;
    }

    private static String getGeneralConfigPath() {
        Log.d(TAG, "init general config path.");
        String generalConfigPath = "";
        StringBuilder sb;
        if(isFactoryConfigExist()){
            sb = new StringBuilder("/factory/pcfg/");
        }else {
            sb = new StringBuilder("/system/pcfg/");
        }
        String skyBaseModel = SystemPropertiesUtil.getProperty("ro.base.skymodel");
        String skyModel = TextUtils.isEmpty(skyBaseModel) ? SystemPropertiesUtil.getProperty("ro.build.skymodel") : skyBaseModel; //机芯，eg：8H81、
        String skyBaseType = SystemPropertiesUtil.getProperty("ro.base.skytype");
        String skyType = TextUtils.isEmpty(skyBaseType) ? SystemPropertiesUtil.getProperty("ro.build.skytype") : skyBaseType; //机型，eg：G9200、14A55
        sb.append(skyModel);
        sb.append("_");
        sb.append(skyType);
        sb.append("/config");
        generalConfigPath = sb.toString();
        Log.d(TAG, "generalConfigPath:" + generalConfigPath);
        return generalConfigPath;
    }

    private static void loadGeneralConfigXml(Map<String, String> mProperties, String filePath) {
        String mNamekey = "name";
        String mValueKey = "value";
        String generalConfigFilePath = filePath.endsWith(File.separator) ? filePath + "general_config.xml" : filePath + File.separator + "general_config.xml";
        Log.d(TAG, "loadGeneralConfigXml from : " + generalConfigFilePath);
        File productfile = new File(generalConfigFilePath);
        if (!productfile.exists())
            return;
        Document generalConfigDoc = XmlHelper.createDocFromFile(generalConfigFilePath);
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
                if (item.getAttributes().getNamedItem(mNamekey) == null) {
                    continue;
                }
                if (mProperties != null) {
                    mProperties.put(item.getAttributes().getNamedItem(mNamekey).getNodeValue(), item.getAttributes().getNamedItem(mValueKey).getNodeValue());
                }
            }
        }
    }

    /**
     * 获取系统配置值
     * 注意事项：key不存在，则返回null
     *
     * @param key 配置名称
     * @return String 配置值
     * @since 1
     */
    static public String getConfig(String key) {
        Log.d(TAG, "key:" + key);
        Log.d(TAG, "mConfigMap.get(key):" + mConfigMap.get(key));
        if (!mConfigMap.containsKey(key)) {
            Log.d(TAG, "mConfigMap.get(key)nn:" + mConfigMap.get(key));
            return null;
        }
        return mConfigMap.get(key);
    }

    /**
     * 概述：根据属性Key获取属性值
     *
     * @param key
     * @return String
     * @see GeneralPropKey
     * @since 1
     */
    public static String getConfig(GeneralPropKey key) {
        return getConfig(key.toString());
    }

    /**
     * 获取系统配置的整型值
     * 适用条件：适用于整型配置值
     * 注意事项：此项配置必须为整型，否则返回def
     *
     * @param key 配置名称
     * @param def key不存在，或者value格式错误时的默认值
     * @return int 配置值
     * @since 1
     */
    static public int getIntConfig(String key, int def) {
        String value = getConfig(key);
        if (value == null) {
            return def;
        }
        int val = def;
        try {
            val = Integer.parseInt(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    /**
     * 获取系统配置的布尔值
     * 适用条件：适用于布尔配置值
     * 注意事项：此项配置必须为布尔值，否则返回false
     *
     * @param key 配置名称
     * @param def key不存在时的默认值
     * @return boolean 配置值
     * @since 1
     */
    static public boolean getBoolConfig(String key, boolean def) {
        String value = getConfig(key);
        return Boolean.parseBoolean(value);
    }

    /**
     * 获取系统配置列表
     *
     * @return List 配置列表
     * @since 1
     */
    public static List<String> getConfigList() {
        return new ArrayList<String>(mConfigMap.keySet());
    }
    public static boolean isFactoryConfigExist(){
        if(isFactoryConfigExist == null){
            File factoryConfig = new File("/factory/pcfg");
            isFactoryConfigExist = factoryConfig.exists();
        }
        Log.d(TAG,"isFactoryConfigExist = "+ isFactoryConfigExist);
        return isFactoryConfigExist;
    }
}
