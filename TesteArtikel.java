
/**
 * Testen zum ausgeben der Vererbung
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class TesteArtikel
{
    public void start(){
        Artikel [] aTab = new Artikel [3];
        aTab[0] = new CD ( 2222 , " CD " ,22, 2 ,"Interpret A " , "Titeeel ",25);
        aTab[1] = new DVD ( 2322 , " DVD " ,22, 2,"Titel B " , 5 ,2000);
        aTab[2] = new DVD ( 2332 , " DVD2 " ,22, 2,"Autor C " , 5 ,2002);
        
        for (int i = 0; i<3; i++){
            System.out.println( aTab[i].getBeschreibung());
        }
        
    }
 
    public static void main(String[] args) {
     new TesteArtikel().start();
    }

}
