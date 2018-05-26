
/**
 * Write a description of class CD here.
 *
 * @author Roland Daidone, Michael Linn
 * @version 1.0
 */
public class CD extends Artikel
{
    private String interpret;
    private String titel;
    private int anzahlMusiktitel;

    /** 
     *  CD-Objekt Konstruktor
     *  
     *  Methoden bzw. Operatoren - Definition eines CD-Objekts
     *  
     *  @param artikelnummer    - die wiederzugebende Artikelnummer
     *  @param bezeichnung      - die wiederzugebende Bezeichnung
     *  @param bestand          - der wiederzugebende Bestand
     *  @param preis            - der wiederzugebende Preis
     *  @param interpret        - der wiederzugebende Interpret der Musik
     *  @param titel            - der wiederzugebende Titel der CD
     *  @param anzahlMusiktitel - die wiederzugebende Anzahl der Musiktitel
     */
    public CD(int artikelnummer, String artikelbezeichnung, int artikelbestand, double artikelpreis, 
    String interpret, String titel, int anzahlMusiktitel) {
        super(artikelnummer, artikelbezeichnung, artikelbestand, artikelpreis);
        this.interpret        = interpret;
        this.titel            = titel;
        this.anzahlMusiktitel = anzahlMusiktitel;
    }

    /**
     * Gibt Interpret der Musik zurück
     */
    public String getInterpret() {
        return interpret;
    }

    /**
     * Gibt Titel der CD zurück
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Gibt Anzahl der Musiktitel zurück
     */
    public int getAnzahlMusiktitel() {
        return anzahlMusiktitel;
    }

    /**
     * Bezieht sich auf die übergeordnete Klasse Artikel
     * und gibt den Interpret der Musik und den Titel der CD zurück
     */
    public String getBeschreibung() {
        return super.getBeschreibung() + "  " + interpret + ": " + titel;
    }

    public static void check(boolean bedingung, String msg) {
        if (!bedingung)
        throw new CDRuntimeException (msg);
    }
}
