package EsameOOPTest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import Meteo.Model.Controller.Configurations;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class ConfigurationTest {
    private Configurations configurations;
    private JSONArray conf;

    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setUp() {
        configurations = new Configurations();
        conf = new JSONArray();
        conf.add("5134295"); //Roma
        conf.add("3117735");//Madrid
        conf.add("2950159");//Berlino
        conf.add("2988507");//Parigi
        conf.add("2643741");//Londra
        try {
            configurations.setConfig(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {}

    @Test
    @DisplayName("Test risposta di configurazioni dinamiche")
    public void test() {
        assertEquals(conf, configurations.getConfig());
    }
}
