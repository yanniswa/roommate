package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

public class Arbeitsplatz {

    private int Id;
    private List<Buchung> buchungen;

    private List<String> ausstattung;

    public Arbeitsplatz(List<String> ausstattung,int Id) {
        this.Id =Id;
        this.ausstattung = ausstattung;
        buchungen = new ArrayList<>();
    }

    public int getId() {
        return Id;
    }

    public List<String> getAusstattung() {
        return new ArrayList<>(ausstattung);
    }

    public void addAusstattung(List<String> ausstattung) {
        this.ausstattung = ausstattung;
    }
}
