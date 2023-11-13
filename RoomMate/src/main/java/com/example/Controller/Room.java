package com.example.Controller;

import java.util.List;

public class Room {
    private int roomnumber;
    private List<Arbeitsplatz> arbeitsplaetze;

    public Room(int roomnumber, int anzahlArbeitsplaetze, List<Arbeitsplatz> arbeitsplaetze) {
        this.roomnumber = roomnumber;
        this.arbeitsplaetze = arbeitsplaetze;
    }


    public List<Arbeitsplatz> getArbeitsplaetze() {
        return arbeitsplaetze;
    }

    public void addArbeitsplaetze(List<Arbeitsplatz> arbeitsplaetze) {
        this.arbeitsplaetze.addAll(arbeitsplaetze);
    }
    public void addArbeitsplatz(Arbeitsplatz arbeitsplatz){
        arbeitsplaetze.add(arbeitsplatz);
    }

    public int getRoomnumber() {
        return roomnumber;
    }

}
