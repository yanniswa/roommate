package com.example.Controller.domain.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BuchungsForm {
    @NotNull
    @FutureOrPresent(message = "Bitte wähle ein Datum in der Gegenwart")
    private LocalDate datum;

    @NotNull
    private LocalTime anfang;
    @NotNull
    private LocalTime ende;

    public LocalTime getEnde() {
        return ende;
    }

    public void setEnde(LocalTime ende) {
        this.ende = ende;
    }

    private int id;

    public LocalTime getAnfang() {
        return anfang;
    }

    public void setAnfang(LocalTime dauer) {
        this.anfang = dauer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

}
