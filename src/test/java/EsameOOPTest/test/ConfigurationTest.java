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
        conf.add("Roma");
        conf.add("Madrid");
        conf.add("Berlino");
        conf.add("Parigi");
        conf.add("Londra");
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
