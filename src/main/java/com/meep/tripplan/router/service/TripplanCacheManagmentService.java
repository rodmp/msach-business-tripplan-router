package com.meep.tripplan.router.service;

import java.util.List;
import com.meep.tripplan.router.model.Resource;
import com.meep.tripplan.router.model.TripplanResourceDiffResponse;

/**
 * Clase que manipulara las lista en cache.
 * 
 * @author .
 *
 */
public interface TripplanCacheManagmentService {

  /**
   * Metodo para manipular la lista de viejos y nuevos recursos existentes.
   */
  void managementCacheTripplanResource(List<Resource> resources);
  
  /**
   * Metodo para obtener la lista de recursos nuevos y la lista de recursos no disponibles.
   * @return TripplanResourceDiffResponse.
   */
  TripplanResourceDiffResponse getDifferenceResorces();
}
