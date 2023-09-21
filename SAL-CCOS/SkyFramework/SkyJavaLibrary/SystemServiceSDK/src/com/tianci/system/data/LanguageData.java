/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2014-6-30          mikan
 *
 */

package com.tianci.system.data;

import java.io.Serializable;

public class LanguageData implements Serializable
{

    /**
     * Description:
     */
    private static final long serialVersionUID = 8584254054076017560L;
    
    private final String mLanguage;
    private final String mCountry;

    public LanguageData(String language, String country)
    {
        mLanguage = language;
        mCountry = country;
    }

    public String getLanguage()
    {
        return mLanguage;
    }

    public String getCountry()
    {
        return mCountry;
    }
}
