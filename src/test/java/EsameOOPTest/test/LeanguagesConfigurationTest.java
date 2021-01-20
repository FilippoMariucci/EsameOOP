package EsameOOPTest.test;

import Meteo.Model.Controller.Configurations;
import Meteo.Model.Controller.LeanguagesConfiguration;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeanguagesConfigurationTest {
    private LeanguagesConfiguration leanConfigurations;
    private JSONArray conf;

    @SuppressWarnings("unchecked")
    @BeforeEach
    public void setUp() {
        leanConfigurations= new LeanguagesConfiguration();
        conf = new JSONArray();
        conf.add("it");
        conf.add("en");
        conf.add("fr");
        conf.add("de");
        conf.add("es");
        try {
            leanConfigurations.setLeanConfig(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {}

    @Test
    @DisplayName ("Test risposta di configurazioni dinamiche")
    public void test() {
        assertEquals(conf, leanConfigurations.getLeanConfig());
    }
}
