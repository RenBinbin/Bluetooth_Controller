package com.canny.renbinbin1.bluetooth_controller.module.parameters.debug.bean;

/**
 * Created by renbinbin1 on 2017/8/21.
 */

public class DebugBean {
    private String title;
    private String name;
    private int num;

    public DebugBean(String title, String name, int num) {
        this.title = title;
        this.name = name;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
