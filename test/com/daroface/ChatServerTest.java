package com.daroface;

import com.daroface.model.ChatClient;
import com.daroface.model.ChatServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ChatServerTest {
    ChatClient chatClient;

    @Before
    public void setup() {
        try {
            new ChatServer().start();
            chatClient = new ChatClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldSendAndReceiveMessageToFromChatServer() {
        try {
            String echo = chatClient.sendMessage("hello server");
            assertEquals("hello server", echo);
            echo = chatClient.sendMessage("server is working");
            assertFalse(echo.equals("hello server"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            chatClient.sendMessage("end");
            chatClient.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
