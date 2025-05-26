# Fleetmanager
<div align="center">
  <a href="https://fleetmanager.azurewebsites.net/">fleetmanager.com</a>
</div>
Fleetmanager er en administrationsløsning designet og bygget op for https://bilabonnement.dk/.

## Demo
Der er sat en demo bruger op for let at kunne teste hjemmesiden.
<div align="center">
<h3> Admin </h3>

**Brugernavn**: demo
<br>

**Kodeord**: demo
</div>

Derudover er der også demo brugere til hver rolle:
<div align="center">
<h3> Forretningsudvikler </h3>

**Brugernavn**: demof
<br>

**Kodeord**: 1234

<h3>Mekaniker</h3>

**Brugernavn**: demom
<br>

**Kodeord**: 1234

<h3>Dataregistrering</h3>
**Brugernavn**: demod
<br>

**Kodeord**: 1234
</div>

## Tech Stack

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework
- [Thymelaf](https://www.thymeleaf.org/) - Template Engine
- [Java](https://www.java.com/en/) - Language
- [JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/) - API
- [MySql](https://www.mysql.com/) - Database
- [Microsoft Azure](https://azure.microsoft.com/en-us/) - Hosting

## Kør Applikationen Lokalt
Hvis du vil køre applikationen lokalt skal du have mulighed for at køre MySQL databasen på egen maskine, eksempelvis med Docker.
Derefter skal du sætte dine environmental variables op, os så køre Script.sql
Til sidst skal du køre applikationen, evt igennem din IDE, og så gå ind på http://localhost:8080/.



