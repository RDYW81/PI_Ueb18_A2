import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse LagerDialog.
 * 
 * @author Roland Daidone, Michael Linn 
 * @version 1.1
 */
public class LagerDialog
{
    private Lager lager;
    private Artikel artikel;
    private Scanner input = new Scanner(System.in);

    // Konstanten für das LagerDialog - Auswahlmoeglichkeiten
    private static final int ARTIKELANLEGEN        = 1;
    private static final int ARTIKELHINZUFUEGEN    = 2;
    private static final int ARTIKELENTFERNEN      = 3;
    private static final int ARTIKELPREIS          = 4;
    private static final int ARTIKELPREISERHOEHUNG = 5;
    private static final int ARTIKELPREISSENKUNG   = 6;
    private static final int ARTIKELBEZEICHNUNG    = 7;
    private static final int ARTIKELLOESCHEN       = 8;
    private static final int AUSGABEBESTANDSLISTE  = 9;
    private static final int ENDE                  = 0;

    // Konstanten für die Artikelauswahl bei ARTIKELANLEGEN
    private static final int BUCH                  = 1;
    private static final int CD                    = 2;
    private static final int DVD                   = 3;
    private static final int SONSTIGES             = 4;
    
    // Nachrichtenmeldungen
    private static final String MSG_ARTIKELPREIS =
        "Artikelpreis: ";
    private static final String MSG_ANGABE_PROZENT =
        "Um wie viel Prozent?: ";
    private static final String MSG_ARTIKELNUMMER =
        "Artikelnummer: ";
    private static final String MSG_ANGABE_EINHEITEN =    
        "Um wie viele Einheiten?: ";
    private static final String MSG_BESTAND =
        "Bestand: ";
    private static final String MSG_BEZEICHNUNG =
        "Bezeichnung: ";
    private static final String MSG_GENRE =
        "Genre: ";
    private static final String MSG_LITERATURGATTUNG =
        "Literaturgattung: ";
    private static final String MSG_TITEL =
        "Titel: ";
    private static final String MSG_AUTOR =
        "Autor: ";
    private static final String MSG_VERLAG =
        "Verlag: ";
    private static final String MSG_INTERPRET =
        "Interpret: ";
    private static final String MSG_ANZAHL_MUSIKTITEL =
        "Anzahl der Musiktitel?: ";
    private static final String MSG_SPIELDAUER_IN_MINUTEN =
        "Spieldauer (Minutenangabe): ";
    private static final String MSG_ERSCHEINUNGSJAHR =
        "Erscheinungsjahr: ";
        

    /**
     * Main-Methode zum Erzeugen des LagerDialog-Objekts
     * Start der Testschleife
     */
    public static void main(String[] args) {
        new LagerDialog().start();
    }

    /**
     * Hauptschleife des Programms
     */
    public void start() {
        lager = new Lager ("Warenlager", 20);
        int funktion = -1;

        while (funktion !=ENDE) {
            try {
                funktion = einlesenFunktion();
                ausfuehrenFunktion(funktion);
                input.nextLine();
            } catch (BuchRuntimeException | DVDRuntimeException | CDRuntimeException | 
                     IllegalArgumentException | java.lang.NullPointerException e) {
                System.err.println("Fehler bei Artikelbehandlung im Lager" + e);
            } catch (java.util.InputMismatchException e) {
                System.err.println(e);
                input.next();
            } catch (Exception e) {
                System.err.println("Ausnahme: " + e);
                e.printStackTrace(System.out);
            }
        }
    }

    /**
     * Anzeige des Menues und Verarbeitung der Eingabefunktion.
     * 
     * @param eingegebene Funktion ist ganzzahliger Wert
     */
    private int einlesenFunktion() {
        System.out.print (
            ARTIKELANLEGEN          + ": Artikel anlegen \n"
            + ARTIKELHINZUFUEGEN    + ": Artikelbestand erhöhen; \n"
            + ARTIKELENTFERNEN      + ": Artikelbestand reduzieren; \n"
            + ARTIKELPREIS          + ": Artikel-Preis definieren; \n"
            + ARTIKELPREISERHOEHUNG + ": Artikel-Preis in % erhöhen; \n"
            + ARTIKELPREISSENKUNG   + ": Artikel-Preis in % senken; \n"
            + ARTIKELLOESCHEN       + ": Artikel aus Lager löschen; \n"
            + AUSGABEBESTANDSLISTE  + ": Ausgabe der Bestandsliste; \n"
            + ENDE                  + ": Programm beenden -> ");

        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Ungültiger Eingabeparameter. Bitte wählen Sie einen Menüpunkt");
        }
        return input.nextInt();
    }

    /**
     * Anzeige des Untermenues unter ARTIKELANLEGEN und Verarbeitung der Eingabefunktion.
     * 
     * @param eingegebene Funktion ist ganzzahliger Wert
     */
    private int einlesenUnterfunktion() {
        System.out.print (
            BUCH                    + ": Buch anlegen \n"
            + CD                    + ": CD anlegen; \n"
            + DVD                   + ": DVD anlegen; \n"
            + SONSTIGES             + ": Standardartikel anlegen; \n"
            + ENDE                  + ": Zurück zum Hauptmenü -> ");

        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Ungültiger Eingabeparameter. Bitte wählen Sie einen Untermenüpunkt");
        }
        return input.nextInt();
    }
        
    /**
     * Fuehrt ausgewaehlte Funktion aus
     * 
     * @param funktion auszufuehrende Funktion als Eingabe durch ganze Zahl
     */
    private void ausfuehrenFunktion(int funktion) {
        switch (funktion) {
            case ARTIKELANLEGEN:
                int unterfunktion = -1;
                while (unterfunktion != ENDE) {
                unterfunktion = einlesenUnterfunktion();
                Artikel artikel;
                switch (unterfunktion) {
                    case BUCH:
                        artikel = new Buch(readInt(MSG_ARTIKELNUMMER), readString(MSG_LITERATURGATTUNG), readInt(MSG_BESTAND), readDouble(MSG_ARTIKELPREIS), 
                                           readString(MSG_TITEL), readString(MSG_AUTOR), readString(MSG_VERLAG));
                        System.out.println("Buch: " + artikel.getBeschreibung());
                        lager.artikelAnlegen(artikel);
                        break;

                    case CD:
                        artikel = new CD(readInt(MSG_ARTIKELNUMMER), readString(MSG_GENRE), readInt(MSG_BESTAND), readDouble(MSG_ARTIKELPREIS), 
                                         readString(MSG_INTERPRET), readString(MSG_TITEL), readInt(MSG_ANZAHL_MUSIKTITEL));
                        System.out.println("CD: " + artikel.getBeschreibung());
                        lager.artikelAnlegen(artikel);
                        break;

                    case DVD:
                        artikel = new DVD(readInt(MSG_ARTIKELNUMMER), readString(MSG_GENRE), readInt(MSG_BESTAND), readDouble(MSG_ARTIKELPREIS), 
                                          readString(MSG_TITEL), readInt(MSG_SPIELDAUER_IN_MINUTEN), readInt(MSG_ERSCHEINUNGSJAHR));
                        System.out.println("DVD: " + artikel.getBeschreibung());
                        lager.artikelAnlegen(artikel);
                        break;

                    case SONSTIGES:
                        artikel = new Artikel(readInt(MSG_ARTIKELNUMMER), readString(MSG_BEZEICHNUNG), readInt(MSG_BESTAND), readDouble(MSG_ARTIKELPREIS));
                        System.out.println("Artikel" + artikel.getBeschreibung());
                        lager.artikelAnlegen(artikel);
                        break;
                        
                    case ENDE:
                        break;
                        
                    default:
                        System.out.println("Ungültiger Eingabeparameter. Bitte wählen Sie einen Menüpunkt");

                }
                System.out.println(lager);
            }
            break;

            case ARTIKELHINZUFUEGEN:
                lager.zugangsBuchung(readInt(MSG_ARTIKELNUMMER), readInt(MSG_ANGABE_EINHEITEN));
                break;

            case ARTIKELENTFERNEN:
                lager.zugangsAbbuchung(readInt(MSG_ARTIKELNUMMER), readInt(MSG_ANGABE_EINHEITEN));
                break;

            case ARTIKELPREIS:
                lager.artikelpreisDefinieren(readInt(MSG_ARTIKELNUMMER), readDouble(MSG_ARTIKELPREIS));
                break;

            case ARTIKELPREISERHOEHUNG:
                lager.artikelpreisErhoehung(readInt(MSG_ARTIKELNUMMER), readDouble(MSG_ANGABE_PROZENT));
                break;

            case ARTIKELPREISSENKUNG:
                lager.artikelpreisSenkung(readInt(MSG_ARTIKELNUMMER), readDouble(MSG_ANGABE_PROZENT));
                break;

            case ARTIKELLOESCHEN:
                lager.delArtikel(readInt(MSG_ARTIKELNUMMER));
                break;
                
            case AUSGABEBESTANDSLISTE:
                lager.ausgebenBestandsliste();
                break;

            case ENDE:
                System.out.println("Programm beendet");
                break;

            default:
                System.out.println("Ungültiger Eingabeparameter. Bitte wählen Sie einen Menüpunkt"); 

        }
        System.out.println(lager);
    }
   
    /**
     * Anzeige entsprechender Nachrichten bei Einlesen (=Ausfuehrung) von Funktionen: 
     * @param int - gibt Ausgabe nach ganzzahliger Eingabe wieder
     * @param double - gibt Ausgabe nach Gleitkommavariable-Eingabe wieder
     * @param String - gibt Ausgabe nach String-Eingabe wieder
     * 
     * @return MSG - MSG auszugebende Nachricht
     */ 
        private int readInt(String msg) {
        System.out.print(msg);
        int temp = skipToNextInt();
        input.nextLine();
        return temp;
    } 
       
    private double readDouble(String msg) {
        System.out.print(msg);
        double temp = skipToNextDouble();
        input.nextLine();
        return temp;
    }
    
    private String readString(String msg) {
        System.out.print(msg); 
        return input.nextLine();
    }

    //Sicherungseinstellungen für korrekte Eingaben
    private int skipToNextInt() {
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Bitte ganze Zahlen eingeben!");
        }
        return input.nextInt();
    }

    private double skipToNextDouble() {
        while (!input.hasNextDouble()) {
            input.next();
            System.out.println("Bitte ganze Zahlen oder Zahlen mit Komma-Stelle angeben!");
        }
        return input.nextDouble();
    }
}