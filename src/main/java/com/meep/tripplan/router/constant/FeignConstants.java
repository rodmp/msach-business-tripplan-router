package com.meep.tripplan.router.constant;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Clase de constantes que maneja informacion del llamado rest template.
 * 
 * @author .
 *
 */
@Component
@EnableConfigurationProperties
@Getter
public class FeignConstants {

  /**
   * Valor de la url de consumo.
   */
  @Value("${constants.feign.url.tripplan}")
  private String tripplanUrl;

  /**
   * Valor del resurso externo.
   */
  @Value("${constants.feign.name.tripplan}")
  private String tripplanName;

  /**
   * Valor de la uri de consumo del recurso externo.
   */
  @Value("${constants.feign.endPoint.tripplan.mapping}")
  private String tripplanUri;

  /**
   * Valor del tiempo en que se ejecutara el llamado al recurso externo.
   */
  public static final String TRIPPLAN_ROUTERS_FIXED_RATE = "${api.schedule.fixed.rate}";

  /**
   * Valor de los parametros para lowerLeftLatLon.
   */
  @Value("${api.pathVariable.lowerLeftLatLon}")
  private String lowerLeftLatLon;

  /**
   * Valor de los parametros para upperRightLatLon.
   */
  @Value("${api.pathVariable.upperRightLatLon}")
  private String upperRightLatLon;

  /**
   * Valor de los parametros para companyZoneIds.
   */
  @Value("${api.pathVariable.companyZoneIds}")
  private String companyZoneIds;

  /**
   * constructor privado.
   */
  private FeignConstants() {}
}
