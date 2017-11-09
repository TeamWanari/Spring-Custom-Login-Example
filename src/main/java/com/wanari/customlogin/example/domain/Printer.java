package com.wanari.customlogin.example.domain;

import java.util.UUID;

//TODO printer entity, you probably want to store data in a DB, but this is just an example app so I used SimpleInMemoryRepository
public class Printer {
    public String id;
    public String name;
    public Boolean isDetonated;

    public Printer() {
        this.id = UUID.randomUUID().toString();
        this.isDetonated = false;
    }
}
