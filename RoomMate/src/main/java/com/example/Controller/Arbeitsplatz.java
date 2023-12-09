package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

public class Arbeitsplatz {

    private boolean verfuegbar;

    private List<String> ausstattung;

    public Arbeitsplatz(List<String> ausstattung) {
        this.ausstattung = ausstattung;
        verfuegbar = true;
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
