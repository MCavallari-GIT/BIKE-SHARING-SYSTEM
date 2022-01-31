# BIKE-SHARING-SYSTEM
L’obiettivo è quello di realizzare un’applicazione per bike sharing.

Andiamo a vedere chi compone il nostro sistema: Ovviamente avremo le biciclette che possono distinguersi in tre categorie: elettrice, elettriche con seggiolino e normali. Esse sono posizionate dal personale (della quale tratteremo più avanti) nelle rastrelliere. Ogni stazione è costituita da una rastrelliera (composta da morse (di tre tipi come le biciclette). Ad’ ogni stazione è presente un totem con la quale gli utenti possono interagire per prelevare/depositare una bicicletta. Riassumendo quindi, nel mio progetto il concetto di stazione è rappresentato come l’unione di una rastrelliera composta da N morse e un totem associato ad essi. Gli utenti sono dotati di una applicazione mobile dalla quale è possibile registrarsi e ottenere un abbonamento che può essere di tre tipi: giornaliero, settimanale o annuale. Una volta ottenuto un abbonamento (l’utente inserisce i propri dati personali, una password, tipo di abb. da acquistare e i dati della sua carta di credito e riceve un codice utente da usare per i futuri login), un utente può prelevare una bicicletta (fino alla scadenza del suo abbonamento). Se un utente restituisce una bicicletta dopo 2h dal ritiro viene aggiunta un’ammonizione al suo abbonamento. Alla terza ammonizione l’abbonamento viene annullato. Inoltre non è possibile ritirare una nuova bicicletta per 5 minuti dopo l’ultimo prelievo. Se un utente restituisce una bicicletta dopo 24h viene addebitata una penale di 150€ più i normali costi di utilizzo sulla sua carta. Gli abbonamenti sono associati ad una carta di credito dalla quale vengo scalati i soldi per l’attivazione dell’abbonamento e dopo ogni prelievo seguendo un opportuno listino prezzi basato sul tipo di bicicletta e sul minutaggio di utilizzo. Un prelievo viene creato quando un utente ritira una bicicletta e viene chiuso quando esso la deposita. Mantenere la traccia dei prelievi e della loro durata consente di calcolare la spesa da attribuire all’utente per l’utilizzo della bicicletta. I totem sono i punti chiave con la quale un utente interagisce per la “gestione delle biciclette”. L’utente per il prelievo/deposito inserirà le credenziali nel totem. Nel caso del ritiro dovrà poi anche indicare quale tipo di bicicletta esso vuole prelevare e se è presente essa verrà tolta dalla rastrelliera associata al totem in questione. Il personale ha accesso ad un terminale, dal quale possono visualizzare la lista di stazioni e di bicicletta presenti nel db. Possono aggiungere, togliere o spostare biciclette, aggiungere o togliere intere stazioni dal sistema

Il personale ha anche accesso a dei dati statistici sull’utilizzo delle biciclette e altro ancora.
