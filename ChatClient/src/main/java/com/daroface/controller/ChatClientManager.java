package com.daroface.controller;

import com.daroface.model.ChatClient;

import java.io.IOException;

public class ChatClientManager {
    private static ChatClientManager chatClientManager;
    private ChatClient chatClient;

    private void startClient() {
        try {
            chatClient = new ChatClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        chatClientManager = new ChatClientManager();
        chatClientManager.startClient();
    }
}
