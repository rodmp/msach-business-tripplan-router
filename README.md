msach-business-tripplan-router.
Este microservicio tiene 1 APIs que obtiene los recursos nuevos y recursos que no estan disponibles:

1.Hace el consumo de los recursos disponibles en un are por medio del siguiente endpoint:
https://apidev.meep.me/tripplan/api/v1/routers/lisboa/resources?lowerLeftLatLon=38.711046,-9.160096&upperRightLatLon=38.739429,-9.137115&companyZoneIds=545,467,473

2. Se utiliza una memoria cache en este caso Redis para lo siguiente:
	a.- Si no hay nada en cache con la llave "resources", almacena la lista de recursos que regreso el consumo del servicio del paso 1
	b.- Cada 30 segundos se consume el endpoint del paso 1 para obtener las difencias, nuevos recursos disponibles y recursos que ya no 
	estan disponibles. Se guarda la lista en cache de los recursos nuevos con la llave "newResources" y los recursos que ya no estan 
	disponibles con la llave "oldResources".
	
Se realizó la implementacion metricas de acceso a recursos externos como mongo y redis.
- **LOG classes**
- **PMD changes**
- **Checkstyle** 
- **Class name refactor**
- **FindBugs**
- **Others**

07/06/2021 by Rodolfo Miranda

> /api/public/v1/tripplan/resources/difference/resources/retrieve GET

En el body del request se debe de incluir el json resultado del analisis que realiza.

URL :

http://localhost:8080/api/public/v1/tripplan/resources/difference/resources/retrieve

> Test data : 
``` json
{
    "customer": {
        "id": "Tndsihg7gtlPiFERapsL/Or/JmRqvCkIZawrWzUU13PnECt1xdVLUnpEncTdQ1WGwNLp/q+mfbuyyYMUE0r46u4re1+BNfLwz6ZjRIXzqXTJes/OW3wbLiEFfQ/yZmB1vHlGifn8xYrL6fkMqi3VO7cOTRR4gHele1VuapUANvtBnuxpANvQVBkz13g6KC+eC9xCofBy6bk1KSUASunNWUlR3/9qtXhXyp5vhYofl7MN8j8UeNrqwLpZGlD1jMuJgRSmaCiy8yv/cDysdaDqKIkdi0Vcvp3wCJABWfY4DriPrRm12FCAy26Ka0PsQF1oPBfv6X2UYhzVYG+3Xu0CeQ==",
        "cellphone": "UQVhpXvXDPXoufw+s/K1MACs9c1FaVtP5FR6CsIt5T/r0EdkC5SLNK4vxFyAyLdafiaZzakHkEgifqb56vPnRkHLKB+h4LYr7Pj6VmPqjZ3d9zAXRZYc0rPZUtjg2VvtqPunOE1Dbjcq/TAkENpW0EZEWAPcyg+EaxsTYHTtrgan2ZTGJEVNs++AdO8a4G+4X2vi+w81OrXgB+ReregEAGLJotDaHPaZA0CvIXaaGh9Leeu6dvL/pZbBVp22R9XjlxLfZ81NC+SF5DQGpXcvc2QtYJRXdrCn9z8xc7rluFC1E18xPKZ2NtyF1PWaSECc+qS8wSAq/ssPZsivXUQ8sw==",
        "birthDate": "GeeCkL8XxmXpjR03Tbql2hMiIGqSRzkZMk9v3R187sjs3Z/xoJMV+Tc4I7g8+tlUwTtoD5JzuXu7m291WmyU3JpY0FAaXTvOki8HZapq+boQJmKfQSeNGT9rrhXFTEnooneAUnxOx1CQxO1q9VX0M3fjRhHbfFD4Fz4b5KLsUj9Z0o3nAQaGXbO+PZQvsW0DnWBQh+s7spBBvorrklYdd26Fi4VvweMmWsqgcb85LRCD1hW7l3Oj5rlxygzTzxMka6I4PS8C/aTQFi/rfRHWnPGuzOzFsUIpxWPFOnjREGPTPiovf2OPVGoSrhRTsRgYGEIhgTj8CVxwsA+g/JGsyQ=="
    },
    "termStatus": {
        "acept": true,
        "version": "1.0"
    }
}

```

 - El servicio es invocable desde el paquete  **com.bancoppel.customer.phone.enrollment** en el metodo:
```java
  public CustomerDataResponse customerDataResponse(CustomeRequest customeRequest,
      HeadersModel header) {
  }

```
> /api/public/v1/channel/authentication/phone/enrollment/confirm POST

Recibe una contraseña, confirmacion de la misma.

URL : 
http://localhost:8080/api/public/v1/channel/authentication/phone/enrollment/confirm POST
- Objeto json encriptado
```json
{
    "password": "SF/9/SNfeWqculUpiMauPcvKMJruKwx7qECpt/e61Dh1f49MrdMkxTRDRQgOnwwpWNFX0FOWA/Y283eBp9pWy/0EqdToS1KSkXDXl7VAs67hn0lgsZZ0/54jluHrlhfC+SIxrL2FjzThaEvIw0CsFkMfo7ixzoOKIGiRJeHtgW4B9WCUwZRk381CiEiR/jfNDFNUaPmg+I+ykEhe5dal6uoGgn3SmI+zqXOfHVDZj9I4KoQI9zKebcLpkiQ5g90mI745694otz4zromEPnMuAgNZPKZ8Xej+JF7Rs2UumZBHV8lpvESDLDjphjENWavG3vUNViDLQNqvb9thwfASiQ==",
    "passwordConfirm": "SF/9/SNfeWqculUpiMauPcvKMJruKwx7qECpt/e61Dh1f49MrdMkxTRDRQgOnwwpWNFX0FOWA/Y283eBp9pWy/0EqdToS1KSkXDXl7VAs67hn0lgsZZ0/54jluHrlhfC+SIxrL2FjzThaEvIw0CsFkMfo7ixzoOKIGiRJeHtgW4B9WCUwZRk381CiEiR/jfNDFNUaPmg+I+ykEhe5dal6uoGgn3SmI+zqXOfHVDZj9I4KoQI9zKebcLpkiQ5g90mI745694otz4zromEPnMuAgNZPKZ8Xej+JF7Rs2UumZBHV8lpvESDLDjphjENWavG3vUNViDLQNqvb9thwfASiQ=="
}
```
 - La clase que realiza el cambio de password se encuentra en el paquete: **com.bancoppel.customer.phone.enrollment** en el metodo:
```java
  public SessionOpen createCustomerConfirm(PasswordBexRequest passwordRequest,
      HeadersModel headers) {
	  }	  
```


* Maven
* Spring
* SpringBoot
* Spring Tools Suite
* Lombok
* Redis


Se necesita tener instalado:
		
- Contar con VPN de coppel 
 - Java 1.8  		
 - Maven 		
 - Spring Tools Suite
 - lombok
 - Redis


    mvn spring boot:run  en Local 
	mvn fabric8:deploy   Openshift 
O desde Spring Tools Suite click derecho sobre el proyecto -> Run As -> Spring Boot App



 - Desde pring Tools Suite click derecho sobre el proyecto  > Run As >
   JUnit Test. 
 
 mvn clean package site sonar:sonar -Pdevelopment_reporting 

 Esto ejecuta todas las pruebas unitarias del proyectos y genera los reportes en el site y el sonar.
 


Genera el reporte de check-style html y lo guarda en el site.


Se divide en dos partes:

 1. Informacion del proyecto.

	- Proporciona una descripción general de los diversos documentos y enlaces que forman parte de la información general de este proyecto.
	
2. Reportes de proyecto
	
	- Brindan un panoramageneral de varios reportes que son generados automaticamente por Maven.
	
EL site es generado  dentro de la ca: target > site > index.html
	

	$ mvn clean install site


Sonar es una plataforma de código abierto utilizada por los equipos de desarrollo para gestionar la calidad del código fuente.

mvn clean package site sonar:sonar -Pdevelopment_reporting

En las últimas líneas de la salida de consola hay un enlace, cópielo y péguelo en cualquier navegador web para ver el informe.
