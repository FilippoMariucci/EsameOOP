package EsameOOPTest.test;


import static org.junit.jupiter.api.Assertions.*;

import Meteo.Model.Eccezioni.EccezioniStatistiche;
import Meteo.Model.Utilities.StatisticCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import it.abmlb.mmw.exceptions.MmwStatsException;
//import it.abmlb.mmw.utilities.*;

public class StatisticCalculatorTest {

    private StatisticCalculator s0, s1;


    @BeforeEach
    void setUp() {
        s0 = new StatisticCalculator();

        s1 = new StatisticCalculator();
        s1.addSpazioVaribili(2.0);
        s1.addSpazioVaribili(10.5);
        s1.addSpazioVaribili(5.0);

    }


    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test in assenza di campioni")
    void test0() {
        try {
            assertEquals(0.0 , s0.getMax());
            assertEquals(0.0 , s0.getMin());
            assertEquals(0.0 , s0.getVarianza());
            assertEquals(0.0 , s0.getVarianza());
        } catch (EccezioniStatistiche e) {

            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test per controllare tutti i vari metodi per calcolare masssimo,minimo,media e varianza")
    void test1() {
        try {
            assertEquals(12.0, s1.getMax());
            assertEquals(4.0, s1.getMin());
            assertEquals(10.66, s1.getVarianza());
            assertEquals(8.0, s1.getVarianza());
        } catch (EccezioniStatistiche e) {

            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Test StatCalculator exception")
    public void testStatExc(){
        EccezioniStatistiche ex =  Assertions.assertThrows(EccezioniStatistiche.class, () -> {
            s0.getMax();
        });

        assertEquals(ex.getMessage(), "Assenza di campioni");
    }
}
