package Meteo.Model.Utilities;

import Meteo.Model.Eccezioni.EccezioniStatistiche;


import java.util.Vector;


/**
 *
 * Classe che implementa i metodi utilizzati per il calcolo delle Statistiche
 * @extends MeteoUtilities
 *
 * @author Mariucci,Trombetta,D'Apote
 */
public class StatisticCalculator extends MeteoUtilities {

    /**
     * Valore massimo assunto dalla grandezza nell'intervallo preso in considerazione
     */
    private Double max;
    /**
     * Valore minimo assunto dalla grandezza nell'intervallo preso in considerazione
     */
    private Double min;
    /**
     * temperatura reale della città selezionata
     */
    private Double temp;


    private Double tempPerc;
    /**
     * variabile di passaggio per poter inserire la temperatura reale
     */
    private Double sp2;

    /**
     * Contatore della quantità di campioni utilizzati
     */
    private Integer N_spazioVariabilis;
    /**
     * Accumulatore dei valori dei campioni utilizzati
     */
    private Double accumulatore;
    /**
     * Vettore contenente i campioni utilizzati
     */
    private Vector<Double> spazioVariabilis;

    /**
     * Costruttore di default
     */

    public StatisticCalculator ( ) {
        this.max = -Double.MAX_VALUE;
        this.min = Double.MAX_VALUE;
        this.N_spazioVariabilis = 0;
        this.accumulatore = 0.0;
        this.spazioVariabilis = new Vector<Double>();
    }


    /**
     * Metodo che serve per aggiungere i dati
     * Andrò ad eseguire diverse operazioni per andare ad assegnare a temp,max,min,N_spazioVariabilis,accumulatore
     * i corrispettivi valori corretti
     */

    public void addSpazioVaribili ( Double spazioVariabili ) {
        this.N_spazioVariabilis++;
        this.accumulatore += spazioVariabili;
        this.temp = spazioVariabili;
        spazioVariabili = this.sp2;
        sp2 = this.temp;
        if (sp2 > this.max)
            this.max = sp2;

        if (sp2 < this.min)
            this.min = sp2;

        this.spazioVariabilis.add(sp2);
    }


    /**
     * @return Max
     * @throws EccezioniStatistiche
     */
    public Double getMax ( ) throws EccezioniStatistiche {
        if (max == -Double.MAX_VALUE)
            throw new EccezioniStatistiche("Campioni non trovati");

        else return max;
    }

    /**
     * @return Min
     * @throws EccezioniStatistiche
     */

    public Double getMin ( ) throws EccezioniStatistiche {
        if (max == Double.MAX_VALUE)
            throw new EccezioniStatistiche("Campioni non trovati");
        else
            return min;
    }


    /**
     * Metodo che restituisce la media arrotondando le ultime due cifre dopo la virgola a soli due valori
     *
     * @return Media
     * @throws EccezioniStatistiche
     */
    public Double getMedia ( ) throws EccezioniStatistiche {
        if (max == -Double.MAX_VALUE)
            throw new EccezioniStatistiche("Campioni non trovati");

        else return Arrotonda(accumulatore / N_spazioVariabilis);
    }


    /**
     * Metodo che restituisce la varianza arrotondando le ultime due cifre dopo la virgola a soli due valori
     *
     * @return Varianza
     * @throws EccezioniStatistiche
     */
    public Double getVarianza ( ) throws EccezioniStatistiche {
        if (max == -Double.MAX_VALUE)
            throw new EccezioniStatistiche("Campioni non trovati");
        else {
            Double med = this.getMedia();
            Double temp = 0.0;
            for (Double spazioVariabili : spazioVariabilis) {
                temp += Math.pow((spazioVariabili - med), 2);
            }
            return Arrotonda(temp / this.N_spazioVariabilis);
        }


    }

    /**
     * Metodo che restituisce il massimo tra 5 valori inseriti all'interno dell'array
     * @param c1
     * @param c2
     * @param c3
     * @param c4
     * @param c5
     * @return Massimo
     */

    public int Massimo(int c1, int c2,int c3,int c4,int c5){
        int x[]={c1,c2,c3,c4,c5};
        int massimo;
        massimo=x[0];
        for (int i=0;i<x.length-1;i++){
            if (x[i]>massimo)
                massimo=x[i];
        }
        return massimo;
    }

    /**
     * Metodo che restituisce la temperatura reale
     *
     * @return Temperatura reale
     * @throws EccezioniStatistiche
     */
    public Double getTemp ( ) throws EccezioniStatistiche {

        return temp;
    }

    /**
     * Metodo che restituisce la temperatura percepita
     *
     * @return Temperatura percepita
     * @throws EccezioniStatistiche
     */

    public Double getTempPerc ( ) throws EccezioniStatistiche {

        return tempPerc;
    }

}
