package com.example.Controller;

import java.time.LocalDateTime;

public class Buchung {

    int id;
    LocalDateTime localDateTime;
    String benutzer;

    public Buchung(int id, LocalDateTime localDateTime, String benutzer) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.benutzer = benutzer;
    }
}
