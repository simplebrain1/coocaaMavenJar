package com.tianci.net.netnode;

import android.text.TextUtils;

import com.skyworth.framework.skysdk.properties.SkySystemProperties;
import com.tianci.system.utils.SysLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IPV6Util {

    private static final String TAG = "IPV6Util";
    private static final String GET_IPV6_CMD = "/system/bin/ip -6 addr show ";
    private static final int IPV6LEN_LEFT_BIT  = 6;
    private static final int IPV6LEN_RIGHT_BIT = 1;
//    private static final String GET_IPV6_GATEWAY_CMD = "busybox ip -6 route show dev ";
    private static final String GET_IPV6_GATEWAY_CMD = "ip route list table 0 dev ";

    private static final int IPV6_GATEWAY_LEN_RIGHT_BIT = 3;

    private static final String GET_IPV6_DNS_CMD = "ip route list table 0 dev ";
    private static final  String  DNS_PRESTR = "default via";

    private  static class LazyHolder {
        private static IPV6Util INSTANCE = new IPV6Util();
    }

    public static IPV6Util getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * params：netName，such as：eth0、wlan0
     * */
    public String getIpV6Address(String iface) {
        SysLog.info(TAG, "get " + iface + " ipv6 address.");
        return  getIpv6Addr(GET_IPV6_CMD + iface);
    }
    public List<String> getIpv6Dns(String iface){
        return getIpv6Dns(GET_IPV6_DNS_CMD,iface);
    }
    private List<String> getIpv6DnsByProperty(){
        List<String> ipv6DnsList = new ArrayList<String>() ;
        String ipv6Dns = SkySystemProperties.getProperty("net.dns1");
        IP_TYPE ipType = validIPAddressType(ipv6Dns);
        SysLog.info(TAG, "ipv6Dns1:" + ipv6Dns+"  type:"+ipType);
        if(IP_TYPE.IPV6_TYPE == ipType){
            ipv6DnsList.add(ipv6Dns);
        }
        ipv6Dns = SkySystemProperties.getProperty("net.dns2");
        ipType = validIPAddressType(ipv6Dns);
        SysLog.info(TAG, "ipv6Dns2:" + ipv6Dns+"  type:"+ipType);
        if(IP_TYPE.IPV6_TYPE == ipType){
            ipv6DnsList.add(ipv6Dns);
        }
        ipv6Dns = SkySystemProperties.getProperty("net.dns3");
        ipType = validIPAddressType(ipv6Dns);
        SysLog.info(TAG, "ipv6Dns3:" + ipv6Dns+"  type:"+ipType);
        if(IP_TYPE.IPV6_TYPE == ipType){
            ipv6DnsList.add(ipv6Dns);
        }
        ipv6Dns = SkySystemProperties.getProperty("net.dns4");
        ipType = validIPAddressType(ipv6Dns);
        SysLog.info(TAG, "ipv6Dns4:" + ipv6Dns+"  type:"+ipType);
        if(IP_TYPE.IPV6_TYPE == ipType){
            ipv6DnsList.add(ipv6Dns);
        }
        return ipv6DnsList;
    }

    /**
     * params：netName，such as：eth0、wlan0
     * */
    public String getIpV6Gateway(String iface){
        return getIpv6Gateway(GET_IPV6_GATEWAY_CMD,iface);
    }

    private String getIpv6Addr(String command) {
        String ipv6AddrAll = "";

        SysLog.info(TAG, "getIpv6Addr cmd:" + command);
        String ipv6AddrScopeGlobalDynamic ="";
        String ipv6AddrBack = "";
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(command);
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while((line = br.readLine()) != null) {

                SysLog.info(TAG, "getIpv6Addr readLine:" + line);

                if((line.contains("inet6")) && (line.contains("scope"))) {
                    ipv6AddrAll = ipv6AddrAll+
                            line.substring(line.indexOf("inet6") + IPV6LEN_LEFT_BIT, line.lastIndexOf("scope") - IPV6LEN_RIGHT_BIT)+ "&";
                    if(line.contains("scope global dynamic")){
                        ipv6AddrScopeGlobalDynamic = line.substring(line.indexOf("inet6") + IPV6LEN_LEFT_BIT, line.lastIndexOf("scope global dynamic") - IPV6LEN_RIGHT_BIT);
                    }
                    if(line.contains("scope global tentative dynamic")){
                        ipv6AddrScopeGlobalDynamic = line.substring(line.indexOf("inet6") + IPV6LEN_LEFT_BIT, line.lastIndexOf("scope global tentative dynamic") - IPV6LEN_RIGHT_BIT);
                    }
                    if(line.contains("scope global")){
                        ipv6AddrBack = line.substring(line.indexOf("inet6") + IPV6LEN_LEFT_BIT, line.lastIndexOf("scope global") - IPV6LEN_RIGHT_BIT);
                    }

                }
            }
        } catch (IOException e) {
            ipv6AddrAll = "";
            SysLog.info(TAG, "get ip error" + e);
        }
        if(!TextUtils.isEmpty(ipv6AddrScopeGlobalDynamic))
            return ipv6AddrScopeGlobalDynamic;
        else
            return ipv6AddrBack;
    }

    private String getIpv6Gateway(String command,String iface) {
        String ipv6Gateway = "";
        SysLog.info(TAG, "getIpv6Gateway cmd:" + command+"   iface:"+iface);
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(command+iface);
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while((line = br.readLine()) != null) {
                SysLog.info(TAG, "getIpv6Gateway readLine:" + line);
                if((line.contains(":")) && (line.contains("/64"))) {
                    ipv6Gateway = line.substring(0, line.lastIndexOf("/64"));
                    if(!ipv6Gateway.contains("fe80")  && !ipv6Gateway.contains("fe00")){
                        break;
                    }
                }
            }
        } catch (IOException e) {
            ipv6Gateway = "";
            SysLog.info(TAG, "get getIpv6Gateway error" + e);
        }
        SysLog.info(TAG, "getIpv6Gateway:" + ipv6Gateway);
        return ipv6Gateway;
    }

    private List<String> getIpv6Dns(String command,String iface) {
        List<String> ipv6DnsList = new ArrayList<String>() ;
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(command+iface);
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while((line = br.readLine()) != null) {
                SysLog.info(TAG, "getIpv6Dns readLine:" + line);
                if(line.contains(DNS_PRESTR)) {
                    int defaultViaIndex = line.indexOf(DNS_PRESTR);
                    String dnsTmp = line.substring(defaultViaIndex+ DNS_PRESTR.length());
                    if(dnsTmp.contains("table")) {
                        dnsTmp = dnsTmp.substring(0, dnsTmp.lastIndexOf("table"));
                        dnsTmp = dnsTmp.replace(" ", "");
                        IP_TYPE ipType = validIPAddressType(dnsTmp);
                        SysLog.info(TAG, "type:"+ipType + " dnsTmp:"+dnsTmp);
                        if(IP_TYPE.IPV6_TYPE == ipType){
                            ipv6DnsList.add(dnsTmp);
                        }
                    }
                 }
             }
        } catch (IOException e) {
            SysLog.info(TAG, "get getIpv6Dns error" + e);
        }
        return ipv6DnsList;
    }

    private enum IP_TYPE {
        IPV4_TYPE,IPV6_TYPE,NONE
    }

    private IP_TYPE validIPAddressType(String IP) {
        if (!IP.contains(".") && !IP.contains(":")) {
            return IP_TYPE.NONE;
        }
        //如果是IPV4
        if (IP.contains(".")) {

            if (IP.endsWith(".")) {
                return IP_TYPE.NONE;
            }
            String[] arr = IP.split("\\.");
            if (arr.length != 4) {
                return IP_TYPE.NONE;
            }
            for (int i = 0; i < 4; i++) {
                if (arr[i].length() == 0 || arr[i].length() > 3) {
                    return IP_TYPE.NONE;
                }
                for (int j = 0; j < arr[i].length(); j++) {
                    if (arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') {
                        continue;
                    }
                    return IP_TYPE.NONE;
                }
                if (Integer.valueOf(arr[i]) > 255 || (arr[i].length() >= 2 && String.valueOf(arr[i]).startsWith("0"))) {
                    return IP_TYPE.NONE;
                }
            }
            return IP_TYPE.IPV4_TYPE;
        }//如果是IPV4

        //如果是IPV6
        if (IP.contains(":")) {
            if (IP.endsWith(":") && !IP.endsWith("::")) {
                return IP_TYPE.NONE;
            }
            //如果包含多个“::”，一个IPv6地址中只能出现一个“::”
            if (IP.indexOf("::") != -1 && IP.indexOf("::", IP.indexOf("::") + 2) != -1) {
                return IP_TYPE.NONE;
            }
            //如果含有一个“::”
            if (IP.contains("::")) {
                String[] arr = IP.split(":");
                if (arr.length > 7 || arr.length < 1) {//"1::"是最短的字符串
                    return IP_TYPE.NONE;
                }
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals("")) {
                        continue;
                    }
                    if (arr[i].length() > 4) {
                        return IP_TYPE.NONE;
                    }
                    for (int j = 0; j < arr[i].length(); j++) {
                        if ((arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') || (arr[i].charAt(j) >= 'A' && arr[i].charAt(j) <= 'F')
                                || (arr[i].charAt(j) >= 'a' && arr[i].charAt(j) <= 'f')) {
                            continue;
                        }
                        return IP_TYPE.NONE;
                    }
                }
                return IP_TYPE.IPV6_TYPE;
            }
            //如果不含有“::”
            if (!IP.contains("::")) {
                String[] arr = IP.split(":");
                if (arr.length != 8) {
                    return IP_TYPE.NONE;
                }
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].length() > 4) {
                        return IP_TYPE.NONE;
                    }
                    for (int j = 0; j < arr[i].length(); j++) {
                        if ((arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') || (arr[i].charAt(j) >= 'A' && arr[i].charAt(j) <= 'F')
                                || (arr[i].charAt(j) >= 'a' && arr[i].charAt(j) <= 'f')) {
                            continue;
                        }
                        return IP_TYPE.NONE;
                    }
                }
                return IP_TYPE.IPV6_TYPE;
            }
        }//如果是IPV6
        return IP_TYPE.NONE;
    }
}