import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse ArtikelTest.
 *
 * @author  Roland Daidone
 * @version 0.1
 */
public class ArtikelTest
{
    private Artikel artikel1;
    private Artikel artikel2;
    
    /**
     * Konstruktor fuer die Test-Klasse ArtikelTest
     */
    public ArtikelTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp()
    { artikel1 = new Artikel (1234, "Test", 100, 100.0); 
    }

    @Test 
    
    public void testZugangsbuchung() {
        artikel1.zugangsBuchung(100);
        assertEquals("Zugangsbuchung fehlgeschlagen", artikel1.getArtikelbestand(), 200);
    } 
    
    @Test 
    
    public void testZugangNegativeZahlBuchungWirdNull() {
        artikel1.zugangsBuchung(-1);
        assertEquals("Zugangsbuchung fehlgeschlagen", artikel1.getArtikelbestand(), 100);
    }
    
    //@Test (expected = IllegalArgumentException.class)
    //
    //public void testezugangsbuchungNegativeZahl() {
    //    artikel1.zugangsbuchung(-1);
    //}
    
    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()
    {
    }
}
