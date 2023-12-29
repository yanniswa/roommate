package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

public class Arbeitsplatz {

    private int Id;
    private boolean verfuegbar;

    private List<String> ausstattung;

    public Arbeitsplatz(List<String> ausstattung,int Id) {
        this.Id =Id;
        this.ausstattung = ausstattung;
        verfuegbar = true;
    }

    public int getId() {
        return Id;
    }

    public boolean isVerfuegbar() {
        return verfuegbar;
    }

    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }

    public List<String> getAusstattung() {
        return new ArrayList<>(ausstattung);
    }

    public void addAusstattung(List<String> ausstattung) {
        this.ausstattung = ausstattung;
    }
}
