package Meteo.Model.Controller;

import Meteo.Model.Eccezioni.EccezioniStatistiche;
import Meteo.Model.MODEL.SpazioVariabili;
import Meteo.Model.Repository.MeteoRepository;
import Meteo.Model.Utilities.StatisticCalculator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@SuppressWarnings("unchecked")
public class RichiestaStatistiche extends Richiesta{
    private static final Logger logger= LoggerFactory.getLogger(RichiestaStatistiche.class);

    /**
     * costruttore con parametri
     * @Param meteoRepository è l'archivio dati su cui effettuare le ricerche
     * @Param filtro è un filtro di ricerca ricevuto nelle api
     */

    public RichiestaStatistiche(JSONObject filter, MeteoRepository meteoRepository){
        super(filter,meteoRepository);
    }

    /**
     * Effettuo l'overriding del metodo della superclasse
     * Ritorna la rispota in formato JSON all'utente
     */
    @Override
    public JSONObject getResult()throws EccezioniStatistiche{
        if (fisrtParseRequest()){
            JSONArray result= new JSONArray();
            this.answer.put("code",0);
            this.answer.put("info","");

            for (Object city :cities){
                String CityId=(String) city;
                logger.info(CityId);// giusto per controllare

                if (this.type.equals("all")){
                    for (String type:this.types){
                        result.add(calcolaStatistiche(CityId,type));
                    }
                }else {
                    result.add(calcolaStatistiche(CityId,this.type));
                }

            }
            this.answer.put("result",result);
        }else {
            this.answer=Generarisposta(3,"File not ok",0L);
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

    private JSONObject calcolaStatistiche(String CityId,String type)throws EccezioniStatistiche {
        List<SpazioVariabili> spazioVariabilis = meteoRepository.trovaValori(CityId,this.start,this.stop);
        JSONObject risultatiPerCityId=new JSONObject();
        risultatiPerCityId.put("CityId",CityId);

        risultatiPerCityId.put("type",type);

        StatisticCalculator statisticCalculator=new StatisticCalculator();
        for (SpazioVariabili spazioVariabili:spazioVariabilis){
            statisticCalculator.addSpazioVaribili(getValue(spazioVariabili,type));

        }





        JSONObject data =new JSONObject();
        data.put("Temperatura",statisticCalculator.getTemp());
        data.put("Temperatura massima",statisticCalculator.getMax());
        data.put("Temperatura minima",statisticCalculator.getMin());
        data.put("Media",statisticCalculator.getMedia());
        data.put("Varianza", statisticCalculator.getVarianza());
        risultatiPerCityId.put("data",data);
        return risultatiPerCityId;

        }

    }



