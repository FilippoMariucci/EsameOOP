package Meteo.Model.MODEL;

import Meteo.Model.Utilities.OpenWeatherParse;
import org.json.simple.parser.ParseException;

import javax.persistence.*;
import java.io.IOException;
/**
 *
 * Classe che descrive il modello dei dati contenuti nell'archivio
 * @Entity è un'annotazione che specifica che la classe è un'entità ed è mappata in una database Table.
 * @Table è un'annotazione che specifica il nome della tabella nel database usata per la mappatura.
 *
 * @author Mariucci,Trombetta,D'Apote
 */
@Entity
@Table(name = "Meteo")
public class SpazioVariabili {
    /**
     * identificatore autogenerato dell'entità
     *
     * @Id è un'annotazione che specifica l'identificatore di un'entità
     * @GeneratedValue si occupa di specificare la strategia di generazione per i valori di questi identificatori
     */

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    @Basic ( optional = false )
    @Column ( name = "id", unique = true, nullable = false )

    private Long Id;
    /**
     * ID della città
     */
    private Long CityId;
    /**
     * Istante temporale in formato UNIX
     */
    private Long Epoch;
    /**
     * lingua scelta
     */
    private String lang;
    /**
     * Temperatura Reale
     */
    private Double temp;
    /**
     * Temperatura Percepita
     */
    private Double tempPerc;


    /**
     * Costruttore di default
     */
    public SpazioVariabili ( ) {

    }

    /**
     * Costruttore con parametri
     *
     * @param CityId
     * @param lang
     * @param Epoch
     * @param tempMed
     * @param tempPerc
     */
    public SpazioVariabili ( Long CityId, String lang, Long Epoch, Double tempMed, Double tempPerc ) {
        this.CityId = CityId;
        this.lang = lang;
        this.Epoch = Epoch;
        this.temp = tempMed;
        this.tempPerc = tempPerc;

    }


    public Long getCityId ( ) {
        return CityId;
    }

    public void setCityId ( Long cityId ) {
        this.CityId = cityId;
    }

    public String getLang ( ) {
        return lang;
    }

    public Long getEpoch ( ) {
        return Epoch;
    }

    public void setEpoch ( Long epoch ) {
        Epoch = epoch;
    }

    public void setLang ( String lang ) {
        this.lang = lang;
    }

    public double getTemp ( ) {
        return temp;
    }


    public void setTemp ( double tempRe ) {
        this.temp = tempRe;
    }

    public double getTempPerc ( ) {
        return tempPerc;
    }

    public void setTempPerc ( double tempPerc ) {
        this.tempPerc = tempPerc;
    }


    /**
     * Metodo utilizzato per stanziare i valori nelle apposite variabili effettuando il parse dell'API
     *
     * @param CityId
     * @throws IOException
     * @throws ParseException
     */
    public void getFromParse ( Long CityId ) throws IOException, ParseException {
        OpenWeatherParse openWeatherParse = new OpenWeatherParse(CityId);
        openWeatherParse.parse();
        this.CityId = openWeatherParse.getCityId();
        this.Epoch = openWeatherParse.getEpoch();
        this.temp = openWeatherParse.getTemp();
        this.tempPerc = openWeatherParse.getTempPerc();


    }
}