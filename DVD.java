
/**
 * Write a description of class DVD here.
 *
 * @author Roland Daidone, Michael Linn
 * @version 1.0
 */
public class DVD extends Artikel
{
    private static final int MIN_FILMJAHR  = 1950;
    private static final int MAX_FILMJAHR  = 2014;
    
    private String titel;
    private int spieldauerInMinuten;
    private int erscheinungsjahr;

    /** 
     *  DVD-Objekt Konstruktor
     *  
     *  Methoden bzw. Operatoren - Definition eines DVD-Objekts
     *  
     *  @param artikelnummer        - die wiederzugebende Artikelnummer
     *  @param bezeichnung          - die wiederzugebende Bezeichnung
     *  @param bestand              - der wiederzugebende Bestand
     *  @param preis                - der wiederzugebende Preis
     *  @param titel                - der wiederzugebende Titel des Films auf DVD
     *  @param spieldauerInMinuten  - die wiederzugebende Spieldauer des Films in Minuten
     *  @param erscheinungsjahr     - das wiederzugebende Erscheinungsjahr des Films zwischen 1950 und 2014
     */
    public DVD(int artikelnummer, String artikelbezeichnung, int artikelbestand, double artikelpreis, 
    String titel, int spieldauerInMinuten, int erscheinungsjahr) {
        super(artikelnummer, artikelbezeichnung, artikelbestand, artikelpreis);

        if (erscheinungsjahr < MIN_FILMJAHR || erscheinungsjahr > MAX_FILMJAHR) // Bedingung das der Film nicht älter oder jünger ist
            throw new IllegalArgumentException ("Das Erscheinungsjahr soll zwischen 1950 und 2014 liegen");
        
        this.titel               = titel;
        this.spieldauerInMinuten = spieldauerInMinuten;
        this.erscheinungsjahr    = erscheinungsjahr;
    }

    /**
     * Gibt Filmtitel zurück
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Gibt Spieldauer in Minuten zurück
     */
    public int getSpieldauerProMinute() {
        return spieldauerInMinuten;
    }

    /**
     * Gibt Erscheinungsjahr zurück
     */
    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    /**
     * Bezieht sich auf die übergeordnete Klasse Artikel
     * und gibt den DVD-Titel zurück
     */
    public String getBeschreibung() {
        return super.getBeschreibung() + "  " + titel;
    }
    
    public static void check(boolean bedingung, String msg) {
        if (!bedingung)
        throw new DVDRuntimeException (msg);
    }
}