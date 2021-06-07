package com.meep.tripplan.router.job;

import com.meep.tripplan.router.business.TripplanRouterConsume;
import com.meep.tripplan.router.constant.Constants;
import com.meep.tripplan.router.constant.FeignConstants;
import com.meep.tripplan.router.model.Resource;
import com.meep.tripplan.router.service.TripplanCacheManagmentService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de trabajo para actualizar la cache de los recursos.
 * 
 * @author .
 *
 */
@Component
@Slf4j
public class UpdateCacheResourcesJob {

  /**
   * Variable FeignConstants.
   */
  @Autowired
  private FeignConstants feignConstants;

  /**
   * Variable TripplanRouterConsume.
   */
  @Autowired
  private TripplanRouterConsume tripplanRouterClient;

  /**
   * Variable TripplanCacheManagmentService.
   */
  @Autowired
  private TripplanCacheManagmentService tripplanCacheManagmentService;

  /**
   * Metodo que se ejecuta cada 30 segundos y consume un servicio externo para obtener informacion
   * de los recursos nuevos y no disponibles. Posteriormente mandar a administrar la cache.
   */
  @Scheduled(fixedRateString = FeignConstants.TRIPPLAN_ROUTERS_FIXED_RATE)
  public void updateTripplanRoutersCacheInfo() {

    List<Resource> resources = tripplanRouterClient.getTripplanResources();

    log.debug(Constants.RESOURCES_REST_TEMPLATE, feignConstants.getTripplanName(), resources);

    tripplanCacheManagmentService.managementCacheTripplanResource(resources);
  }
}
