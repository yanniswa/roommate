package com.example.Controller;

import java.util.List;

public class Room {
    private int roomnumber;
    private int anzahlArbeitsplaetze;
    private List<Arbeitsplatz> arbeitsplaetze;

    public int getAnzahlArbeitsplaetze() {
        return anzahlArbeitsplaetze;
    }

    public void setAnzahlArbeitsplaetze(int anzahlArbeitsplaetze) {
        this.anzahlArbeitsplaetze = anzahlArbeitsplaetze;
    }

    public List<Arbeitsplatz> getArbeitsplaetze() {
        return arbeitsplaetze;
    }

    public void setArbeitsplaetze(List<Arbeitsplatz> arbeitsplaetze) {
        this.arbeitsplaetze = arbeitsplaetze;
    }

    public int getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(int roomnumber) {
        this.roomnumber = roomnumber;
    }
}
