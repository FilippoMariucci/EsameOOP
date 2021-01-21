# EsameOOP
#Progetto svolto da Dario Trombetta, Filippo Mariucci e Michele D'Apote.




****Traccia d'esame****

Implementare un servizio web per la visualizzazione delle condizioni meteo delle capitali d'europa più importanti. 
Per effettuare una ricerca parallela, occorre utilizzare gli ID delle Città. Il servizio deve dare la possibilità all'utente di modificare la lingua delle 
descrizioni del meteo, scegliendo una delle lingue a disposizione. 
Il servizio dovrà visualizzare e salvare le informazioni ogni ora. 
Infine dovrà generare delle statistiche, visualizzabili quando l'utente sceglierà una delle città dallo schermo.
Quindi occorre gestire anche le interazioni tra utenti ed il servizio. 
Il file contenente gli ID delle Città è scaricabile dal loro sito.
<STATS E FILTRI>: Statistiche periodiche riguardanti valori minimi, massimi, media e varianza di temperature delle varie città, sia reali sia percepite.
Statistiche sulle lingue scelte per la visualizzazione, da parte degli utenti.
Filtraggio in base alla periodicità (giornaliera, settimanale, mensile, range personalizzabile) e alle lingue maggiormente scelte.
 
 
 **** Diagrammi UML****
 
 Qui sotto sono riportati: Diagramma dei casi d'uso, Diagramma delle classi e Diagramma delle sequenze.
Questi tre diagrammi sono stati presi come spunto per la creazione del nostro progetto che, a seconda delle richieste, è stato modificato.
l'UML non è stato più modificato in quanto abbimo lavorato direttamente al progetto.  

![alt text](https://github.com/FilippoMariucci/EsameOOP/blob/master/Casi%20d'uso.jpg)
![alt text](https://github.com/FilippoMariucci/EsameOOP/blob/master/Modello%20Class%20Diagram.jpg)


****Funzionamento del programma****

Come prima cosa abbiamo scelto 5 lingue per le generazione delle statistiche, quali Italiano, Inglese, Tedesco, Francese e Spagnolo.
Innanzitutto bisogna avviare il nostro softwere scaricabile dalla nostra cartella github come zip, e lanciare la classe di bootstrap(EsameOOPApplication).
Una volta fatto ciò partirà SpringBoot e sarà in ascolto un server Tomcat, quindi a questo punto bisognerà lanciare postman e inserire l'indirizzo localhost:8080,
porta che abbiamo scelto per eseguire richieste lato client.
Tra le varie opzioni possiamo trovare quella di default, ovvero GET(serve per ottenere i valori di quella determinata API) e POST(crea il file JSON).
Quindi, nella barra laterale sulla sinistra si dovrà sostituire la voce GET con  Post facendo un semplice click sinistro.
Ok, siamo pronti ad inserire la nostra lingua. Inseriamo l'indirizzo localhost:8080/lang per inserire la lingua, e selezioniamo la voce Body.
Successivamente in basso si dovrà selezionare "raw".

N.B. nella Body di Postman bisognerà fare copia incolla della lingua desiderata, le trovi qui sotto!!!

["it"]  ---> per la lingua italiana

["en"]  ---> per la lingua inglese

["es"]  ---> per la lingua spagnola

["de"]  ---> per la lingua tedesca

["fr"]  ---> per la lingua francese

Una volta scelta la lingua e fatto il copia incolla bisognerà mandare il SEND, e qual'ora sia andato a buon fine nel terminale verrà restituito info:OK!

Una volta fatto ciò bisogna inserire gli ID delle città, le città di cui vogliamo vedere le statistiche meteo.
Cambiamo indirizzo e inseriamo localhost:8080/config, e nel Body(nello stesso modo indicato prima) inseriamo gli ID delle cittò desiderate(è possibile 
visualizzare anche le statistiche di più città contemporaneamente.
Ad ogni ID è associata una capitale d'Europa, seguono:

["5134295"] ----->Roma

["2643741"] ----->Londra

["2267057"] ---->Lisbona

["3054643"] ----->Budapest

["2988507"] ----->Parigi

["2761369"] ---->Vienna

["2950159"] ---->Berlino

["3117735"] ---->Madrid

["588409"] ----->Tallin

["264371"] ------>Atene

["3067696"] ----->Praga

["2964574"] ---->Dublino

["2618425"] ---->Copenaghen

["756135"] ----->Varsavia

["2759794"] ---->Amsterdam

["792680"] ----->Belgrado

Gli Id sopra riportati corrispondono alle 15 principali capitali d'Europa(come richiesto negli obbiettivi). Nel caso in cui si voglia visualizzare il meteo con 
rispettive statistiche di altre città basterà andare nel sito di OpenWeather(https://openweathermap.org/current#severalid) e scaricare il file contenente tutti gli Id's delle città.

Esempio per scegliere due o più città contemporaneamente.
Nel body bisognerà inserire gli ID attraverso la seguente dicitura:

["IDCITTà1",

 "IDCittà2",
 
 ...
 
 "IDCittàN"
 ]
 
Per generare infine le nostre statistiche nella lingua scelta, delle città scelte, bisognerà fare una SEND all'indirizzo localhost:8080/stats, nel quale si potrà
scegliere il periodo desiderato delle nostre statistiche, modificabile in base alla propria preferenza(es. giornaliero, settimanale,mensile, orario ecc...).
Si potrà inoltre scegliere se ricevere le statistiche della temperatura reale inserendo in Type "Temperatura1", altrimenti si potranno ottenre le statistiche della
temperatura percepita inserendo in type "Temperatura2".
Ecco cosa dovrà contenere il body nella stats:

{"cities":

["3180582",  //ci dovranno essere i CityID da noi scelti in precedenza

"2643741",

"5134295",

"2950159",

"3117735"],

"period": {

"from": "2021-01-19 00:00:00",  //yyyy-MM-dd HH:mm:ss

//esmpio di periodo delle temperature generate(filtri) rappresentati in anno, mese, giorno - ore,minuti,secondi

"to": "2021-02-20 24:00:00"

},

"type": "Temperatura1"

}

Una volta mandato il Send le nostre statistiche verranno generate.
Per visualizzare le statistiche che vengono salvate ogni ora, bisognerà accedere attraverso un browser all'indirizzo localhost:8080/h2 e accedere al database.
Scaricando il codice completo avrete un file nel quale è presente il nostro database, nel quale sono state inserite diverse temperature di diverse città in
periodi differenti.



****Diario di bordo****

Abbiamo iniziato a lavorare al progetto subito dopo la fine delle lezioni, quindi intrno alla metà di Dicembre.
Per quanto riguarda la parte progettuale, quindi i vari diagrammi UML, tutti noi 3 membri del gruppo avevamo delle buone cnoscenze di base riguardo tale argomento,
l'alunno Trombetta avendo già lavorato con tali diagrammi durante il triennio delle superiori si è occupato principalmente della modellazione dei 3 diagrammi per la 
strutturazione del progetto, ovviamente dialogando con gli altri 2 alunni per abbozzare concettualmente il progetto e per renderci conto in maniera concreta di
ciò che saremmo andati a programmare.
Siamo passati poi alla programmazione del progetto vero e proprio, e abbiamo deciso grazie al consiglio di Mariucci di cambiare il nostro IDE, lavorando tutti e 3 con
IntelliJ, che abbiamo trovato molto più comodo e automatizzato rispetto Eclipse, anche grazie al suo layout molto più gradevole.
L'alunno Mariucci per quanto riguarda la parte di programmazione è risultato essere molto più preparato rispetto a Trombetta e D'Apote, sopratutto nell'utilizzo
di SpringBoot e Postman, argomento inizialmente poco chiaro agli alunni.
Grazie all'aiuto di Mariucci, alle conferenze di Teams in gruppo,alle linee di codice scritte insieme, e sporattutto alla voglia di fare, tali lacune sono state colmate.
A parte leggeri ritardi, una volta definito un oario di incontro su un apposito gruppo WhatsApp, il team è stato quasi sempre puntuale, lavorando circa almeno 2-3 
ore al giorno.
Inoltre gli alunni hanno continuato a lavorare anche in maniera autonoma, magari per cercare di risolvere dei problemi riscontrati nello sviluppo del programma, o magari colmando argomenti poco chiari tramite le apposite diapositive messe a disposizione dal prof. Frontoni, Paolanti e Martini, o magari anche con spiegazioni trovate in giro per il web.
Abbiamo anche svolto due ricevimenti, uno con il prof Frontoni, e uno con Martini, per alcuni chiarimenti riguardo la traccia d'esame assegnata.
