package Meteo.Model.ApiUtente;

import Meteo.Model.Controller.Configurations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Classe che espone le API per le configurazioni e gestisce le chiamate effettuabili tramite:
 * metodo HTTP GET per leggere le configurazioni attuali,
 * metodo HTTP POST per modificare le configurazioni attuali.
 * Si serve della classe Configurations per leggere o modificare le configurazioni.
 *
 * @author Mariucci,Trombetta,D'Apote
 * @RestController è l'annotazione utilizzata per definire i servizi web RESTful
 */
@RestController
public class ConfigurationsApi {

        private static final Logger logger = LoggerFactory.getLogger(ConfigurationsApi.class);

        /**
         * @Autowired viene utilizzato perchè Configurations è un componente e dunque
         * è una classe la cui unica istanza viene gestita dal FrameWork,
         * non istanzio io nuovi oggetti, ma utilizzo quelli tenuti in vita dal FrameWork
         */
        @Autowired
        Configurations configurazioni;

        /**
         * @return the JSONArray containing the cities in the configurations
         * @RequestMapping è l'annotazione usata per definire il Request URI per accedere
         * agli Endpoint REST
         */
        @RequestMapping(value = "/config")
        public JSONArray leggiConfig() {
            return configurazioni.getConfig();
        }

        /**
         * @param confStr Stringa contenente il JSON body richiesto
         * @return the JSONObject containing information on success/failure of the operation
         * @RequestMapping è l'annotazione usata per definire il Request URI per accedere
         * agli Endpoint REST
         * @RequestBody è l'annotazione usata per definire il contenuto del Request body
         */
        @RequestMapping(value = "/config", method = RequestMethod.POST)
        public JSONObject scriviConfig(@RequestBody String confStr) {
            try {
                // se è stato parsificato tutto correttamente
                // aggiorno le configurazioni e le rendo da subito operative
                JSONArray config = (JSONArray) new JSONParser().parse(confStr);
                configurazioni.setConfig(config);

                return answer(0, "ok");

            } catch (ParseException e) {
                //altrimenti risolvo qua la situazione se ci sono stati problemi nel parsing
                logger.error(e.toString());
                return answer(1, e.toString());
            } catch (IOException e) {
                //problemi di IO
                logger.error(e.getMessage());
                return answer(2, e.toString());
            }
        }

        @SuppressWarnings("unchecked")
        private JSONObject answer(int code, String info) {
            JSONObject answer = new JSONObject();
            answer.put("code", code);
            answer.put("info", info);
            return answer;
        }
    }

