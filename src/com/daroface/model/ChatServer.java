package com.daroface.model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ChatServer extends Thread {
    private static final int port = 6870;
    private DatagramSocket socket;
    private boolean isRunning;
    private byte[] buffer = new byte[256];

    public ChatServer() throws SocketException {
        socket = new DatagramSocket(port);
        isRunning = false;
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int clientPort = packet.getPort();
                String received = new String(packet.getData(), 0, packet.getLength());
                if (received.equals("end")) {
                    isRunning = false;
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}