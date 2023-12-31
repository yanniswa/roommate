package com.example.Controller;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BuchungsForm {
    @NotNull
    @FutureOrPresent(message = "Bitte wähle ein Datum in der Gegenwart oder Zukunft")
    private LocalDateTime datum;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

}
