package Meteo.Model.Utilities;

import Meteo.Model.Eccezioni.EccezioniStatistiche;


import java.util.Vector;

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

    public StatisticCalculator(){
        this.max=-Double.MAX_VALUE;
        this.min=Double.MAX_VALUE;
        this.N_spazioVariabilis=0;
        this.accumulatore=0.0;
        this.spazioVariabilis=new Vector<Double>();
    }


    /**
     * Metodo che serve per aggiungere i dati
     * Andrò ad eseguire diverse operazioni per andare ad assegnare a temp,max,min,N_spazioVariabilis,accumulatore
     * i corrispettivi valori corretti
     */

    public void addSpazioVaribili(Double spazioVariabili){
        this.N_spazioVariabilis++;
        this.accumulatore+=spazioVariabili;
        this.temp=spazioVariabili;
        spazioVariabili=this.sp2;
        sp2=this.temp;
        if (sp2> this.max)
            this.max=sp2;

        if (sp2 < this.min)
            this.min=sp2;

        this.spazioVariabilis.add(sp2);
    }




    public Double getMax()throws EccezioniStatistiche {
        if (max==-Double.MAX_VALUE)
            throw new EccezioniStatistiche("Campioni non trovati");

        else return max;
    }

    public Double getMin()throws EccezioniStatistiche{
        if (max==Double.MAX_VALUE)
            throw new EccezioniStatistiche("Campioni non trovati");
        else
            return min;
    }


    /**
     * Metodo che restituisce la media arrotondando le ultime due cifre dopo la virgola a soli due valori
     * @return Media
     * @throws EccezioniStatistiche
     */
    public Double getMedia() throws EccezioniStatistiche {
        if (max==-Double.MAX_VALUE)
            throw new EccezioniStatistiche("Campioni non trovati");

        else return Arrotonda(accumulatore/N_spazioVariabilis);
    }


    /**
     * Metodo che restituisce la varianza arrotondando le ultime due cifre dopo la virgola a soli due valori
     * @return Varianza
     * @throws EccezioniStatistiche
     */
    public Double getVarianza()throws EccezioniStatistiche{
        if (max==-Double.MAX_VALUE)
            throw new EccezioniStatistiche("Campioni non trovati");
        else {
            Double med=this.getMedia();
            Double temp=0.0;
            for (Double spazioVariabili:spazioVariabilis){
                temp+=Math.pow((spazioVariabili - med),2);
            }
            return Arrotonda(temp/this.N_spazioVariabilis);
        }



    }

    public Double getTemp() throws EccezioniStatistiche{

        return temp;
    }


    public Double getTempPerc() throws EccezioniStatistiche {

        return tempPerc;
    }
}
