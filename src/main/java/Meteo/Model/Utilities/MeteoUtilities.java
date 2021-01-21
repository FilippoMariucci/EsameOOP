package Meteo.Model.Utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

public class MeteoUtilities {

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

    private String temps;
    private Double tempPerc;
    private String st;
    private Integer N_spazioVariabilis;
    private Vector<Double> spazioVariabilis;
    private Double temp;
    private Double sp2;
    private Double accumulatore;



    public int Contatore (String spazioVariabili ) {this.N_spazioVariabilis = 0;
        this.accumulatore = 0.0;
        this.spazioVariabilis = new Vector<Double>();
        String b=spazioVariabili;
        String a="[\"it\"]";
        if(b.equals(a)){
            this.N_spazioVariabilis++;
            this.accumulatore ++;
            this.temps=spazioVariabili;
            spazioVariabili= this.st;
            sp2 = this.temp;
            this.spazioVariabilis.add(sp2);
        }
        return this.N_spazioVariabilis;
    }
}
