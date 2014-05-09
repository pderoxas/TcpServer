package com.paypal.tcp;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;

/**
 * Created by Paolo
 * Created on 5/8/14 8:58 PM
 */
public class Server
{
    Logger logger = Logger.getLogger(this.getClass());
    public void run() {
        try {
            int serverPort = 4020;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            serverSocket.setSoTimeout(0);   //infinite
            while(true) {
                logger.debug("Waiting for client on port " + serverSocket.getLocalPort() + "...");

                Socket server = serverSocket.accept();
                logger.debug("Connected to client: " + server.getRemoteSocketAddress());

                PrintWriter toClient = new PrintWriter(server.getOutputStream(),true);
                BufferedReader fromClient = new BufferedReader( new InputStreamReader(server.getInputStream()));
                String line = fromClient.readLine();
                logger.debug("Server received: " + line);

                //If we implement stay-alive approach
                //Run every 5 seconds
                //Timer timer = new Timer();
                //timer.schedule(new Task(toClient), 0, 5000);

                //Response to single client connection
                String deviceList = Task.getDeviceList();
                toClient.println(deviceList);
            }
        }
        catch(UnknownHostException ex) {
            logger.error(ex.getMessage(), ex);
        }
        catch(IOException ex){
            logger.error(ex.getMessage(), ex);
        }
    }

    public static void main(String[] args) {
        Server srv = new Server();
        srv.run();
    }
}
