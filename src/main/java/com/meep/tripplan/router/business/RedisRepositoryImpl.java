package com.meep.tripplan.router.business;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meep.tripplan.router.model.Resource;
import com.meep.tripplan.router.service.RedisRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author .
 *
 */
@Service
@Slf4j
public class RedisRepositoryImpl implements RedisRepository {

  /**
   * 
   */
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * 
   */
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * {@inheritDoc}
   * 
   * @throws JsonProcessingException
   * @throws JsonMappingException
   */
  @Override
  public List<Resource> findResources(String key) throws JsonProcessingException {
    String resourcesString = (String) redisTemplate.opsForValue().get(key);
    return mapper.readValue(resourcesString, new TypeReference<List<Resource>>() {});
  }

  /**
   * {@inheritDoc}
   * 
   * @throws JsonProcessingException
   */
  @Override
  public void add(List<Resource> resources, String key) throws JsonProcessingException {
    String jsonInString = mapper.writeValueAsString(resources);
    log.debug("Informacion a guardar en cache: KEY {}, cadena {} ", key, jsonInString);
    redisTemplate.opsForValue().set(key, jsonInString, 1, TimeUnit.DAYS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean existsKey(String key) {
    return redisTemplate.hasKey(key);
  }

}
