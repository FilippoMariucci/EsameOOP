package Repository;


import Model.SpazioVariabili;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeteoRepository extends CrudRepository<SpazioVariabili,Long> {
    @Query(value = "SELECT * FROM Meteo WHERE CITY_ID = :CityId AND EPOCH >= :start AND EPOCH <= :stop", nativeQuery = true)
    List<SpazioVariabili> trovaValori(@Param("CityId") String CityId,
                                      @Param("start") long start,
                                      @Param("stop") long stop);
}
