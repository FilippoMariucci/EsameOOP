package Meteo.Model.ApiUtente;

import Meteo.Model.Controller.LeanguagesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LeanguagesApi {

    private static final Logger logger= LoggerFactory.getLogger(LeanguagesApi.class);

    @Autowired
    LeanguagesConfiguration leanguagesConfiguration;


@RequestMapping(value = "/lang",method = RequestMethod.POST)
    public JSONObject scriviLingua(@RequestBody String leanStr){
    try {
        JSONArray leanConfig = (JSONArray) new JSONParser().parse(leanStr);
        leanguagesConfiguration.setLeanConfig(leanConfig);
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

