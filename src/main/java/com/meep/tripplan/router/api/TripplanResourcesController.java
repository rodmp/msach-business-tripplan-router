package com.meep.tripplan.router.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meep.tripplan.router.constant.Constants;
import com.meep.tripplan.router.model.TripplanResourceDiffResponse;
import com.meep.tripplan.router.service.TripplanCacheManagmentService;

/**
 * 
 * @author .
 *
 */
@RestController
@RequestMapping(Constants.BASE_PATH)
public class TripplanResourcesController {

  /**
   * 
   */
  @Autowired
  private TripplanCacheManagmentService tripplanCacheManagmentService; 
  
  /**
   * 
   * @return
   */
  @GetMapping(value = Constants.GET_MAPPING_RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TripplanResourceDiffResponse> getDifferencesResources(){
    return ResponseEntity.ok(tripplanCacheManagmentService.getDifferenceResorces());
  }
}
