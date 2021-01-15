package Utilities;

import Model.SpazioVariabili;

public class Lingua {
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
                c1.inc();break;
            case "de":
                c2.inc();break;
            case "sp":
                c3.inc();break;
            case "fr":
                c4.inc();break;
            case "en":
                c5.inc();break;
            default:

        }
    }
}
