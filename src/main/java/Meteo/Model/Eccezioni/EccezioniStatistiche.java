package Meteo.Model.Eccezioni;

public class EccezioniStatistiche extends Exception {
    public static final long serialVersion=3L;

    public EccezioniStatistiche(){
        super();
    }

    public EccezioniStatistiche(String messaggio){
        super(messaggio);
    }
}
