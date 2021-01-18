package Meteo.Model.Controller;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class LeanguagesConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(LeanguagesConfiguration.class);

    private final String filePath = "leanConfig.json";
    private JSONArray leanConfig;

    public LeanguagesConfiguration() {
        super();
        this.leanConfig = new JSONArray();


                }

    @SuppressWarnings("unchecked")

    public JSONArray getLeanConfig () {
        if (this.leanConfig.size() == 0) {
            File l = new File(this.filePath);
            if (l.isFile()) { // se è un file, provo a leggerlo

                try {
                    BufferedReader br = new BufferedReader(new FileReader(this.filePath));
                    this.leanConfig = (JSONArray) new JSONParser().parse(br);
                    br.close();
                } catch (FileNotFoundException e) {

                    logger.error(e.getMessage());
                } catch (IOException e) {

                    logger.error(e.getMessage());
                } catch (ParseException e) {

                    logger.error(e.toString());
                }
            } else {
                JSONArray stdConfig = new JSONArray();
                stdConfig.add("it");

                try {
                    this.setLeanConfig(stdConfig);
                } catch (IOException e) {
                    //problemi di IO
                    logger.error(e.getMessage());
                }
            }
        }
        return this.leanConfig;
            }

            public void setLeanConfig(JSONArray leanConfig) throws IOException{
        this.leanConfig=leanConfig;

                try {
                    FileWriter file = new FileWriter(this.filePath); // il file viene aperto in modalità sovrascrittura
                    BufferedWriter bw = new BufferedWriter(file);
                    bw.write(leanConfig.toJSONString());
                    bw.close();
                } catch (IOException e) {

                    //scrivo sul log se ci sono stati problemi con file su disco
                    logger.error(e.getMessage());
                    throw e;
        }
    }
}