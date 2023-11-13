package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomnumber;
    private List<Arbeitsplatz> arbeitsplaetze;

    public Room(int roomnumber,List<Arbeitsplatz> arbeitsplaetze) {
        this.roomnumber = roomnumber;
        this.arbeitsplaetze = arbeitsplaetze;
    }

    public Room(int roomnumber) {
        this.arbeitsplaetze = new ArrayList<>();
        this.roomnumber = roomnumber;
    }

    public List<Arbeitsplatz> getArbeitsplaetze() {
        return new ArrayList<>(arbeitsplaetze);
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
