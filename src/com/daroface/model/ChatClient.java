package com.daroface.model;

import java.io.IOException;
import java.net.*;

public class ChatClient {
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buffer;
    private static final String serverName = "localhost";
    private static final int serverPort = 6870;

    public ChatClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName(serverName);
    }

    public String sendMessage(String message) throws IOException {
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, serverPort);
        socket.send(packet);
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }
}

