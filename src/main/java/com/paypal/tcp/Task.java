package com.paypal.tcp;

import java.io.PrintWriter;
import java.util.Random;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Created by Paolo
 * Created on 5/8/14 11:17 PM
 */
public class Task extends TimerTask {
    private PrintWriter toClient;
    private UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    private Random random = new Random();
    private static final int MAX = 20;
    private static final int MIN = 0;

    public Task(PrintWriter toClient) {
        this.toClient = toClient;
    }

    @Override
    public void run() {
        int numberOfDevices = random.nextInt(MAX - MIN + 1) + MIN;
        toClient.println("NUMBER OF CONNECTED DEVICES: " + numberOfDevices);
        for(int i=0; i<numberOfDevices; i++){
            toClient.println("Device: " + uid.randomUUID());
        }
    }
}