package com.skyworth.framework.data;

import java.io.Serializable;

/**
 * 模式数据结构
 * 可以用于图像模式，声音模式等
 */
public class Mode implements Serializable {
    /**
     * 模式id
     */
    public int id;

    /**
     * 模式显示名称
     */
    public String label;

    public Mode() {
    }

    public Mode(int id) {
        this.id = id;
    }

    public Mode(int id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return id == ((Mode) obj).id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
