package com.tianci.system.data;

import java.io.Serializable;

public class OneKeyData implements Serializable {

    private static final long serialVersionUID = -6761051988232075571L;
    /**
     * color key
     */
    public String color = "";
    /**
     * name
     */
    public String actionName = "";

    public OneKeyData() {
    }

    public OneKeyData(String color, String actionName) {
        this.color = color;
        this.actionName = actionName;
    }

    @Override
    public String toString() {
        return "color=" + color + ",actionName=" + actionName;
    }
}
