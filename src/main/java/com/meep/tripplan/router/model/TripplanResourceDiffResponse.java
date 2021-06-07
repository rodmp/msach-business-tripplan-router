package com.meep.tripplan.router.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author .
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripplanResourceDiffResponse {

  /**
   * 
   */
  private List<Resource> newResources;
  
  /**
   * 
   */
  private List<Resource> notAvailable;
}
