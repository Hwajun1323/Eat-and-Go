package com.example.eatandgo.domain;

public class Restaurant {
    private final String name;
    private final String address;
    private final Long id;

    public Restaurant(Long id, String name, String address) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return "McDonald";
    }

    public String getInformation() {
        return name + " in " +  address;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }
}
