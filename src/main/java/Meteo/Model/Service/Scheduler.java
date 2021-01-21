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


@Component
public class Scheduler {

    /**
     * @Autowired viene utilizzato poichè, essendo  MeteoRepository  un componente, esso viene
     * considerato come una classe la cui unica istanza viene gestita dal FrameWork
     */
    @Autowired
    MeteoRepository meteoRepository;

    @Autowired
    Configurations configurations;


    /**
     * @Autowired viene utilizzato poichè, essendo Configurations un componente, esso viene
     * considerato come una classe la cui unica istanza viene gestita dal FrameWork
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




/*
@Scheduled ( fixedRate = 60000 )//3600000
    public void ConnectToOpenWeather2 ( ) throws IOException, ParseException {
        final Logger logger = LoggerFactory.getLogger(Scheduler.class);
        JSONArray lista2 = leanguagesConfiguration.getLeanConfig();
        if (StaticConfig.getCallOpenWeather()) {
            for (Object lang : lista2) {
                logger.info("scritto in " + lang.toString());
                SpazioVariabili spazioVariabili2 = new SpazioVariabili();
                spazioVariabili2.getFromParse(Long.valueOf(lang.toString()));
                meteoRepository.save(spazioVariabili2);
            }

        }else {
            logger.info("Chiamata ad OpenWeather non attiva");
        }
    }
 */















/*
 JSONArray lista2=leanguagesConfiguration.getLeanConfig();
                Object lang=new Object();
                    logger.info("Recupero i dati di "+lang.toString());
                    FileReader f = new FileReader("leanConfig.json");
                    BufferedReader b = new BufferedReader(f);
                    String s= b.readLine();
                    switch (s){
                        case "[\"it\"]":
                            SpazioVariabili it = new SpazioVariabili();
                            it.incIT();
                            System.out.println(it.getIt());
                            meteoRepository.save(it);break;
                        case "[\"de\"]":
                            SpazioVariabili de = new SpazioVariabili();
                            de.incIT();
                            System.out.println(de.getDe());
                            meteoRepository.save(de);break;
                    }
 */
