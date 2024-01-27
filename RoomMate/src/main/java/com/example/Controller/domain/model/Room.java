package com.example.Controller.domain.model;

import com.example.Controller.domain.model.Arbeitsplatz;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public int anzahlArbeitsplaetze(){
        return arbeitsplaetze.size();
    }

    public List<Zeitslot> freieSlots(LocalDate datum, int platzID){
        Arbeitsplatz arbeitsplatz = arbeitsplaetze.stream().filter(e->e.getId()==platzID).findFirst().get();
        return arbeitsplatz.freieZeitslots();

    }


}
