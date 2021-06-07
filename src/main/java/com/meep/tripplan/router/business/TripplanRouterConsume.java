package com.meep.tripplan.router.business;

import com.meep.tripplan.router.constant.Constants;
import com.meep.tripplan.router.constant.ErrorResolverConstants;
import com.meep.tripplan.router.constant.FeignConstants;
import com.meep.tripplan.router.exceptions.custom.TimeoutRestTemplateException;
import com.meep.tripplan.router.model.Resource;
import com.meep.tripplan.router.service.TripplanRouterConsumeService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class TripplanRouterConsume implements TripplanRouterConsumeService {

  @Autowired
  private RestTemplate restTemplate;

  /**
   * Variable FeignConstants.
   */
  @Autowired
  private FeignConstants feignConstants;

  /**
   * Variable ErrorResolverConstants.
   */
  @Autowired
  private ErrorResolverConstants errorResolverConstants;

  /**
   * Metodo para obtener los recuersos de viaje por un cliente feign.
   * 
   * @return List de Resources.
   */
  public List<Resource> getTripplanResources() {
    String url = feignConstants.getTripplanUrl().concat(feignConstants.getTripplanUri());
    log.debug(Constants.REST_TEMPLATE_CONSUME, url);

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
