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

```

 - El servicio es invocable desde el paquete  **com.meep.tripplan.router.api** en el metodo:
```java
  public ResponseEntity<TripplanResourceDiffResponse> getDifferencesResources(){
    
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
