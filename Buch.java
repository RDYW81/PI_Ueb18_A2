
/**
 * Write a description of class Buch here.
 *
 * @author Roland Daidone, Michael Linn
 * @version 1.0
 */
public class Buch extends Artikel
{
    private String autor;
    private String titel;
    private String verlag;

    /** 
     *  Buch-Objekt Konstruktor
     *  
     *  Methoden bzw. Operatoren - Definition eines Buch-Objekts
     *  
     *  @param artikelnummer    - die wiederzugebende Artikelnummer
     *  @param bezeichnung      - die wiederzugebende Bezeichnung
     *  @param bestand          - der wiederzugebende Bestand
     *  @param preis            - der wiederzugebende Preis
     *  @param titel            - der wiederzugebende Titel des Buches
     *  @param autor            - der wiederzugebende Autor des Buches
     *  @param verlag           - der wiederzugebende Verlag des Buches
     */
    public Buch(int artikelnummer, String artikelbezeichnung, int artikelbestand, double artikelpreis, 
    String titel, String autor, String verlag) {
        super(artikelnummer, artikelbezeichnung, artikelbestand, artikelpreis);
        this.titel  = titel;
        this.autor  = autor;
        this.verlag = verlag;
    }

    /**
     * Gibt Buch-Titel zurück
     */
    public String getTitel() {
        return titel;
    }
    
    /**
     * Gibt Autor zurück
     */
    public String getAutor() {
        return autor;
    }
    
    /**
     * Gibt Verlag zurück
     */
    public String getVerlag() {
        return verlag;
    }
    
    /**
     * Bezieht sich auf die übergeordnete Klasse Artikel
     * und gibt den Autor und den Buch-Titel zurück
     */
    public String getBeschreibung() {
        return super.getBeschreibung() + "  " + autor + ": " + titel; 
    }
    
    public static void check(boolean bedingung, String msg) {
        if (!bedingung)
        throw new BuchRuntimeException (msg);
    }
}

