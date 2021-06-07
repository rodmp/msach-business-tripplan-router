package com.meep.tripplan.router.constant;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene los valores externalizados del o las API's.
 * 
 * @author .
 */
@Component
@Getter
public class ApiValues {

  /**
   * Valor externalizado para indicar el paquete base del proyecto.
   */
  @Value("${constants.swagger.basePackage}")
  private String basePackage;

  /**
   * Valor externalizado para indicar el titulo del API.
   */
  @Value("${constants.swagger.title}")
  private String title;

  /**
   * Valor externalizado para indicar la descripcion de la API.
   */
  @Value("${constants.swagger.descriptionApi}")
  private String descriptionApi;

  /**
   * Valor externalizado para indicar la version del swagger.
   */
  @Value("${constants.swagger.version}")
  private String version;

  /**
   * Valor externalizado para indicar el nombre del desarrollador.
   */
  @Value("${constants.swagger.nameDeveloper}")
  private String nameDeveloper;

  /**
   * Valor externalizado para indicar la URL de la peticion.
   */
  @Value("${constants.swagger.contactUrl}")
  private String contactUrl;

  /**
   * Valor externalizado para indicar el mail del desarrollador.
   */
  @Value("${constants.swagger.emailDeveloper}")
  private String emailDeveloper;

  /**
   * Valor externalizado para indicar las etiquetas del swagger.
   */
  @Value("${constants.swagger.label}")
  private String label;

  /**
   * Valor externalizado para indicar el recurso a consumir.
   */
  @Value("${constants.swagger.resourceLocation}")
  private String resourceLocation;

  /**
   * Valor externalizado para indicar donde se generan los artefactos de swagger.
   */
  @Value("${constants.swagger.webjars}")
  private String webjars;

  /**
   * Valor externalizado para indicar la ubicacion de los artefactos.
   */
  @Value("${constants.swagger.webjarsLocation}")
  private String webjarsLocation;

  /**
   * Constructor privado para evitar la instancia de esta clase.
   */
  private ApiValues() {

  }

}
