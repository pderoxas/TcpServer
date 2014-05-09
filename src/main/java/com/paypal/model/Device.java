package com.paypal.model;

/**
 * Created by pderoxas on 5/9/14.
 */
public class Device {
    private String uuid;
    private String macAddress;

    public Device(String uuid, String macAddress) {
        this.uuid = uuid;
        this.macAddress = macAddress;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
