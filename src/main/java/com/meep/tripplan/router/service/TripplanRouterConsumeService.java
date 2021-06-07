package com.meep.tripplan.router.service;

import java.util.List;
import com.meep.tripplan.router.model.Resource;

/**
 * Interfaz que define el contrato para el manejo de rest template.
 * @author .
 *
 */
public interface TripplanRouterConsumeService {

  /**
   * Metodo para obtener los recursos por medio de restTemplate.
   */
  public List<Resource> getTripplanResources();
}
