package Meteo.Model.Eccezioni;

public class EccezioniStatistiche extends Exception {
    //Ghjk
    public static final long serialVersion=3L;

    public EccezioniStatistiche(){
        super();
    }

    public EccezioniStatistiche(String messaggio){
        super(messaggio);
    }
}
