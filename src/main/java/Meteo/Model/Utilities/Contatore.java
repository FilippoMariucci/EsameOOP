package Meteo.Model.Utilities;

import Meteo.Model.Repository.MeteoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


public class Contatore {
    private Integer it;
    private Integer sp;
    private Integer fr;
    private Integer de;
    private Integer en;


    public Contatore(){
        it=0;
        sp=0;
        fr=0;
        de=0;
        en=0;
    }

    public Integer getIt ( ) {
        return it;
    }

    public Integer getSp ( ) {
        return sp;
    }

    public Integer getFr ( ) {
        return fr;
    }

    public Integer getDe ( ) {
        return de;
    }

    public Integer getEn ( ) {
        return en;
    }

    public Integer incIT ( ) {
        this.it++;
        return it;
    }
    public Integer incES ( ) {
        this.sp++;
        return sp;
    }
    public Integer incDE ( ) {
        this.de++;
        return de;
    }
    public Integer incFR ( ) {
        this.fr++;
        return fr;
    }
    public Integer incEN ( ) {
        this.en++;
        return en;
    }
}
