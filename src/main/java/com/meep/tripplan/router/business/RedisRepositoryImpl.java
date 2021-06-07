package com.meep.tripplan.router.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meep.tripplan.router.constant.Constants;
import com.meep.tripplan.router.model.Resource;
import com.meep.tripplan.router.service.RedisRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Clase que maneja las operaciones a la cache en este caso Redis.
 * 
 * @author .
 *
 */
@Service
@Slf4j
public class RedisRepositoryImpl implements RedisRepository {

  /**
   * variable tipo RedisTemplate.
   */
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * Variable tipo ObjectMapper.
   */
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * {@inheritDoc}.
   * 
   * @throws JsonProcessingException exception.
   */
  @Override
  public List<Resource> findResources(String key) throws JsonProcessingException {
    String resourcesString = (String) redisTemplate.opsForValue().get(key);
    log.debug(Constants.GET_REDIS_CACHE , key, resourcesString);
    return mapper.readValue(resourcesString, new TypeReference<List<Resource>>() {});
  }

  /**
   * {@inheritDoc}.
   * 
   * @throws JsonProcessingException exception.
   */
  @Override
  public void add(List<Resource> resources, String key) throws JsonProcessingException {
    String jsonInString = mapper.writeValueAsString(resources);
    log.debug(Constants.SAVE_REDIS_CACHE, key, jsonInString);
    redisTemplate.opsForValue().set(key, jsonInString, 1, TimeUnit.DAYS);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Boolean existsKey(String key) {
    return redisTemplate.hasKey(key);
  }

}
