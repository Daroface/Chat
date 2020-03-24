package com.daroface.controller;

import com.daroface.model.ChatServer;

import java.net.SocketException;

public class ChatServerManager {
    private  static ChatServerManager chatServerManager;
    private static ChatServer chatServer;

    private void startServer() {
        try {
            chatServer = new ChatServer();
            chatServer.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        chatServerManager = new ChatServerManager();
        chatServerManager.startServer();
    }
}
