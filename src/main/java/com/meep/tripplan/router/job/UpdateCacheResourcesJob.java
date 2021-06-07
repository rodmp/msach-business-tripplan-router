package com.meep.tripplan.router.job;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.meep.tripplan.router.business.TripplanRouterConsume;
import com.meep.tripplan.router.constant.FeignConstants;
import com.meep.tripplan.router.model.Resource;
import com.meep.tripplan.router.service.TripplanCacheManagmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Clase de trabajo para actualizar la cache de los recursos.
 * 
 * @author .
 *
 */
@Component
@Slf4j
public class UpdateCacheResourcesJob {

  /**
   * 
   */
  @Autowired
  private FeignConstants feignConstants;

  /**
   * 
   */
  @Autowired
  private TripplanRouterConsume tripplanRouterClient;
  
  /**
   * 
   */
  @Autowired
  private TripplanCacheManagmentService tripplanCacheManagmentService;
  
  /**
   * 
   */
  @Scheduled(fixedRateString = FeignConstants.TRIPPLAN_ROUTERS_FIXED_RATE)
  public void updateTripplanRoutersCacheInfo() {

    List<Resource> resources =
        tripplanRouterClient.getTripplanResources();

    log.debug("Recuersos devueltos del consumo del servicio {} : {}",
        feignConstants.getTripplanName(), resources);
    
    tripplanCacheManagmentService.managementCacheTripplanResource(resources);
  }
}
