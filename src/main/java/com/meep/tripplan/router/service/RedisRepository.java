package com.meep.tripplan.router.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meep.tripplan.router.model.Resource;

import java.util.List;

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
   * @return List Resource resources.
   * @throws JsonProcessingException exception.
   */
  List<Resource> findResources(String key) throws JsonProcessingException;

  /**
   * Almacenar la lista de recursos segun la KEY.
   * 
   * @param resources List Resource.
   * @throws JsonProcessingException exception.
   */
  void add(List<Resource> resources, String key) throws JsonProcessingException;

  /**
   * Metodo para saber la existencia de una llave.
   * 
   * @param key cadena que representa una llave.
   * @return Boolean value.
   */
  Boolean existsKey(String key);
}
