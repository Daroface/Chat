package com.daroface.model;

import java.net.InetAddress;

public class Client {
    private String user;
    private InetAddress address;
    private Integer port;

    public Client(InetAddress address, Integer port, Integer id) {
        this.user = "User" + id.toString();
        this.address = address;
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getUser() {
        return user;
    }
}
