package com.canny.renbinbin1.bluetooth_controller.bluetooth;

/**
 * Created by renbinbin1 on 2017/8/25.
 */

public class BlueToothBean {
    private int id;
    private String message;
    private String name;

    public BlueToothBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
