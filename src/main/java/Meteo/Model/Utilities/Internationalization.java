/*
Per prima cosa occorrerà definire il file di lingua di default nell’application.properties

# INTERNATIONALIZATION (MessageSourceAutoConfiguration)
spring.messages.basename=file_lingua
spring.messages.cache-seconds=-1
spring.messages.encoding=UTF-8 */

//Il file_lingua.properties in italiano verrà tradotto rispettivamente in file_lingua_en.properties,
// file_lingua_te.properties, file_lingua_es.properties e file_lingua_fr.properties.
//Questi file verranno inseriti nella stessa directory del file di lingua di default.
//Arrivati a questo punto definiamo un’Interceptor per il cambio della lingua.
//Per questa funzionalità utilizzeremo l’oggetto ResourceBundleMessageSource.
//Questo codice può essere inserito in qualsiasi classe di configurazione del progetto
// Spring Boot purchè annotata con @Configuration.

/*
@Configuration
public class InterceptorLanguage{

     * Setting del Resolver per la lingua di default.
     * @return LocaleResolver

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ITALIAN);
        return slr;
    }


     * Intercettatore del file per il cambio della lingua.

   *@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


     * Gestione delle url ?lang=it oppure ?lang=es/...

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
}*/

//l responsabile del cambio della lingua è il LocaleChangeInterceptor che a seconda del parametro lang attiva o meno
// un’altra lingua.
//Ad esempio chiamando l’url /generic_url/?lang=es e disponendo del file tell_me_es.properties (definito in precedenza).
//Successivamente al cambio della lingua si sarà rediretti presso l’url sulla quale ci trovavamo prima della richiesta
// del cambio di lingua.