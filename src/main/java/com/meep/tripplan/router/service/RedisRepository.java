package com.meep.tripplan.router.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.meep.tripplan.router.model.Resource;

/**
 * Interfaz que define las operaciones para la cache.
 * 
 * @author .
 *
 */
public interface RedisRepository {

  /**
   * Obtener la lista de recursos segun la KEY.
   * 
   * @return List<Resource> resources.
   * @throws JsonProcessingException
   * @throws JsonMappingException
   */
  List<Resource> findResources(String key) throws JsonProcessingException;

  /**
   * Almacenar la lista de recursos segun la KEY.
   * 
   * @param resources List<Resource>.
   * @throws JsonProcessingException
   */
  void add(List<Resource> resources, String key) throws JsonProcessingException;

  /**
   * Metodo para saber la existencia de una llave.
   * 
   * @param key cadena que representa una llave.
   * @return
   */
  boolean existsKey(String key);
}
