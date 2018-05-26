/**
 * Eine einfache Artikel-Klasse.
 * 
 * @author Roland Daidone, Michael Linn
 * @version 1.5
 */
public class Artikel
{
    private static final int EINHUNDERT    = 100;
    private static final int MIN_ARTIKELNR = 1000;
    private static final int MAX_ARTIKELNR = 9999;

    private int nummer = 0;
    private String bezeichnung;
    private String leerfeld = "";
    private int bestand = 0;
    private double preis;

    /** 
     *  Artikel-Objekt konstruieren
     *  
     *  Methoden bzw. Operatoren - Definition des Verhaltens der Bestandsfuehrung
     *  @param artikelnummer - Die Artikelnummer muss eine 4-stellige positive Zahl sein
     *  @param bezeichnung   - Die Bezeichnung darf nicht null sein
     *  @param bestand       - Der Bestand darf nie kleiner als 0 werden
     *  @param preis         - Der Preis darf nie kleiner als 0 werden
     */

    public Artikel(int artikelnummer, String artikelbezeichnung, int artikelbestand, double artikelpreis) {
        if (artikelnummer <MIN_ARTIKELNR || artikelnummer >MAX_ARTIKELNR) // Bedingung einer positiven vierstelligen Artikelnummer
            throw new IllegalArgumentException ("Nur vierstellige Artikelnummer angeben");
        if (artikelbezeichnung.trim().isEmpty())   // Bedingung für eine ausgefüllte Artikelbezeichnung
            throw new IllegalArgumentException ("Artikelbezeichnung muss angeben werden");
        if (artikelbestand < 0)                    // Bedingung das der Bestand nie unter 0 fällt
            throw new IllegalArgumentException ("Artikel kann nicht weniger als 0 sein");
        if (artikelpreis <=0)
            throw new IllegalArgumentException ("Nichts ist umsonst! Daher Preis höher als 0 EUR definieren.");

        this.nummer = artikelnummer;
        this.bezeichnung = artikelbezeichnung;
        setartikelbezeichnung(artikelbezeichnung);
        this.preis = artikelpreis;
        setartikelpreis(artikelpreis);
        this.bestand = artikelbestand;
    }

    /**
     * Artikel-Objekt mit zwei Argumenten konstruieren
     * (wird genutzt wenn der Benutzer keinen Artikelbestand und keinen Artikelpreis definiert)
     * 
     * @param Die Artikelnummer muss eine 4-stellige positive Zahl sein
     * @param Die Bezeichnung darf nicht null sein
     */
    public Artikel(int artikelnummer, String artikelbezeichnung) {
        this(artikelnummer, artikelbezeichnung, 0, 0.0);
    }

    /**
     * Artikel-Objekt mit einem Argument konstruieren
     * (wird genutzt wenn der Benutzer keinen Artikelpreis definiert)
     * 
     * @param Die Artikelnummer muss eine 4-stellige positive Zahl sein
     * @param Die Bezeichnung darf nicht null sein
     */
    public Artikel(int artikelnummer, String artikelbezeichnung, int artikelbestand) {
        this(artikelnummer, artikelbezeichnung, artikelbestand, 0.0);
    }

    public String toString() {
        String euro = "\u20ac";
        return "Artikel: "   + nummer
        + ", Bezeichnung: "  + bezeichnung
        + ", Bestand: "      + bestand + " Einheiten" 
        + ", Preis: "        + preis + euro;
    }

    /**
     *  @param  menge - ist die zuzubuchende Menge
     */
    public void zugangsBuchung(int menge) {
        if (menge <0)           // Zugang von Artikeln mit rein positiven Zahlen
            menge =0;
        bestand = bestand + menge;
    }

    /**
     *  @param  menge - ist die abzubuchende Menge
     */
    public void zugangsAbbuchung(int menge) {
        if (menge >=0) 
            bestand = bestand - menge;
        else bestand = bestand + menge;  // Falls der User einen Zugang mit negativ gebuchten Artikel versucht
        if (bestand <0)         // Abgang von Artikeln mit Regel, dass der Artikelbestand nie unter 0 fällt
            bestand =0;
    }

    /**
     *  @param  prozent - ist die prozentuale Preiserhöhung
     */
    public void preisErhoehung(double prozent) {      
        if (prozent <= 0)           // Zugang von Artikeln mit rein positiven Zahlen
            throw new IllegalArgumentException ("Nur positive Prozent-Werte bei Preiserhöhung angeben. ");

        double prozentwert = (preis * prozent)/ EINHUNDERT;
        preis = preis + prozentwert;
        preis = Math.round (preis * EINHUNDERT) / 100.0;
    }

    /**
     *  @param  prozent - ist die prozentuale Preissenkung
     */
    public void preisSenkung(double prozent) {
        if (prozent <= 0 || prozent >= EINHUNDERT) 
            throw new IllegalArgumentException ("Der Preis fällt unter 0 oder \n der Prozentwert zur Preissenkung war mind. 100% oder höher. Wir verschenken nichts. ");

        double prozentwert = (preis * prozent)/ EINHUNDERT;
        preis = preis - prozentwert;
        preis = Math.round (preis * EINHUNDERT) / 100.0;
    }

    /**
     * Gibt Artikelnummer wieder
     */
    public int getArtikelnummer()
    {
        return this.nummer;
    }

    /**
     * Gibt Artikelbezeichnung wieder
     */
    public String getArtikelbezeichnung()
    {
        return this.bezeichnung;
    }

    /**
     * Gibt Artikelbeschreibung wieder
     */
    public String getBeschreibung()
    {
        return this.leerfeld; 
    }

    /**
     * Gibt Artikelbestand wieder
     */
    public int getArtikelbestand()
    {
        if (bestand <0)
            throw new IllegalArgumentException ("Artikel kann nicht weniger als 0 sein");
        return this.bestand;
    }

    /**
     * Gibt Artikelpreis wieder
     */
    public double getArtikelpreis()
    {
        return this.preis;
    }

    /**
     * Setze Artikelnummer fest
     * @param artikelnummer - neue Artikelnummer
     */
    public void setartikelnummer(int artikelnummer)
    {
        if (artikelnummer <MIN_ARTIKELNR || artikelnummer >MAX_ARTIKELNR)
            throw new IllegalArgumentException ("Nur vierstellige Artikelnummer angeben");
        this.nummer = artikelnummer;
    }

    /**
     * Setze Artikelbezeichnung fest
     * @param artikelbezeichnung - neue Artikelbezeichnung
     */
    public void setartikelbezeichnung (String artikelbezeichnung)
    {
        if (artikelbezeichnung.trim().isEmpty())
            throw new IllegalArgumentException ("Artikelbezeichnung muss angeben werden");
        this.bezeichnung = artikelbezeichnung;
    }

    /**
     * Setze Artikelpreis fest (dient zur Wiederherstellung des Ausgangswerts eines Preises 
     * ohne ein erneutes Rechenverfahren in Prozent)
     * 
     * @param artikelpreis - neuer Artikelpreis
     */
    public void setartikelpreis(double artikelpreis)
    {
        if (artikelpreis <= 0)
            throw new IllegalArgumentException ("Nichts ist umsonst! Daher Preis höher als 0 EUR definieren. ");
        this.preis = artikelpreis;
    }
}
