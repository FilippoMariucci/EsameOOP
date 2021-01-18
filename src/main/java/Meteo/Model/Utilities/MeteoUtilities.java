package Meteo.Model.Utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MeteoUtilities {
    //jhgfd
    /**
     * Metodo per effettuare arrotondamenti a due cifre decimali per i tipi double
     * @param valore Valore da arrotondare
     * @return the rounded value
     */
    protected double Arrotonda (double valore) {
        int arrotondamento = (int) (valore*100.0);
        return (double)arrotondamento/100;
    }

    /**
     * metedo che trasforma il risultato di timeStamp espressi in Unix in stringhe, formattando data e ora
     */

    protected String toDateSTR(long millisecondi){
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar calendario =Calendar.getInstance();
        calendario.setTimeInMillis(millisecondi);
        return  dateFormat.format(calendario.getTime());

    }

    /**
     * Metodo che permette di costruire un JSONObject di risposta di default
     */

    @SuppressWarnings("unchecked")
    protected JSONObject Generarisposta(int code, String info, long time){
        JSONObject risposta=new JSONObject();
        risposta.put("code",code);
        risposta.put("info",info);
        risposta.put("time",time);
        risposta.put("Risultato",new JSONArray());
        return risposta;
    }
}
