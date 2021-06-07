package com.meep.tripplan.router.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meep.tripplan.router.constant.RedisCacheConstants;
import com.meep.tripplan.router.exceptions.custom.RedisOperationException;
import com.meep.tripplan.router.model.Resource;
import com.meep.tripplan.router.model.TripplanResourceDiffResponse;
import com.meep.tripplan.router.service.TripplanCacheManagmentService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase implementacion para manipular la cache y almacenar la informacion de los recursos nuevos y
 * no disponibles.
 * 
 * @author .
 *
 */
@Service
@Slf4j
public class TripplanCacheManagment implements TripplanCacheManagmentService {

  /**
   * Variable RedisRepositoryImpl.
   */
  @Autowired
  private RedisRepositoryImpl redisRepositoryImpl;

  /**
   * {@inheritDoc}.
   */
  @Override
  public void managementCacheTripplanResource(List<Resource> resources) {
    if (!resources.isEmpty()) {
      try {
        if (redisRepositoryImpl.existsKey(RedisCacheConstants.KEY)) {
          List<Resource> oldResources = redisRepositoryImpl.findResources(RedisCacheConstants.KEY);
          List<Resource> newResources = resources.stream()
              .filter(element -> !oldResources.contains(element)).collect(Collectors.toList());

          List<Resource> notAvailableResources = oldResources.stream()
              .filter(element -> !resources.contains(element)).collect(Collectors.toList());

          redisRepositoryImpl.add(newResources, RedisCacheConstants.KEY_NEW);
          redisRepositoryImpl.add(notAvailableResources, RedisCacheConstants.KEY_OLD);
        }
        redisRepositoryImpl.add(resources, RedisCacheConstants.KEY);
      } catch (JsonProcessingException exception) {
        log.debug(exception.getMessage());
      }
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public TripplanResourceDiffResponse getDifferenceResorces() {
    TripplanResourceDiffResponse tripplanResourceDiffResponse = new TripplanResourceDiffResponse();
    try {
      if (redisRepositoryImpl.existsKey(RedisCacheConstants.KEY_OLD)) {
        List<Resource> notAvailable =
            redisRepositoryImpl.findResources(RedisCacheConstants.KEY_OLD);
        tripplanResourceDiffResponse.setNotAvailable(notAvailable);
      }

      if (redisRepositoryImpl.existsKey(RedisCacheConstants.KEY_NEW)) {
        List<Resource> newResources =
            redisRepositoryImpl.findResources(RedisCacheConstants.KEY_NEW);
        tripplanResourceDiffResponse.setNewResources(newResources);
      }
    } catch (JsonProcessingException exception) {
      throw new RedisOperationException("Error la intentar una operacion en cache");
    }
    return tripplanResourceDiffResponse;
  }
}
