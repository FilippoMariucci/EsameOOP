package Meteo.Model.ApiUtente;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeanguagesApi {

    private static final Logger logger= LoggerFactory.getLogger(LeanguagesApi.class);

@RequestMapping(value = "/lang",method = RequestMethod.POST)
    public JSONObject scriviLingua(@RequestBody String leanStr){
    try {
        JSONArray leanConfig = (JSONArray) new JSONParser().parse(leanStr);
        configurazioni.setConfig(config);
    }
}



}
