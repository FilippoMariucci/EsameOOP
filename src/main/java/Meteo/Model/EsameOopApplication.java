package Meteo.Model;

import Meteo.Model.Service.StaticConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication

@EnableScheduling
public class EsameOopApplication {


	@Autowired
	private Environment env;

	public static void main ( String[] args ) {
		SpringApplication.run(EsameOopApplication.class, args);
	}

	/**
	 * Metodo eseguito alla fine dell'inizializzazione dell'applicativo da
	 * parte del framework Spring e prima del suo avvio (grazie all'annotazione @PostConstruct)
	 * Legge dal file application.properties, usando l'interfaccia Environment, le
	 * configurazioni fisse (non modificabili a runtime)
	 */
	@PostConstruct
	public void init ( ) {
		StaticConfig.setApikey(env.getProperty("meteo.apikey"));
		StaticConfig.setOffset(Long.parseLong(env.getProperty("meteo.offset")));
		StaticConfig.setCallOpenWeather(Boolean.parseBoolean(env.getProperty("meteo.callopenweather")));

	}
}