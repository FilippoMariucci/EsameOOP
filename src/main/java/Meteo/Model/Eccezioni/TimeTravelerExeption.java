package Meteo.Model.Eccezioni;


/**
 * @Author Mariucci,Trombetta,D'Apote
 */
public class TimeTravelerExeption extends Exception {
    private static final long serialVersionUID = 1L;


    public TimeTravelerExeption() {
        super();
    }

    /**
     * @param message Description of the Exception
     */
    public TimeTravelerExeption(String message) {
        super(message);
    }
}
