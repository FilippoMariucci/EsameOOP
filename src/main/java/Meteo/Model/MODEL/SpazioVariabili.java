package Meteo.Model.MODEL;

import Meteo.Model.Utilities.OpenWeatherParse;
import org.json.simple.parser.ParseException;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "Meteo")
public class SpazioVariabili {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long Id;
    private Long CityId;
    private Long Epoch;
    private String lang;

    private Double temp;
    private Double tempPerc;
    private String nomeCitta;


    public SpazioVariabili() {
    }


    public SpazioVariabili(Long CityId, String lang, Long Epoch, Double tempMed, Double tempPerc, String nomeCitta) {
        this.CityId = CityId;
        this.lang = lang;
        this.Epoch = Epoch;
        this.temp = tempMed;
        this.tempPerc = tempPerc;
        this.nomeCitta = nomeCitta;
    }


    public Long getCityId() {
        return CityId;
    }

    public void setCityId(Long cityId) {
        this.CityId = cityId;
    }

    public String getLang() {
        return lang;
    }

    public Long getEpoch() {
        return Epoch;
    }

    public void setEpoch(Long epoch) {
        Epoch = epoch;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


    public double getTemp() {
        return temp;
    }

    public void setTemp(double tempRe) {
        this.temp = tempRe;
    }

    public double getTempPerc() {
        return tempPerc;
    }

    public void setTempPerc(double tempPerc) {
        this.tempPerc = tempPerc;
    }

    public String getNomeCitta() {
        return nomeCitta;
    }

    public void setNomeCitta(String nomeCitta) {
        this.nomeCitta = nomeCitta;
    }


    /* rihiamo dal parse i valori e li assegno alle varibili attraverso i vari metodi get*/

    public void getFromParse(Long CityId) throws IOException, ParseException {
        OpenWeatherParse openWeatherParse = new OpenWeatherParse(CityId);
        openWeatherParse.parse();
        this.CityId = openWeatherParse.getCityId();
        this.Epoch = openWeatherParse.getEpoch();
        this.temp = openWeatherParse.getTemp();
        this.nomeCitta = openWeatherParse.getNomeCitta();
        this.tempPerc = openWeatherParse.getTempPerc();

    }
}
