package Meteo.Model.Utilities;

import Meteo.Model.MODEL.SpazioVariabili;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;

public class Lingua {

    private static  final Logger logger= LoggerFactory.getLogger(Lingua.class);


    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String text5;
    private String text6;

    private  String leanguages;

    public Lingua(String lingua) {
        this.leanguages=lingua;
    }


    public void contatoreLingua(){


        SpazioVariabili sp1 = new SpazioVariabili();
        this.leanguages=sp1.getLang();
        Contatore c1=new Contatore();
        Contatore c2=new Contatore();
        Contatore c3=new Contatore();
        Contatore c4=new Contatore();
        Contatore c5=new Contatore();
        switch (this.leanguages){
            case "it":
                this.text1="Dati";
                this.text2="Tempratura";
                this.text3="Temperatura minima";
                this.text4="Temperatura massima";
                this.text5="Media delle temperature";
                this.text5="Varianza tra le temperature";
                c1.inc();break;
            case "de":
                this.text1="Daten";
                this.text2="Temperatur";
                this.text3="Mindesttemperatur";
                this.text4="Maximale temperatur";
                this.text5="Durchschnittstemperaturen";
                this.text5="Varianz zwischen den temperaturen";
                c2.inc();break;
            case "sp":
                this.text1="Datos";
                this.text2="Temperatura";
                this.text3="Temperatura mìnima";
                this.text4="Temperatura màxima";
                this.text5="Temperaturas medias";
                this.text5="Variaciòn entre temperaturas";
                c3.inc();break;
            case "fr":
                this.text1="Les donnèes";
                this.text2="Température";
                this.text3="Température minimale";
                this.text4="Température maximale";
                this.text5="Températures moyennes";
                this.text5="Variance entre les températures";
                c4.inc();break;
            case "en":
                this.text1="Data";
                this.text2="Temperature";
                this.text3="Minimum temperature";
                this.text4="Maximum temperature";
                this.text5="Avarege temperatures";
                this.text5="Variance between temperatures";
                c5.inc();break;
            default:
                logger.error("Lingua non disponibile");

        }
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }

    public String getText4() {
        return text4;
    }

    public String getText5() {
        return text5;
    }

    public String getText6() {
        return text6;
    }
}
