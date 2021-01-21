package Meteo.Model.Service;

import Meteo.Model.Controller.Configurations;
import Meteo.Model.Controller.LeanguagesConfiguration;
import Meteo.Model.MODEL.SpazioVariabili;
import Meteo.Model.Repository.MeteoRepository;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 *
 * Classe che viene attivata periodicamente dal Framework Spring richiamando l'API di OpenWeather
 * seguendo le configurazioni attuali;infine salva i dati nel repository MeteoRepository
 * @Component annotazione che definisce la classe come componente autogestito da Spring
 *
 * @author Mariucci,Trombetta,D'Apote
 */

@Component
public class Scheduler {

    /**
     * @Autowired viene utilizzato poichè, essendo  MeteoRepository  un componente, esso viene
     * considerato come una classe la cui unica istanza viene gestita dal FrameWork
     */
    @Autowired
    MeteoRepository meteoRepository;


    /**
     * @Autowired viene utilizzato poichè, essendo Configurations un componente, esso viene
     * considerato come una classe la cui unica istanza viene gestita dal FrameWork
     */
    @Autowired
    Configurations configurations;





    /**
     * @Scheduled annotazione che indica che il metodo verrà richiamato da un TaskScheduler
     * seguendo l'intervallo prefissato con fixedRateString
     * Il metodo chiama l'API di OpenWeather seguendo le configurazioni
     * attuali e salva i rispettivi dati nel repository MeteoRepository
     */

    @Scheduled ( fixedRateString = "${meteo.interval}" )//3600000
    public void ConnectToOpenWeather ( ) throws IOException, ParseException {


        final Logger logger = LoggerFactory.getLogger(Scheduler.class);
        JSONArray lista = configurations.getConfig();
        if (StaticConfig.getCallOpenWeather()) {
            for (Object city : lista) {
                logger.info("Recupero i dati  di " + city.toString());
                SpazioVariabili spazioVariabili = new SpazioVariabili();
                spazioVariabili.getFromParse(Long.valueOf(city.toString()));
                meteoRepository.save(spazioVariabili);
            }


        } else {
            logger.info("Chiamata ad OpenWeather non attiva");
        }


    }
}


