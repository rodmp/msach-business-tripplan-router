#msach-business-tripplan-router.

## Description
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

### Versioning
1.0.0

#### Last modification date:
07/06/2021 by Rodolfo Miranda

#### 1. Obtiene recursos actuales.
### Endpoint
> /api/public/v1/tripplan/resources/difference/resources/retrieve GET

En el body del request se debe de incluir el json resultado del analisis que realiza.

## instrucciones de consumo
URL :

http://localhost:8080/swagger-ui.html#!/tripplan-resources-controller/getDifferencesResourcesUsingGET

```

 - El servicio es invocable desde el paquete  **com.meep.tripplan.router.api** en el metodo:
```java
  public ResponseEntity<TripplanResourceDiffResponse> getDifferencesResources(){
    
  }

```
## Built With
* Maven
* Spring
* SpringBoot
* Spring Tools Suite
* Lombok
* Redis


Se necesita tener instalado:
		
 - Java 1.8  		
 - Maven 		
 - Spring Tools Suite
 - lombok
 - Redis

###
Instalar redis en docker

docker run -d -p 6379:6379 redis

Istalación en Ubuntu

$ sudo add-apt-repository ppa:redislabs/redis
$ sudo apt-get update
$ sudo apt-get install redis

## Deployment
    mvn spring boot:run  en Local 
    mvn fabric8:deploy -Popenshift 
O desde Spring Tools Suite click derecho sobre el proyecto -> Run As -> Spring Boot App


### Running JUnit tests
 - Desde pring Tools Suite click derecho sobre el proyecto  > Run As >
   JUnit Test. 
 
 mvn clean package site sonar:sonar -Pdevelopment_reporting 

 Esto ejecuta todas las pruebas unitarias del proyectos y genera los reportes en el site y el sonar.
 

### Reporting
Genera el reporte de check-style html y lo guarda en el site.

#### site
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

#-**Preguntas**

- ¿Cómo de escalable es tu solución propuesta? 

	Se puede instalar en un openshift local. 
	Aumentar el número de contenedores o pods.

- ¿Que problemas a futuro podría presentar? Si has detectado alguno 

	1.- Puede haber problemas de concurrencia, tiempo de procesamiento si la lista de recursos es muy grande o si existen problema con algunas operaciones con la cache.
   
	2.- Cuando cambien de entradas para obtener diferentes coordenadas se borrara lo que quizas se utilice en otro usuario.

- ¿Qué alternativa/s propones para solventar dichos problemas?

	Se puede manejar concurrencia para evitar dead locks

	Manejar un mayor tiempo en ir a obtener los recursos.

	Manejo de la informacion desde el cliente y no desde un backend.
