package com.meep.tripplan.router.service;

import com.meep.tripplan.router.model.Resource;

import java.util.List;

/**
 * Interfaz que define el contrato para el manejo de rest template.
 * @author .
 *
 */
public interface TripplanRouterConsumeService {

  /**
   * Metodo para obtener los recursos por medio de restTemplate.
   */
  List<Resource> getTripplanResources();
}
