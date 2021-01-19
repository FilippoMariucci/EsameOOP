package Meteo.Model.Utilities;

public class Contatore {

    private int contIt=0;
    private int contEn=0;
    private int contEs=0;
    private int contDe=0;
    private int contFr=0;


    public int getContIt(){
        return contIt;
    }

    public int getContEn(){
        return contEn;
    }

    public int getContDe(){
        return contDe;
    }

    public int getContEs(){
        return contEs;
    }

    public int getConIt(){
        return contIt;
    }

    public int getContFr(){
        return contFr;
    }

    public int incIta() {
        return contIt+=1;
    }

    public int incFr() {
        return contFr+=1;
    }

    public int incEn() {
        return contEn+=1;
    }

    public int incEs() {
        return contEs+=1;
    }

    public int incDe() {
        return contDe+=1;
    }



}
