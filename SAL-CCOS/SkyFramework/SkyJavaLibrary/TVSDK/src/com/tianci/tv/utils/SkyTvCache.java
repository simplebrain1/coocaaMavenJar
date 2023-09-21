/**
 * Copyright (C) 2012 The SkyTvOS Project
 *
 * Version     Date           Author
 * ─────────────────────────────────────
 *           2013-1-10         Root.Lu
 *
 */

package com.tianci.tv.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkyTvCache<Key, Value>
{
    public static class CacheItem<Key1, Value1>
    {
        public Key1 key = null;
        public Value1 value = null;

        public CacheItem(Key1 key, Value1 value)
        {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Key, Value> map = new HashMap<Key, Value>();
    private ArrayList<Key> keyList = new ArrayList<Key>();
    private ArrayList<Value> list = new ArrayList<Value>();
    private ArrayList<CacheItem<Key, Value>> itemList = new ArrayList<CacheItem<Key, Value>>();

    public void add(Key key, Value value)
    {
        remove(key);
        map.put(key, value);
        list.add(value);
        keyList.add(key);
        itemList.add(new CacheItem<Key, Value>(key, value));
    }

    public void remove(Key key)
    {
        if (map.containsKey(key))
        {
            Value val = map.get(key);
            list.remove(val);
            map.remove(key);
            keyList.remove(key);
        }
    }

    public Value get(Key key)
    {
        return map.get(key);
    }

    public List<Value> getList()
    {
        return list;
    }

    public List<Key> getKeyList()
    {
        return keyList;
    }

    public List<CacheItem<Key, Value>> getItemList()
    {
        return itemList;
    }

    public void clear()
    {
        keyList.clear();
        list.clear();
        map.clear();
        itemList.clear();
    }

    public int size()
    {
        return list.size();
    }

    public boolean contains(Key key)
    {
        return map.containsKey(key);
    }
}
