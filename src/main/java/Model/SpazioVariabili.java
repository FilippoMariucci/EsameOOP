package Model;

import Utilities.OpenWeatherParse;
import org.json.simple.parser.ParseException;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;

public class SpazioVariabili {
    @Id
    private Long Id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String CityId;
    private Long Epoch;
    private String lang;

    private Double temp;
    private Double tempPerc;
    private String nomeCitta;


    public SpazioVariabili() {}


    public SpazioVariabili(String CityId, String lang, Long Epoch, Double tempMed, Double tempPerc, String nomeCitta) {
        this.CityId = CityId;
        this.lang = lang;
        this.Epoch=Epoch;
        this.temp = tempMed;
        this.tempPerc = tempPerc;
        this.nomeCitta = nomeCitta;
    }



    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
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

    public void getFromParse(String CityId) throws IOException, ParseException {
        OpenWeatherParse openWeatherParse=new OpenWeatherParse(CityId);
        openWeatherParse.parse();
        this.CityId=openWeatherParse.getCityId();
        this.Epoch=openWeatherParse.getEpoch();
        this.temp=openWeatherParse.getTemp();
        this.nomeCitta=openWeatherParse.getNomeCitta();
        this.tempPerc=openWeatherParse.getTempPerc();

    }
