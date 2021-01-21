package Meteo.Model.Controller;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;


/**
 *
 * Classe che, richiamata da LeanguagesApi, permette di leggere o modificare le configurazioni
 * dinamiche dell'applicativo
 * @Component annotazione che definisce la classe come componente autogestito da Spring
 *
 * @author Mariucci,Trombetta,D'Apote
 */
@Component
public class LeanguagesConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(LeanguagesConfiguration.class);

    private final String filePath = "leanConfig.json";
    private JSONArray leanConfig;

    public LeanguagesConfiguration() {
        super();
        this.leanConfig = new JSONArray();


                }

    /**
     * Metodo che si occupa di ritornare le configurazioni al chiamante,
     * attuando diversi controlli sulla presenza di configurazioni in memoria e su file
     * Se non trova configurazioni in memoria o su file, crea delle configurazioni di default
     * @return the LeanConfig
     */

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
                JSONArray stdLeanConfig = new JSONArray();
                stdLeanConfig.add("it");

                try {
                    this.setLeanConfig(stdLeanConfig);
                } catch (IOException e) {
                    //problemi di IO
                    logger.error(e.getMessage());
                }
            }
        }
        return this.leanConfig;
            }
    /**
     * Metodo che si occupa di settare le nuove configurazioni dinamiche nel relativo attributo
     * e di riportare gli stessi cambiamenti anche su file di testo in formato JSON in modo
     * da poterli recuperare ad un nuovo avvio dell'applicativo
     * @param leanConfig the LeanConfig to set
     * @throws IOException
     */
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