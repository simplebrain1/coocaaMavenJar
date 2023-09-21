package com.skyworth.framework.utils;

import android.content.Context;
import android.text.TextUtils;

import com.skyworth.framework.config.GeneralConfig;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class AddonTextUtils {
    private final static String TAG = "addon";
    private static AddonTextUtils instance;
    private String resFilePATH = null;
    private HashMap<String, String> texts;
    private String packageName;

    public synchronized static AddonTextUtils getInstance(Context context) {
        if (instance == null) {
            instance = new AddonTextUtils(context);
        }
        return instance;
    }

    private AddonTextUtils(Context context) {
        if (context != null) {
            packageName = context.getPackageName();
        }
        texts = new HashMap<String, String>();
        initResFilePath();
        loadTexts();
    }

    private void initResFilePath() {
        String languageString = "chinese";
        if (Locale.getDefault().getLanguage().equals(Locale.ENGLISH.getLanguage()))
            languageString = "english";
        resFilePATH = GeneralConfig.getConfig(GeneralConfig.GeneralPropKey.ROCONFIGDIR) + "/addon/" + languageString + ".xml";
    }

    private void loadTexts() {
        if (!texts.isEmpty()) {
            LogUtil.e(TAG, "AddonText is not empty !");
            return;
        }
        LogUtil.e(TAG, "AddonText is empty ，start load xml !");
        try {
            loadXML();
        } catch (Exception e) {
            LogUtil.e(TAG, "Load addon file [" + resFilePATH + "] fail! " + e.toString());
            e.printStackTrace();
        }
    }

    private void loadXML() throws Exception {
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        if (docBuilder != null) {
            Document doc = docBuilder.parse(new File(resFilePATH));
            NodeList nodes = doc.getElementsByTagName("string");
            int index = 0;
            while (index < nodes.getLength()) {
                Node node = nodes.item(index);
                String id = node.getAttributes().getNamedItem("name").getTextContent();
                Node packageNameNode = node.getAttributes().getNamedItem("package");
                String packageName = null;
                if (packageNameNode != null) {
                    packageName = packageNameNode.getTextContent();
                }
                String value = node.getTextContent();
                LogUtil.e(TAG, "packageName:"+packageName);
                if (packageName != null && !packageName.equals(this.packageName)) {
                    ++index;
                    continue;
                }
                LogUtil.e(TAG, "id:"+id);
                LogUtil.e(TAG, "value:"+value);
                texts.put(id, value);
                ++index;
            }
        }
    }

    public String getText(String res_name) {
        String ret = "";
        if (!TextUtils.isEmpty(res_name) && texts != null && texts.containsKey(res_name))
            ret = texts.get(res_name);
        return ret;
    }

    public void localeChange() {
        if (texts != null){
            texts.clear();
        }
        initResFilePath();
        loadTexts();
    }

}
