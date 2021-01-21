package Meteo.Model.Repository;


import Meteo.Model.MODEL.SpazioVariabili;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * Interfaccia che permette di gestire il nostro archivio dati.
 * @extends CrudRepository, interfaccia che fornisce funzionalità sofisticate come:
 * Create Read Update Delete per la classe (@Entity) creata appositamente
 * @Repository annotazione che indica che tale classe è un repository,
 * che astrae i metodi di accesso e archiviazione dei dati.
 *
 * @author Mariucci,Trombetta,D'Apote
 */
@Repository
public interface MeteoRepository extends CrudRepository<SpazioVariabili,Long> {

    /**
     * Query specifica che chiede al repository l'id della città nel periodo scelto dall'utente
     * @param CityId
     * @param start
     * @param stop
     * @return
     */

    @Query ( value = "SELECT * FROM Meteo WHERE CITY_ID = :CityId AND EPOCH >= :start AND EPOCH <= :stop", nativeQuery = true )
    List<SpazioVariabili> trovaValori ( @Param ( "CityId" ) String CityId,
                                        //@Param("CityName") String CityName,
                                        @Param ( "start" ) long start,
                                        @Param ( "stop" ) long stop );

}