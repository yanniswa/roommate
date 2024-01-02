package com.example.Controller.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Buchung {

    int id;
    private LocalDate localDate;
    private LocalTime anfang;
    private LocalTime ende;
    private String benutzer;

    public Buchung(int id, LocalDate localDateTime, LocalTime anfang, LocalTime ende, String benutzer) {
        this.id = id;
        this.localDate = localDateTime;
        this.anfang = anfang;
        this.ende = ende;
        this.benutzer = benutzer;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getAnfang() {
        return anfang;
    }

    public LocalTime getEnde() {
        return ende;
    }
}
