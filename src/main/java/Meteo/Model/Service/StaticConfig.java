package Meteo.Model.Service;

public class StaticConfig {
    //uugfghj
    /**
     * apikey per il collegamento a OpenWeather
     */
    private static String apikey;
    /**
     * offset per gestire le code dell'interpolatore
     */
    private static Long offset;
    /**
     * flag booleano che attiva/disattiva la lettura schedulata da OpenWeather
     */
    private static Boolean callOpenWeather;

    /**
     * @return the apikey
     */
    public static String getApikey() {
        return apikey;
    }
    /**
     * @param apikey the apikey to set
     */
    public static void setApikey(String apikey) {
        StaticConfig.apikey = apikey;
    }
    /**
     * @return the offset
     */
    public static Long getOffset() {
        return offset;
    }
    /**
     * @param offset the offset to set
     */
    public static void setOffset(Long offset) {
        StaticConfig.offset = offset;
    }
    /**
     * @return the callOpenWeather
     */
    public static Boolean getCallOpenWeather() {
        return callOpenWeather;
    }
    /**
     * @param callOpenWeather the callOpenWeather to set
     */
    public static void setCallOpenWeather(Boolean callOpenWeather) {
        StaticConfig.callOpenWeather = callOpenWeather;
    }
}
