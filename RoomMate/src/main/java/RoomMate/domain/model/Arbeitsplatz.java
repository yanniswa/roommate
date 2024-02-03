package RoomMate.domain.model;

import RoomMate.annotations.AggregateRoot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@AggregateRoot
 public class Arbeitsplatz {

    private int Id;
    private List<Buchung> buchungen;
    Room room;
    private List<String> ausstattung;


    public Arbeitsplatz(List<String> ausstattung,int Id, int  roomNumber) {
        this.Id =Id;
        this.ausstattung = ausstattung;
        buchungen = new ArrayList<>();
        this.room= new Room(roomNumber);
    }

    public List<Buchung> getBuchungen() {
        return buchungen;
    }

    public int getId() {
        return Id;
    }


    public int getroomnumber() {
        return room.getRoomnumber();
    }

    public boolean addBuchung(LocalTime anfang, LocalTime ende,
                              LocalDate datum, String benutzer){
        boolean keineBuchungenImZeitraum = buchungen.stream().filter(e -> e.getLocalDate().isEqual(datum))
                .filter(e -> e.getAnfang().isBefore(anfang) && e.getEnde().isAfter(anfang) ||
                        e.getAnfang().isAfter(anfang)&& e.getEnde().isAfter(anfang) ||
                        e.getAnfang().equals(anfang) || e.getEnde().equals(anfang)||
                        e.getAnfang().equals(ende) || e.getEnde().equals(ende))
                .filter(e -> e.getAnfang().isBefore(ende) && e.getEnde().isAfter(ende)||
                        e.getAnfang().isBefore(ende) && e.getEnde().isBefore(ende)||
                        e.getAnfang().equals(ende) || e.getEnde().equals(ende)||
                        e.getAnfang().equals(anfang) || e.getEnde().equals(anfang))
                .toList().isEmpty();

        if(datum.isBefore(LocalDate.now())){
            return false;
        }
        else if(!keineBuchungenImZeitraum){
            return false;
        } else if (LocalTime.now().isAfter(anfang)&& datum.isEqual(LocalDate.now())) {
            return false;
        }
        buchungen.add(new Buchung(datum,anfang,ende,benutzer));
        return true;
    }
    //public void addBuchung(List<Buchung> buchung){buchungen.addAll(buchung);}

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

    public List<Zeitslot> freieZeitslots(LocalDate datum){
        List<Buchung> buchungAnDemTag = new ArrayList<>(buchungen.stream().filter(e->e.getLocalDate().isEqual(datum)).toList());
        List<Zeitslot> freieSlot = new ArrayList<>();
        LocalTime anfang = LocalTime.of(0,0);
        LocalTime previous= null;
        for(int i=0; i<24;i++){
            for (int j=0;j<60;j+=10){
                LocalTime current = LocalTime.of(i,j);
                List<Buchung> buchungenDavor = isFree(buchungAnDemTag, current);
                if(buchungenDavor.isEmpty()){
                    previous = current;
                }else{
                    freieSlot.add(new Zeitslot(anfang,previous));
                    Buchung buchungVorFreienSlot = getMin(buchungenDavor);
                    anfang = buchungVorFreienSlot.getEnde();
                    previous = buchungVorFreienSlot.getEnde().plusMinutes(10);
                    i= buchungVorFreienSlot.getEnde().getHour();
                    j= buchungVorFreienSlot.getEnde().getMinute();
                    buchungAnDemTag.remove(buchungVorFreienSlot);
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
