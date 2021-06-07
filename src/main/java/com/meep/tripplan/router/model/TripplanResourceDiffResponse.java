package com.meep.tripplan.router.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Clase DTO para regresar las dos listas de recursos, nuevos y no disponibles.
 * @author .
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripplanResourceDiffResponse {

  /**
   * Lista de newResources.
   */
  private List<Resource> newResources;
  
  /**
   * Lista de notAvailable.
   */
  private List<Resource> notAvailable;
}
