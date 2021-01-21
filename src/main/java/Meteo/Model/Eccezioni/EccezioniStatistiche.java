package Meteo.Model.Eccezioni;

/**
 * @Author Mariucci,Trombetta,D'Apote
 */
public class EccezioniStatistiche extends Exception {

    public static final long serialVersion=3L;

    public EccezioniStatistiche(){
        super();
    }

    /**
     * @param messaggio Description of the Exception
     */

    public EccezioniStatistiche(String messaggio){
        super(messaggio);
    }
}
