package Meteo.Model.Controller;

import Meteo.Model.Utilities.Contatore;
import Meteo.Model.Eccezioni.EccezioniStatistiche;
import Meteo.Model.MODEL.SpazioVariabili;
import Meteo.Model.Repository.MeteoRepository;
import java.lang.System.*;
import Meteo.Model.Utilities.StatisticCalculator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@SuppressWarnings("unchecked")
public class RichiestaStatistiche extends Richiesta {
    private static final Logger logger = LoggerFactory.getLogger(RichiestaStatistiche.class);

    /**
     * costruttore con parametri
     *
     * @Param meteoRepository è l'archivio dati su cui effettuare le ricerche
     * @Param filtro è un filtro di ricerca ricevuto nelle api
     */

    public RichiestaStatistiche(JSONObject filter, MeteoRepository meteoRepository) {
        super(filter, meteoRepository);
    }

    /**
     * Effettuo l'overriding del metodo della superclasse
     * Ritorna la rispota in formato JSON all'utente
     */
    @Override
    public JSONObject getResult() throws EccezioniStatistiche, IOException {
        if (fisrtParseRequest()) {
            JSONArray result = new JSONArray();
            this.answer.put("code", 0);
            this.answer.put("info", "");

            for (Object city : cities) {
                String CityId = (String) city;
                logger.info(CityId);// giusto per controllare

                if (this.type.equals("all")) {
                    for (String type : this.types) {
                        result.add(calcolaStatistiche(CityId, type));
                    }
                } else {
                    result.add(calcolaStatistiche(CityId, this.type));
                }

            }
            this.answer.put("result", result);
        } else {
            this.answer = Generarisposta(3, "File not ok", 0L);
        }
        return this.answer;
    }


    /**
     * Metodo che si occupa di costruire la risposta in formato JSON
     * da ritornare al chiamante
     * Effettua la query al repository
     * Istanzia un oggetto di StatisticCalculator performando i calcoli richiesti
     * sui dati estratti
     */

    private JSONObject calcolaStatistiche(String CityId, String type) throws EccezioniStatistiche, IOException {

        Contatore Ita = new Contatore();
        Contatore Esp = new Contatore();
        Contatore Deu = new Contatore();
        Contatore Fra = new Contatore();
        Contatore Eng = new Contatore();

        List<SpazioVariabili> spazioVariabilis = meteoRepository.trovaValori(CityId, this.start, this.stop);
        JSONObject risultatiPerCityId = new JSONObject();
        risultatiPerCityId.put("CityId", CityId);

        risultatiPerCityId.put("type", type);

        StatisticCalculator statisticCalculator = new StatisticCalculator();
        for (SpazioVariabili spazioVariabili : spazioVariabilis) {
            statisticCalculator.addSpazioVaribili(getValue(spazioVariabili, type));

        }
        /**
         * È possibile leggere da file una linea per volta, invece di un carattere per volta.
         * Questo permette anche la lettura di interi, reali, ecc.
         *Per poter leggere una linea per volta, è necessario creare un oggetto BufferedReader a partire dal FileReader.
         * In altre parole, si crea prima un FileReader, poi usando questo si crea un BufferedReader.
         * Quest'ultimo si può quindi usare per la lettura riga per riga
         */

        FileReader f;
        f = new FileReader("leanConfig.json");
        BufferedReader b;
        b = new BufferedReader(f);
        /**
         * A questo punto si può leggere una riga per volta usando b.readLine().
         */
        do {
            String s;
            s = b.readLine();


            switch (s) {
                case "[\"it\"]":
                    Ita.incIta();
                    JSONObject data1 = new JSONObject();
                    data1.put("Temperatura", statisticCalculator.getTemp());
                    data1.put("Temperatura minima", statisticCalculator.getMin());
                    data1.put("Temperatura massima", statisticCalculator.getMax());
                    data1.put("Media", statisticCalculator.getMedia());
                    data1.put("Varianza", statisticCalculator.getVarianza());
                    data1.put("La lingua italiana e' stata scelta ", Ita.getConIt());
                    risultatiPerCityId.put("Dati", data1);


                    break;

                case "[\"de\"]":
                    Deu.incDe();
                    JSONObject data2 = new JSONObject();
                    data2.put("Temperatur", statisticCalculator.getTemp());
                    data2.put("Mindesttemperatur", statisticCalculator.getMin());
                    data2.put("Maximale temperatur", statisticCalculator.getMax());
                    data2.put("Durchschnittstemperaturen", statisticCalculator.getMedia());
                    data2.put("Varianz zwischen den temperaturen", statisticCalculator.getVarianza());
                    data2.put("La lingua tedesca e' stata scelta ", Deu.getContDe());
                    risultatiPerCityId.put("Daten", data2);
                    break;
                case "[\"en\"]":
                    Eng.incEn();
                    JSONObject data3 = new JSONObject();
                    data3.put("Temperature", statisticCalculator.getTemp());
                    data3.put("Minimum temperature", statisticCalculator.getMin());
                    data3.put("Maximum temperature", statisticCalculator.getMax());
                    data3.put("Avarege temperatures", statisticCalculator.getMedia());
                    data3.put("Variance between temperatures", statisticCalculator.getVarianza());
                    data3.put("La lingua inglese e' stata scelta ", Eng.getContEn());
                    risultatiPerCityId.put("Data", data3);
                    break;
                case "[\"fr\"]":
                    Fra.incFr();
                    JSONObject data4 = new JSONObject();
                    data4.put("Température", statisticCalculator.getTemp());
                    data4.put("Température minimale", statisticCalculator.getMin());
                    data4.put("Température maximale", statisticCalculator.getMax());
                    data4.put("Températures moyennes", statisticCalculator.getMedia());
                    data4.put("Variance entre les températures", statisticCalculator.getVarianza());
                    data4.put("La lingua francese e' stata scelta ", Fra.getContFr());
                    risultatiPerCityId.put("Les donnèes", data4);
                    break;
                case "[\"es\"]":
                    Esp.incEs();
                    JSONObject data5 = new JSONObject();
                    data5.put("Temperatura", statisticCalculator.getTemp());
                    data5.put("Temperatura mìnima", statisticCalculator.getMin());
                    data5.put("Temperatura màxima", statisticCalculator.getMax());
                    data5.put("Temperaturas medias", statisticCalculator.getMedia());
                    data5.put("Variaciòn entre temperaturas", statisticCalculator.getVarianza());
                    data5.put("La lingua italiana e' stata scelta ", Ita.getContEs());
                    risultatiPerCityId.put("Datos", data5);
                    break;

                default:
                    logger.error("Lingua non disponibile");

            }
        } while (!(Ita==null) && !(Eng==null) && !(Esp==null) && !(Fra==null) && !(Deu==null));

        return risultatiPerCityId;
    }
}



