package com.paypal.tcp;

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
class Server
{

    public void run() {
        try {
            int serverPort = 4020;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            serverSocket.setSoTimeout(0);   //infinite
            while(true) {
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");

                Socket server = serverSocket.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());

                PrintWriter toClient = new PrintWriter(server.getOutputStream(),true);
                BufferedReader fromClient = new BufferedReader( new InputStreamReader(server.getInputStream()));
                String line = fromClient.readLine();
                System.out.println("Server received: " + line);



                toClient.println("Thank you for connecting to " + server.getLocalSocketAddress());
                Timer timer = new Timer();
                timer.schedule(new Task(toClient), 0, 5000);
            }
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Server srv = new Server();
        srv.run();
    }
}
