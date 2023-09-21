/**
 * Copyright (C) 2012 The SkyTvOS Project
 * <p>
 * Version     Date           Author
 * ─────────────────────────────────────
 * 2013-10-11         thinkhwa
 */

package com.skyworth.framework.utils.internel;

import java.io.UnsupportedEncodingException;

public class StringEncoder {
    /**
     * 概述：判断字符串是否为UTF8编码<br/>
     *
     * @param charSequence 预判断的字符串
     * @return boolean
     * @date 2013-10-14
     */

    public static boolean isUTF8(CharSequence charSequence) {
        byte[] utf;
        try {
            utf = ((String) charSequence).getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        int ix;
        char c;
        /*
         * utf is a string of 1, 2, 3 or 4 bytes.  The valid strings
         * are as follows (in "bit format"):
         *    0xxxxxxx                                      valid 1-byte
         *    110xxxxx 10xxxxxx                             valid 2-byte
         *    1110xxxx 10xxxxxx 10xxxxxx                    valid 3-byte
         *    11110xxx 10xxxxxx 10xxxxxx 10xxxxxx           valid 4-byte
         */
        for (ix = 0; ix < utf.length; ) {
            c = (char) utf[ix];
            /* string is 0-terminated */
            if ((c & 0x80) == 0x00) { /* 1-byte code, starts with 10 */
                ix++;
            } else if ((c & 0xe0) == 0xc0) {  /* 2-byte code, starts with 110 */
                if (utf.length < 2) {
                    return false;
                }
                if ((ix+1<utf.length) && ((utf[ix + 1] & 0xc0) != 0x80)) {
                    return false;
                }
                ix += 2;
            } else if ((c & 0xf0) == 0xe0) {  /* 3-byte code, starts with 1110 */
                if (utf.length < 3) {
                    return false;
                }
                if ( ((ix+1<utf.length) && ((utf[ix + 1] & 0xc0) != 0x80)) ||
                        ((ix+2<utf.length) && ((utf[ix + 2] & 0xc0) != 0x80)) ){
                    return false;
                }
                ix += 3;
            } else if ((c & 0xf8) == 0xf0) {
                /* 4-byte code, starts with 11110 */
                if (utf.length < 4) {
                    return false;
                }
                if ( ((ix+1<utf.length) && ((utf[ix + 1] & 0xc0) != 0x80)) ||
                        ((ix+2<utf.length) && ((utf[ix + 2] & 0xc0) != 0x80)) ||
                        ((ix+3<utf.length) && ((utf[ix + 3] & 0xc0) != 0x80)) ) {
                    return false;
                }
                ix += 4;
            } else if ((c & 0xFB) == 0xF8) {
                /* 4-byte code, starts with 11110 */
                if (utf.length < 5) {
                    return false;
                }
                if (((ix+1<utf.length) && ((utf[ix + 1] & 0xc0) != 0x80)) ||
                        ((ix+2<utf.length) && ((utf[ix + 2] & 0xc0) != 0x80)) ||
                        ((ix+3<utf.length) && ((utf[ix + 3] & 0xc0) != 0x80)) ||
                        ((ix+4<utf.length) && ((utf[ix + 4] & 0xc0) != 0x80))) {
                    return false;
                }
                ix += 5;
            } else              /* unknown encoding */ {
                return false;
            }
        }
        return true;
    }


    /**
     * 概述：将指定的字符按照targetCharSet来进行编码 <br/>
     *
     * @param charSequence
     * @param targetCharSet
     * @return String：指定的字符targetCharSet编码后得到的字符串
     * @date 2013-10-14
     */
    public static String convertString(CharSequence charSequence, String targetCharSet) {
        try {
            return new String(((String) charSequence).getBytes("ISO-8859-1"), targetCharSet);
        } catch (UnsupportedEncodingException e) {
            //Logger.warning("failed to convert string [" + original + "] to " + targetCharSet);
            return (String) charSequence;
        }
    }
}
