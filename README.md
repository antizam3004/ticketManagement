# NSoft-zadatak

Spring boot aplikacija rađena u InteliJ IDEA. 

Prije pokretanja potrebno u mysql-u kreirati bazu tickets_db i importati datoteku tickets_db.sql iz repozitorija. Nakon toga pokrenuti aplikaciju.

<br/>
Dodavanje novih tiketa se radi koristeći POST metodu na link:
http://localhost:8080/stakelimitservice/tickets

Šalje se json u formatu:
```json
{
    "id":"31288d90-385e-11eb-adc1-0242ac120002",
    "deviceId":"31288d90-385e-11eb-adc1-0242ac120002",
    "stake":220
}
```

<br/>
Konfiguracija parametara se radi koristeći POST metodu na link:
http://localhost:8080/stakelimitservice/configuration

Šalje se json u formatu:
```json
{
    "timeDuration":600,
    "stakeLimit":1000,
    "hotPercentage":80,
    "restrictionExpires":3600
}
```
U mysql-u je postavljen trigger koji signalizira ako su vrijednosti veće ili manje od dozvoljenih. 


<br/><br/>

U repozitoriju se nalaze Dockerfile i docker-compse.yml. Ako se pokreće docker-compose potrebno pomoću Mavena napraviti "package" da se kreira snapshot.jar.
Nije mi uspjelo da pokrenem na ovaj način aplikaciju jer sam imao problema sa povezivanjem sa mysql-om unutar kontejnera.

<br/><br/>
Ante Sušac<br/>
ante.susac@gmail.com
