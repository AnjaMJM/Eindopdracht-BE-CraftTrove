# To do voor eindopdracht

### Technisch ontwerp
* Inleiding
* Inhoudsopgave
* ~~Algemene omschrijving web-API~~
* ~~4 User stories~~
* ~~25 Functionele en niet-functionele eisen~~
* Klassendiagram
* 2 sequentiediagrammen

### Verantwoordingsdocument
* Inleiding
* Verantwoording 5 technische keuzes (bijvoorbeeld over OOP-structuren,
  design patterns, Web-API of architectuur) + reflectie (welke alternatieven zijn er?)
  * getter en setter van lombok
  * DTO's en mappers gescheiden in eigen entiteiten
* 5 realistische limitaties + Mogelijke doorontwikkelingen
* reflectie op leerproces
* link naar github-repository

### Broncode
* 3 belangrijke kernfunctionaliteiten (naast Authenticate en authorization)
  * toevoegen van patronen door designer
  * kopen van patronen door users
  * zoeken/ophalen van patronen a.d.h. van categorie, designer en/of keywords
  * Authenticatie van users
  * Authorisatie van verschillende rollen
* 2 a 3 rollen
  * User
  * Designer
  * Admin
* 6 Entiteiten (klassen)
  ~~* Product~~
 ~~* Review~~ 
  ~~* Admin~~
  ~~* Purchase~~
  ~~* User~~
  ~~* Keyword~~
  ~~* Designer~~
  ~~* Category~~
  * Security

* 2 geslaagde integratietesten
* 2 classes 100% coverage met unit-tests, gebruikmakend van de drie A's
* Exception handling (voorkom 500-foutmeldingen)
* 20 commits
* 5 pull requests + mergen naar main
* Modellen met data constraints (bv NotNull, max. en min. value, ..)
  * Database constraints op entity (@Column()), Validation annotations in inputDTO's! (=technische keuze)
* Database met testdata via data.sql
* validatie inkomende data
* upload/download integratie
* implementatie kwalitatieve web-API 
* volledig volgens REST richtlijnen

### Installatiehandleiding
1. Inleiding
2. Benodigdheden
3. Installatie instructies (in de vorm van een stappenplan)
4. Testgebruikers
5. Postman collecties
6. REST-endpoints
7. Overige commando’s

### Postman collectie in JSON

#### Algemene eisen:
☐ Documentatie ingeleverd als .pdf.

☐ Document bevat geen bronnen of verwijzingen buiten het document (behalve wanneer hier
expliciet naar gevraagd wordt zoals bijvoorbeeld de link naar het Github-project.).

☐ De eindopdracht is goed leesbaar zonder storende aanwezigheid van grammatica- en
spellingsfouten.

☐ Het volledige project en bijbehorende documenten wordt aangeleverd d.m.v. een ZIP-
bestand van maximaal 50 MB. (geen .rar).

☐ Het ZIP-bestand bevat de volgende elementen:

* Technisch ontwerp (in .pdf)
* Verantwoordingsdocument (in .pdf)
* Broncode van het project (Let op: dus niet alleen de link naar het Github-project)
* Installatiehandleiding (in .pdf)
* Postman collectie in JSON (.json);

#### Inhoudelijke eisen:
☐ Alle deelopdrachten zijn uitgewerkt en de gevraagde deelproducten zijn aanwezig.

☐ De web-API is geprogrammeerd met Springboot en een versie van Java voor long term
support, zoals Java 17 of Java 21.

☐ Er wordt gebruikgemaakt van DTO’s om data te valideren en vervuiling van de database te
voorkomen.

☐ Het project is geüpload naar een GitHub repository: deze repository staat op public. De link is
toegevoegd in jouw verantwoordingsdocument.

☐ Het project wordt ingeleverd zonder out-map/target-map/.idea-map en .iml-bestand.

☐ Maven is gebruikt als dependency manager.

☐ De web-API start op zonder te crashen.