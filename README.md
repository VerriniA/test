Creazione di una web application per la lettura del saldo e l'invio di un bonifico utilizzando le API esposte da Platfr.io

Materiale condiviso

1. Web application java + Tomcat 7.0 + Eclipse
  - progetto "testAlbertoVerrini"
  
2. Modellazione UML di uno use case e di un class diagram dei soggetti coinvolti
  - nella cartella "testAlbertoVerrini/docs/UML"
  
3. Test di approccio alle API esposte da Platfr.io: una batteria di test realizzati con postamn, delle chiamate AccountBalance e CreateSCTOrder
  - nella cartella "testAlbertoVerrini/src-test/postman"
  
4. Unit test delle chiamate alle API esposte da Platfr.io tramite la classe CustomHttpClient.java
  - nella cartella "testAlbertoVerrini/src-test/"

5. Qualche screenshot delle varie schermate della webapp
  - nella cartella "testAlbertoVerrini/docs/screenshots"
  
APPROCCIO
1. Ho eseguito una serie di test con postman per prendere confidenza con le API e visualizzare sia i mock di risposta OK che KO

2. Ho preparato un paio di diagrammi UML con un test case e un class diagram

3- Ho realizzato una piccola webapp (java+tomcat7) con una pagina HTML di presentazione che consente la scelta di una tra due azioni:
  - Chiedi estrattpo conto
  - Esegui bonifico
  
    Le pagine associate permettono di 
      - inviare la richiesta e ricevere il saldo
      - compilare un form, inviare la richiesta e ricevere conferma dell'operazioneù
      
    Le relative servlet gestisono la creazione di:
      - una GET tramite un httpClient dell'API rest AccountBalance
      - una POST tramite un httpClient sull'API rest CreateSCTOrder
  
    Le risposte alla chiamate dell'httpClient vengono gentite dalle servlet in modo opportuno così da restituire 
    una pagina HTML con la presentazione della risposta all'utente

4. Ho preparato una serie di unittest con Junit5 per verificare che le risposte al ClientHttp java fossero coerenti con i miei test fatti con postman

