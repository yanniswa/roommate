package com.example.Controller.domain.model;

import com.example.Controller.domain.model.Buchung;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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

     private List<Buchung> isFree(List<Buchung> buchungen, LocalTime zeit){
         return buchungen.stream().filter(e -> e.getAnfang().isBefore(zeit)||e.getAnfang().equals(zeit)).toList();
     }
     private Buchung getMin(List<Buchung> liste){
         liste.stream()
                 .min(Comparator.comparing(Buchung::getAnfang));
         return liste.getFirst();
     }

    public List<Zeitslot> freieZeitslots(){
        List<Zeitslot> freieSlot = new ArrayList<>();
        LocalTime anfang = LocalTime.of(0,0);
        LocalTime previous= null;
        for(int i=0; i<24;i++){
            for (int j=0;j<60;j+=10){
                LocalTime current = LocalTime.of(i,j);
                List<Buchung> buchungenDavor = isFree(buchungen, current);
                if(buchungenDavor.isEmpty()){
                    previous = current;
                }else{
                    freieSlot.add(new Zeitslot(anfang,previous));
                    Buchung buchungVorFreienSlot = getMin(buchungenDavor);
                    anfang = buchungVorFreienSlot.getEnde();
                    previous = buchungVorFreienSlot.getEnde().plusMinutes(10);
                    i= buchungVorFreienSlot.getEnde().getHour();
                    j= buchungVorFreienSlot.getEnde().getMinute();
                    buchungen.remove(buchungVorFreienSlot);
                }
            }
        }
        if(anfang.isAfter(previous)){
            return freieSlot;
        }
        freieSlot.add(new Zeitslot(anfang,previous));
        return freieSlot;
    }
}
