package Meteo.Model.Controller;

import Meteo.Model.Eccezioni.EccezioniStatistiche;
import Meteo.Model.Eccezioni.TimeTravelerExeption;
import Meteo.Model.MODEL.SpazioVariabili;
import Meteo.Model.Repository.MeteoRepository;
import Meteo.Model.Utilities.MeteoUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

abstract class Richiesta extends MeteoUtilities {
    //vsjhddcs
    private static final Logger logger = LoggerFactory.getLogger(Richiesta.class);

    /**
     * database su cui effettuare le varie ricerche
     */

    protected MeteoRepository meteoRepository;
    /**
     * Filtro ricevuto dalla richiesta dell'api
     */
    protected JSONObject filter;

    /**
     * JSONObject utilizzato per costruire la nostra risposta
     */

    protected JSONObject answer;

    /**
     * tipologia di grandezza richiesta
     */
    protected String type;

    /**
     * JSONArray che conterrà tutte le città richieste
     */

    protected JSONArray cities;

    /**
     * JSONObject che conterrà il periodo di ricerca richiesto dall'utente
     */

    private JSONObject period;

    /**
     * istante di partenza per la ricerca (UNIX)
     */
    protected Long start;

    /**
     * istante di fine per la ricerca (UNIX)
     */

    protected Long stop;

    /**
     * array contenente  la possibile grandezza
     */
    protected String[] types = {"temperature","all"};

    /**
     * Costruttore con paremetri MeteoRepository, Filtro
     */

    public Richiesta(JSONObject filter, MeteoRepository meteoRepository) {
        this.meteoRepository = meteoRepository;
        this.filter = filter;
        this.answer = new JSONObject();
    }


    /**
     * Metodo astratto che si occupa di costruire la risposta. il metodo sarà svolto nella sua sottoclasse
     */

    public abstract JSONObject getResult() throws EccezioniStatistiche;

    /**
     * Metodo che si occupa di effettuare un primo parse dalla richesta
     */

    protected boolean fisrtParseRequest() {
        try {
            if ((this.cities = (JSONArray) this.filter.get("cities")) == null)
                return false;
            if ((this.period = (JSONObject) this.filter.get("period")) != null) {
                date2epoch();
            } else
                return false;

            if ((this.type = (String) this.filter.get("type")) == null)
                return false;

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }


    private void date2epoch() throws TimeTravelerExeption, InvalidParameterException {

        String from = (String) this.period.get("from");
        String to = (String) this.period.get("to");

        if ((from != null) && (to != null)) { // il parsing è riuscito

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.systemDefault());

            if ((Instant.from(fmt.parse(from)).toEpochMilli() / 1000) < (Instant.from(fmt.parse(to)).toEpochMilli()
                    / 1000)) {
                this.start = Instant.from(fmt.parse(from)).toEpochMilli() / 1000;
                this.stop = Instant.from(fmt.parse(to)).toEpochMilli() / 1000;

            } else {
                throw new TimeTravelerExeption("Intervallo non Valido");
            }

        } else {
            throw new InvalidParameterException("Parametri di ricerca non valide");
        }
    }

    protected Double getValue(SpazioVariabili spazioVariabili, String type) {
        switch (type) {
            case "Temperatura1":
                return spazioVariabili.getTemp();
            case "Temperatura2":
                return spazioVariabili.getTempPerc();

            default:
                return -999.0;

        }

    }

    }


