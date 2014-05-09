package com.paypal.tcp;

import com.google.gson.Gson;
import com.paypal.model.Device;
import org.apache.log4j.Logger;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Paolo
 * Created on 5/8/14 11:17 PM
 */
public class Task extends TimerTask {
    private static Logger logger = Logger.getLogger(Task.class);
    private PrintWriter toClient;
    private static UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    private static Random random = new Random();
    private static final int MAX = 20;
    private static final int MIN = 0;

    private static final int MAX_MAC = 1000000000;
    private static final int MIN_MAC = 0;
    private static final Gson gson = new Gson();

    public Task(PrintWriter toClient) {
        this.toClient = toClient;
    }

    /**
     * Runnable task if we want to implement a "stay-alive" approach
     */
    @Override
    public void run() {
        toClient.println(getDeviceList());
    }

    public static String getDeviceList() {
        int numberOfDevices = random.nextInt(MAX - MIN + 1) + MIN;
        List<Device> deviceList = new ArrayList<Device>(numberOfDevices);
        logger.debug("NUMBER OF CONNECTED DEVICES: " + numberOfDevices);
        for(int i=0; i<numberOfDevices; i++){
            String uuid = uid.randomUUID().toString();
            String mac = randomMac();
            logger.debug("UUID: " + uuid + ", MAC: " + mac);
            deviceList.add(new Device(uuid, mac));
        }
        String deviceListString = gson.toJson(deviceList);
        logger.debug("deviceListString: " + deviceListString);
        return deviceListString;
    }

    private static String randomMac() {
        int randomMac = random.nextInt(MAX_MAC - MIN_MAC + 1) + MIN_MAC;

        if (randomMac > 0xFFFFFFFFFFFFL || randomMac < 0)
            throw new IllegalArgumentException("mac out of range");

        StringBuffer m = new StringBuffer(Long.toString(randomMac, 16));
        while (m.length() < 12) m.insert(0, "0");

        for (int j = m.length() - 2; j >= 2; j-=2)
            m.insert(j, ":");
        return m.toString().toUpperCase();
    }
}