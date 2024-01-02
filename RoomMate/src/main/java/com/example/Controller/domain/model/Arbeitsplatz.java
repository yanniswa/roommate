package com.example.Controller.domain.model;

import com.example.Controller.domain.model.Buchung;
import org.springframework.stereotype.Component;

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

    public List<Buchung> getBuchungen() {
        return buchungen;
    }

    public void addBuchungen(Buchung buchung){
        buchungen.add(buchung);
    }

    public List<String> getAusstattung() {
        return new ArrayList<>(ausstattung);
    }

    public void addAusstattung(List<String> ausstattung) {
        this.ausstattung = ausstattung;
    }
}
