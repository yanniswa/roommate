package com.example.Controller;

import java.util.List;

public class Arbeitsplatz {

    private boolean verfuegbar;

    private List<String> ausstattung;

    public boolean isVerfuegbar() {
        return verfuegbar;
    }

    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }

    public List<String> getAusstattung() {
        return ausstattung;
    }

    public void setAusstattung(List<String> ausstattung) {
        this.ausstattung = ausstattung;
    }
}
