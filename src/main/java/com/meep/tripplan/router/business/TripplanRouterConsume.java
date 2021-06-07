package com.meep.tripplan.router.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.meep.tripplan.router.constant.ErrorResolverConstants;
import com.meep.tripplan.router.constant.FeignConstants;
import com.meep.tripplan.router.exceptions.custom.TimeoutRestTemplateException;
import com.meep.tripplan.router.model.Resource;
import com.meep.tripplan.router.service.TripplanRouterConsumeService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TripplanRouterConsume implements TripplanRouterConsumeService {

  @Autowired
  private RestTemplate restTemplate;

  /**
   * 
   */
  @Autowired
  private FeignConstants feignConstants;

  /**
   * 
   */
  @Autowired
  private ErrorResolverConstants errorResolverConstants;

  /**
   * Metodo para obtener los recuersos de viaje por un cliente feign.
   * 
   * @return
   */
  public List<Resource> getTripplanResources() {
    String url = feignConstants.getTripplanUrl().concat(feignConstants.getTripplanUri());
    log.debug("Url de consumo para obtener recursos: {} ", url);

    ResponseEntity<List<Resource>> responseApi = null;
    try {
      responseApi = restTemplate.exchange(url, HttpMethod.GET, null,
          new ParameterizedTypeReference<List<Resource>>() {}, feignConstants.getLowerLeftLatLon(),
          feignConstants.getUpperRightLatLon(), feignConstants.getCompanyZoneIds());
    } catch (ResourceAccessException resourceAccessException) {
      throw new TimeoutRestTemplateException(errorResolverConstants.getRequestTimeoutException());
    }
    return responseApi.getBody();
  }
}
