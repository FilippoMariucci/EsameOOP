package Meteo.Model.Utilities;

import Meteo.Model.Service.StaticConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


/**
 * Classe che realizza l'interfacciamento con OpenWeather e va ad effettuare il parse con il  JSON ricevuto
 * popolando i vari attributi
 *
 * @author Mariucci,Trombetta,D'Apote
 */
public class OpenWeatherParse {
    /**
     * ID della città
     */
    private Long CityId;

    /**
     * Istante temporale delle misurazioni, espresso in formato UNIX
     */
    private long Epoch;

    /**
     * valore medio della temperatura
     */
    private double temp;
    /**
     * valore della temperatura percepita
     */
    private double tempPerc;
    /**
     * nome della città
     */
    private String nomeCitta;

    private static final Logger logger= LoggerFactory.getLogger(OpenWeatherParse.class);

    public OpenWeatherParse(Long CityId) {
        this.CityId=CityId;
    }


    public Long getCityId() {
        return CityId;
    }

    public long getEpoch() {
        return Epoch;
    }


    public double getTemp() {
        return temp;
    }

    public double getTempPerc() {
        return tempPerc;
    }

    public String getNomeCitta() {
        return nomeCitta;
    }


    /**
     * Metodo per realizzare l'interfaccia con OpenWeather
     * Effettua il parse con il  JSON ricevuto per popolare i suoi  attributi
     */
    public void parse(){
        JSONParser parser=new JSONParser();
        JSONObject obj=null;
        RestTemplate restTemplate= new RestTemplate();
        String result=restTemplate.getForObject(
                "http://api.openweathermap.org/data/2.5/weather?lang=it&id="+this.CityId+"&appid=acff9fc7b20e0ff3ebb1f1615f76abb1&units=metric",String.class
        );
        logger.info(result);

        try {
            obj=(JSONObject) parser.parse(result);
            this.CityId= (Long) obj.get("id");
            this.nomeCitta=(String) obj.get("name");
            this.Epoch=(Long)obj.get("dt");
            JSONObject main=(JSONObject) obj.get("main");
            this.temp=Double.parseDouble(main.get("temp").toString());
            this.tempPerc=Double.parseDouble(main.get("feels_like").toString());
        }catch (ParseException e){
            logger.error(e.toString());
        }
    }
}
