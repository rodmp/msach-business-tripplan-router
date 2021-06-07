package com.meep.tripplan.router.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;

/**
 * 
 * @author .
 *
 */
@Component
@EnableConfigurationProperties
@Getter
public class FeignConstants {

  /**
   * 
   */
  @Value("${constants.feign.url.tripplan}")
  private String tripplanUrl;

  /**
   * 
   */
  @Value("${constants.feign.name.tripplan}")
  private String tripplanName;

  /**
   * 
   */
  @Value("${constants.feign.endPoint.tripplan.mapping}")
  private String tripplanUri;

  /**
   * 
   */
  public static final String TRIPPLAN_ROUTERS_FIXED_RATE = "${api.schedule.fixed.rate}";

  /**
   * 
   */
  @Value("${api.pathVariable.lowerLeftLatLon}")
  private String lowerLeftLatLon;

  /**
   * 
   */
  @Value("${api.pathVariable.upperRightLatLon}")
  private String upperRightLatLon;

  /**
   * 
   */
  @Value("${api.pathVariable.companyZoneIds}")
  private String companyZoneIds;

  /**
   * constructor privado.
   */
  private FeignConstants() {}
}
