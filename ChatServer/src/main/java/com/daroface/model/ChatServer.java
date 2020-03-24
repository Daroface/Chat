package com.daroface.model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class ChatServer extends Thread {
    private static final int port = 6870;
    private DatagramSocket socket;
    private boolean isRunning;
    private byte[] buffer = new byte[256];
    ArrayList<Client> clients;

    public ChatServer() throws SocketException {
        clients = new ArrayList<Client>();
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
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                if(execute(receivedMessage, packet)) {
                    clearBuffer();
                    buffer = "Ok".getBytes();
                } else {
                    packet = new DatagramPacket(buffer, buffer.length, address, clientPort);
                    socket.send(packet);
                }
                clearBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    private boolean execute(String command, DatagramPacket packet) {
        switch(command) {
            case "end":
                isRunning = false;
                return true;
            case "addMe":
                if(!isClientExist(packet.getAddress())) {
                    clients.add(new Client(packet.getAddress(), packet.getPort(), clients.size()));
                    clients.get(clients.size()-1);
                }
                return true;
        }
        return false;
    }

    private boolean isClientExist(InetAddress address) {
        for(Client client : clients) {
            if(client.getAddress().equals(address)) {
                return true;
            }
        }
        return false;
    }

    private void clearBuffer() {
        buffer = new byte[256];
    }
}