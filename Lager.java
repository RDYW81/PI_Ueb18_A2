import java.util.*;
import java.util.function.*;
/**
 * Lager-Klasse zur Verwaltung mehrerer Artikel, ihrer Anzahl und Preisgestaltung.
 * 
 * @author Roland Daidone, Michael Linn
 * @version 1.5
 */
public class Lager
{
    // Benennung des Lagers und Grundstein Array - Lager
    private String lager;
    private Artikel [] artikelDB;
    private int zahlArtikel;

    // Fehlermeldungen
    private static final String MSG_ARTIKEL_NICHT_VORHANDEN =
        "Artikel nicht vorhanden!";
    private static final String MSG_ARTIKEL_ANLEGEN =
        "Artikel nicht anlegbar!";
    private static final String MSG_ARTIKEL_VORHANDEN =
        "Artikel bereits vorhanden!";
    private static final String MSG_ARTIKEL_VERSCHIEDEN =
        "Die Artikel muessen verschieden sein!";
    private static final String MSG_MAX_ANZ_ARTIKEL =
        "Die Zahl der Artikel muss > 0 sein!";

    /**
     * Konstruktor für Objekte der Klasse Lager
     * 
     * @param lager Lager (Name des Lagers)
     * @param maxAnzArtikel maximale Anzahl festzulegender Artikel
     */
    public Lager(String lager, int maxZahlArtikel) {
        check(maxZahlArtikel > 0, MSG_MAX_ANZ_ARTIKEL);
        this.lager = lager;
        artikelDB = new Artikel[maxZahlArtikel];
        zahlArtikel = 0;
        artikelAnlegen(new Artikel(1000, "HalliGalli", 20, 20.0));
        artikelAnlegen(new Buch(1100, "IchbinEinBuch", 25, 60.0, "HalliGalliTitel", "Picard", "SinnlosenVerlag"));
        artikelAnlegen(new CD(1200, "IchBinEineCD", 200, 20.0, "Guns' Roses", "Jungle", 10));
        artikelAnlegen(new DVD(1300, "IchBinEineDVD", 1000, 70.0, "Terminator", 120, 1986));
    }

    /**
     * Anlegen eines Artikels. 
     * Prüfung, ob Artikelnummer bereits vergeben ist
     * Prüfung, ob Artikeltabelle bereits voll ist.
     * 
     * @param  artikelnummer -  Artikelnummer des zu erstellenden Artikels
     * @param  artikelbezeichnung -  Bezeichnung des Artikels
     */ 
    public void artikelAnlegen(Artikel artikel) {
        check(findeArtikel(artikel.getArtikelnummer()) < 0, MSG_ARTIKEL_VORHANDEN);
        check(zahlArtikel < artikelDB.length, MSG_ARTIKEL_ANLEGEN);

        artikelDB[zahlArtikel] = artikel;
        zahlArtikel++;
    }

    /** 
     * Artikel um Bestand X erhöhen  
     * 
     * @param  artikelnummer - zu übergebende Artikelnummer
     * @param  bestand - zuzubuchender Bestand (Addition)
     */
    public void zugangsBuchung(int artikelnummer, int bestand) {
        int i = findeArtikel(artikelnummer);
        check(i >= 0, MSG_ARTIKEL_NICHT_VORHANDEN);
        artikelDB[i].zugangsBuchung(bestand); 
    }

    /** 
     * Artikel um Bestand X abziehen
     * 
     * @param  artikelnummer - zu übergebende Artikelnummer
     * @param  bestand - abzubuchender Bestand (Subtraktion)
     */
    public void zugangsAbbuchung(int artikelnummer, int bestand) {
        int i = findeArtikel(artikelnummer);
        check(i >= 0, MSG_ARTIKEL_NICHT_VORHANDEN);
        artikelDB[i].zugangsAbbuchung(bestand); 
    }

    /**
     * Artikelpreis definieren
     * 
     * @param artikelnummer - zu übergebende Artikelnummer
     * @param preis - Artikelpreis wird definiert
     */
    public void artikelpreisDefinieren(int artikelnummer, double artikelpreis) {
        int i = findeArtikel(artikelnummer);
        check (i >= 0, MSG_ARTIKEL_NICHT_VORHANDEN);
        artikelDB[i].setartikelpreis(artikelpreis);
    }

    /**
     * Artikelpreis um X-Prozent erhöhen
     * 
     * @param artikelnummer - zu übergebende Artikelnummer
     * @param artikelpreis - Artikelpreis wird um X-Prozent erhöht
     */

    public void artikelpreisErhoehung (int artikelnummer, double artikelpreis) {
        int i = findeArtikel(artikelnummer);
        check (i >= 0, MSG_ARTIKEL_NICHT_VORHANDEN);
        artikelDB[i].preisErhoehung(artikelpreis);
    }

    /**
     * Artikelpreis um X-Prozent senken
     * 
     * @param artikelnummer - zu übergebende Artikelnummer
     * @param artikelpreis - Artikelpreis wird um X-Prozent gesenkt
     */

    public void artikelpreisSenkung (int artikelnummer, double artikelpreis) {
        int i = findeArtikel(artikelnummer);
        check (i >= 0, MSG_ARTIKEL_NICHT_VORHANDEN);
        artikelDB[i].preisSenkung(artikelpreis);
    }

    /** 
     * Artikel aus der Datenbank löschen
     * 
     * @param  artikelnummer - zu uebergebende Artikelnummer
     */
    public void delArtikel(int artikelnummer) {
        int i = findeArtikel(artikelnummer);

        if (i >= 0) {
            for (int j = i; j < zahlArtikel-1; j++)
                artikelDB[j] = artikelDB [j+1];
            artikelDB[zahlArtikel-1] = null;
            zahlArtikel--;
        }
    }

    /** 
     * Interne Methode findeArtikel zur Vereinfachung anderer Methoden
     * 
     * @param  artikelnummer - zu übergebende Artikelnummer
     * @return Index des gesuchten Artikels oder -1
     * 
     */
    private int findeArtikel(int artikelnummer) {
        for (int i = 0; i < zahlArtikel; i++) {
            if (artikelDB[i].getArtikelnummer() == artikelnummer)
                return i;
        }
        return -1; 
    }

    /** 
     * Lager-Objekt als Zeichenkette aufbereiten;
     * verwendet implizit die toString-Methode von Artikel
     * 
     * @return  Zeichenkette
     * 
     */
    public String toString() {
        String ausgabe = lager + '\n';
        for (int i = 0; i < zahlArtikel; i++) {
            ausgabe = ausgabe + i + ": " + artikelDB[i] + '\n';
        }
        return ausgabe;
    }

    /**
     * Insertion-Sort-Verfahren zur Sortierung der Artikel, welches von nachfolgenden Lambda-Ausdrücken
     * bedient wird.
     */
    public void getSorted(BiPredicate<Artikel,Artikel> a) {
        for (int i = 0; i < zahlArtikel; i++){
            for (int j = i - 1; j >= 0; j--){
                if (a.test(artikelDB[j] , artikelDB[j + 1])){
                    Artikel sortiereMich = artikelDB[j];
                    artikelDB[j] = artikelDB[j + 1];
                    artikelDB[j + 1] = sortiereMich;
                }
            }
        }
    }

    /**
     * Methode, welche das Lager nach bestimmten Filterkriterien filtert
     * 
     * @param filterkriterium gibt Filterkriterium wieder
     */
    public ArrayList<Artikel> filter(Predicate<Artikel> filterkriterium) {

        ArrayList<Artikel> angabe = new ArrayList<>();

        for (Artikel artikel : artikelDB) {
            if (filterkriterium.test(artikel)) {
                angabe.add(artikel);
            }
        }
        return angabe;
    } 

    /**
     * Methode, welche eine bestimmte Anweisung auf die Artikel im Lager anwendet
     * 
     * @param anweisung gibt Anweisung wieder
     */
    public void applyToArticles(Consumer<Artikel> anweisung) {
        for (Artikel artikel : artikelDB) {
            anweisung.accept(artikel);
        }
    } 

    /**
     * Methode, welche das Lager nach bestimmten Filterkriterien mit einer zusätzlichen
     * Anweisung filtert
     * 
     * @param filterkriterium gibt Filterkriterium wieder
     * @param anweisung gibt Anweisung wieder
     */
    public ArrayList<Artikel> applytoSomeArticles(Consumer<Artikel> anweisung, Predicate<Artikel> filterkriterium) {

        ArrayList<Artikel> angabe = new ArrayList<>();

        for (Artikel artikel : artikelDB) {
            if (filterkriterium.test(artikel)) {
                angabe.add(artikel);
            }
            anweisung.accept(artikel);
        }
        return angabe;
    }

    public void getArticles(BiPredicate<Artikel,Artikel> a, Predicate<Artikel> filterkriterium) {
    
    }

    // Preissortierung
    BiPredicate <Artikel,Artikel> sortiereNachPreis   = (artikel1 ,artikel2)  -> 
            (artikel1.getArtikelpreis() > artikel2.getArtikelpreis());

    // Bestandssortierung
    BiPredicate <Artikel,Artikel> sortiereNachBestand = (artikel1 ,artikel2)  -> 
            (artikel1.getArtikelbestand() > artikel2.getArtikelbestand());

    // Alphabetische Sortierung
    BiPredicate <Artikel,Artikel> sortiereNachAlphabet = (artikel1 ,artikel2) -> 
            (artikel1.getArtikelbezeichnung().toLowerCase().compareTo(artikel2.getArtikelbezeichnung().toLowerCase()) > 0);

    /**
     * Lager-Objekt als Zeichenkette aufbereiten
     * gemäß Vorgabe Kundenspezifikation aus Übung 9;
     * 
     * @return Zeichenkette nach Vorgabe Kundenspezifikation
     */
    public void ausgebenBestandsliste() {
        double gesamtwert = 0;
        System.out.println("\nLagerort: Alt-Saarbrücken\n\n"
            + "ArtNr    Beschreibung                                       Preis         Bestand          Gesamt  \n"
            + "--------------------------------------------------------------------------------------------------");
        for (int i = 0; i < zahlArtikel; i++) {
            Artikel aktuellerArtikel = artikelDB[i];
            double gesamt = aktuellerArtikel.getArtikelpreis()*aktuellerArtikel.getArtikelbestand();
            gesamtwert = gesamt + gesamtwert;
            System.out.format ("%-7d%-53.53s%-14.2f%-10d%13.2f\n",aktuellerArtikel.getArtikelnummer(), 
                aktuellerArtikel.getBeschreibung(),aktuellerArtikel.getArtikelpreis(),
                aktuellerArtikel.getArtikelbestand(),gesamt);
        }
        System.out.print ("--------------------------------------------------------------------------------------------------\n"
            + "Gesamtwert:                                                                               ");
        System.out.format("%7.2f\n\n",gesamtwert);  

    }

    private static void check(boolean bedingung, String msg) {
        if (!bedingung)
            throw new IllegalArgumentException(msg);
    }
}
