import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.function.*;    
/**
 * Lager-Klasse zur Verwaltung mehrerer Artikel, ihrer Anzahl und Preisgestaltung.
 * 
 * @author Roland Daidone, Michael Linn
 * @version 1.7.1
 */
public class Lager implements Iterable <Artikel>
{
    // Benennung des Lagers und Grundstein Array - Lager
    private static final int ZWEI           =  2;
    private static final int ZWANZIG        = 20;
    private static final double ZEHNPROZENT =  1.1;
    private String lager;
    private Artikel [] artikelDB;
    private int zahlArtikel;
    
    
    //Map
    TreeMap<Integer,Artikel> artikelMap;    
    
    // Iterator
    Iterator iterator;
    
    // Fehlermeldungen
    private static final String MSG_ARTIKEL_NICHT_VORHANDEN =
        "Artikel nicht vorhanden!";
    private static  final String MSG_ARTIKEL_ANLEGEN =
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
    public Lager() {
        artikelMap = new TreeMap<Integer,Artikel>();
        lager = "Artikellager-treemaps-SaarbrueckenSE";
        
        zahlArtikel = 0;
        artikelAnlegen(new Artikel(1000, "HalliGalli", 20, 20.0));
        artikelAnlegen(new Buch(1100, "Roman", 25, 14.99, "Die drei Sonnen", "Cixin, L.", "Heyne"));
        artikelAnlegen(new Buch(1101, "Novelle", 25, 9.99, "Spiegel", "Cixin, L.", "Heyne"));
        artikelAnlegen(new Buch(1102, "Fachbuch", 2, 50.0, "Java ist auch eine Insel", "Ullenboom, C.", "Rheinwerk"));
        artikelAnlegen(new CD(1200, "IchBinEineCD", 200, 20.0, "Guns' Roses", "Jungle", 10));
        artikelAnlegen(new DVD(1300, "IchBinEineDVD", 1000, 70.0, "Terminator", 120, 1986));
         
        
        
    }
    
/**    public void fehlerErzeugen(){
    *    artikelAnlegen(new Artikel(1000, "HalliGalli", 20, 20.0));
    *    artikelAnlegen(new Buch(1100, "Roman", 25, 14.99, "Die drei Sonnen", "Cixin, L.", "Heyne")); 
    }*/
    
    /**
     * Anlegen eines Artikels. 
     * Prüfung, ob Artikelnummer bereits vergeben ist
     * Prüfung, ob Artikeltabelle bereits voll ist.
     * 
     * @param  artikelnummer -  Artikelnummer des zu erstellenden Artikels
     * @param  artikelbezeichnung -  Bezeichnung des Artikels
     */ 
    public void artikelAnlegen(Artikel artikel) {
       
        check  ( findeArtikelMap(artikel.getArtikelnummer()) == true , MSG_ARTIKEL_VORHANDEN); 
        artikelMap.put(artikel.getArtikelnummer() ,artikel);
        zahlArtikel++;
    }
    
    /** 
     * Artikel um Bestand X erhöhen  
     * 
     * @param  artikelnummer - zu übergebende Artikelnummer
     * @param  bestand - zuzubuchender Bestand (Addition)
     */
    public void zugangsBuchung(int artikelnummer, int bestand) {
        
        //map versuch
        Artikel a;
        if ( findeArtikelMap(artikelnummer) == false){
        a = artikelMap.get(artikelnummer);
        a.zugangsBuchung(bestand);
        artikelMap.replace(artikelnummer , a);
       }
    }

    /** 
     * Artikel um Bestand X abziehen
     * 
     * @param  artikelnummer - zu übergebende Artikelnummer
     * @param  bestand - abzubuchender Bestand (Subtraktion)
     */
    public void zugangsAbbuchung(int artikelnummer, int bestand) {
        
        //Map versuch
        Artikel a;
        check ( findeArtikelMap(artikelnummer) == false,MSG_ARTIKEL_NICHT_VORHANDEN);
        a = artikelMap.get(artikelnummer);
        a.zugangsAbbuchung(bestand);
        artikelMap.replace(artikelnummer , a);
    }

    /**
     * Artikelpreis definieren
     * 
     * @param artikelnummer - zu übergebende Artikelnummer
     * @param preis - Artikelpreis wird definiert
     */
    public void artikelpreisDefinieren(int artikelnummer, double artikelpreis) {
        
      //Map
        Artikel a;
        check ( findeArtikelMap(artikelnummer) == false,MSG_ARTIKEL_NICHT_VORHANDEN);
        a = artikelMap.get(artikelnummer);
        a.setartikelpreis(artikelpreis);
        artikelMap.replace(artikelnummer, a);
    }

    /**
     * Artikelpreis um X-Prozent erhöhen
     * 
     * @param artikelnummer - zu übergebende Artikelnummer
     * @param artikelpreis - Artikelpreis wird um X-Prozent erhöht
     */

    public void artikelpreisErhoehung (int artikelnummer, double artikelpreis) { 
        //map 
        // Artikel a;
        // check ( findeArtikelMap(artikelnummer) == false,MSG_ARTIKEL_NICHT_VORHANDEN);
        // a = artikelMap.get(artikelnummer);
        // a.setartikelpreis(a.getArtikelpreis()*artikelpreis);
        // artikelMap.replace(artikelnummer, a);
        
        for (Artikel artikel :this){
            artikel.setartikelpreis(artikel.getArtikelpreis()*artikelpreis);
        }
    }

    /**
     * Artikelpreis um X-Prozent senken
     * 
     * @param artikelnummer - zu übergebende Artikelnummer
     * @param artikelpreis - Artikelpreis wird um X-Prozent gesenkt
     */

    public void artikelpreisSenkung (int artikelnummer, double artikelpreis) {
        //map manuel viel zu viel, aufwand :x
        // Artikel a;
        // check ( findeArtikelMap(artikelnummer) == false,MSG_ARTIKEL_NICHT_VORHANDEN);
        // a = artikelMap.get(artikelnummer);
        // a.setartikelpreis(a.getArtikelpreis()-artikelpreis);
        // artikelMap.replace(artikelnummer, a);
        
        for (Artikel artikel : this){
            artikel.setartikelpreis(artikel.getArtikelpreis()+artikel.getArtikelpreis()/100*artikelpreis);      
        }
    }

    /** 
     * Artikel aus der Datenbank löschen
     * 
     * @param  artikelnummer - zu uebergebende Artikelnummer
     */
   
    
    public void delItem(int aNummer){
        if ( findeArtikelMap(aNummer) == false){
         artikelMap.values().remove(aNummer);
         zahlArtikel--;
        }
    }

    public void delAll(){
        artikelMap.clear();
        zahlArtikel = 0;
    }
    /** 
     * Interne Methode findeArtikel zur Vereinfachung anderer Methoden
     * 
     * @param  artikelnummer - zu übergebende Artikelnummer
     * @return Index des gesuchten Artikels oder -1
     * 
     */
    
    //Map
    private boolean findeArtikelMap(int artikelnummer){
      if( artikelMap.containsKey(artikelnummer ))
         return false; //Artikelnummer existiert bereits
      return true; //Artikelnummer existiert nicht
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
        int i=0;
        for (Artikel art : this) {
           // ausgabe = ausgabe + i + ": " + artikelDB[i] + '\n';
            ausgabe += i+": "+art.toString()+'\n';
            i++;
        }
        return ausgabe;
    }
   
    //Map
    public int getSize(){
        return artikelMap.size();
    }
    /**
     * Insertion-Sort-Verfahren zur Sortierung der Artikel, welches von nachfolgenden Lambda-Ausdrücken
     * bedient wird.
     */
    public List<Artikel> getSorted(BiPredicate<Artikel,Artikel> a) {
        return stream().sorted((k,v) -> a.test(k,v) ? 1: -1).collect(Collectors.toList());
    }

    /**
     * Methode, welche das Lager nach bestimmten Filterkriterien filtert
     * 
     * @param filterkriterium gibt Filterkriterium wieder
     */
    public List<Artikel> filter(Predicate<Artikel> filterkriterium) {
        return stream().filter(filterkriterium).collect(Collectors.toList());
        // ArrayList<Artikel> angabe = new ArrayList<>();
        // Set set = artikelMap.entrySet();
        // iterator =  set.iterator();
        // while(iterator.hasNext()){        
        // for (Artikel artikel : artikelMap.values()) {
            // if (filterkriterium.test(artikel)) {
                // angabe.add(artikel);
            // }
        // }
    // } 
    // return angabe;
       
    }
    /**
     * Methode, welche eine bestimmte Anweisung auf die Artikel im Lager anwendet
     * 
     * @param anweisung gibt Anweisung wieder
     */
    public void applyToArticles(Consumer<Artikel> anweisung) {
       // for (Artikel artikel : artikelMap.values()) {
        //    anweisung.accept(artikel);
       // }
       // artikelMap.forEach((k,v) -> anweisung.accept(v));
       stream().forEach(v -> anweisung.accept(v));
    } 

    /**
     * Methode, welche das Lager nach bestimmten Filterkriterien mit einer zusätzlichen
     * Anweisung filtert
     * 
     * @param filterkriterium gibt Filterkriterium wieder
     * @param anweisung gibt Anweisung wieder
     */
    public void applytoSomeArticles(Consumer<Artikel> anweisung, Predicate<Artikel> filterkriterium) {
        for (int i = 0; i < zahlArtikel; i++) {
            stream().filter(filterkriterium).forEach(anweisung);
        }
    }
    /**
     * 
     */
    public List<Artikel> getArticles(BiPredicate<Artikel,Artikel> a, Predicate<Artikel> filterkriterium) {

        // ArrayList<Artikel> angabe = new ArrayList<>();        
        // for (Artikel artikel : artikelMap.values()) {
            // if (filterkriterium.test(artikel)) {
                // angabe.add(artikel);
            // }
        // }

        // for (int i = 0; i < zahlArtikel; i++){
            // for (int j = i - 1; j >= 0; j--){
                // if (a.test(artikelDB[j] , artikelDB[j + 1])){
                    // Artikel sortiereMich = artikelDB[j];
                    // artikelDB[j] = artikelDB[j + 1];
                    // artikelDB[j + 1] = sortiereMich;
                // }
            // }
        // }
        // return angabe;
        
        return stream().filter(filterkriterium).sorted((k,v) -> a.test(k,v)?1:-1).collect(Collectors.toList());
    }
    public void minimain (){
        filter(filterNachCD);
        
    }
    public Stream<Artikel> stream() {
		return StreamSupport.stream(this.spliterator(), false);
	}
	
    @Override
    public Iterator<Artikel> iterator(){
        return new Iterator<Artikel>(){
            
            Iterator<Integer> iter = artikelMap.keySet().iterator();
            
            @Override 
            public boolean hasNext(){
                
                return iter.hasNext();
            }
            
            @Override
            public Artikel next(){
                return artikelMap.get(iter.next());
            }
    };
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

    // Filtere nach CDs und erhöhe um 10% am Preis
    Predicate <Artikel> filterNachCD = (artikel) ->
            (artikel instanceof CD);
            
    // Reduziere alle Artikel mit Bestand von 2 um 5%
    Predicate <Artikel> bestandZwei = (artikel) ->
             artikel.getArtikelbestand() == ZWEI;

    Consumer <Artikel> erhoeheUmZehnProzent = (artikel) ->
            (artikel.setartikelpreis(artikel.getArtikelpreis() * ZEHNPROZENT));

    // Reduziere alle Bücher eines Autors um 5%
    Predicate <Artikel> filterNachBuch = (artikel) ->
            (artikel instanceof Buch);

    Consumer <Artikel> senkeUmFuenfProzentNachAutor = (artikel) -> {
            Buch b = (Buch) artikel;
            if (b.getAutor().equals("Cixin, L.")) {
                artikel.setartikelpreis(artikel.getArtikelpreis() - artikel.getArtikelpreis()/ZWANZIG);
            }
        };

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
        for (Artikel aktuellerArtikel: this) {            
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

    /**
     * Check-Methode, die eine Bedingung auf wahr oder falsch prüft und Fehlermeldung ausgibt
     * 
     * @param bedingung gibt Bedingung zurück
     */
    private static void check(boolean bedingung, String msg) {
        if (!bedingung)
            throw new IllegalArgumentException(msg);
    }
}
