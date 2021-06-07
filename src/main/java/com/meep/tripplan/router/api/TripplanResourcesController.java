package com.meep.tripplan.router.api;

import com.meep.tripplan.router.constant.Constants;
import com.meep.tripplan.router.model.TripplanResourceDiffResponse;
import com.meep.tripplan.router.service.TripplanCacheManagmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase controller que adminte llamados a los servicios.
 * 
 * @author .
 *
 */
@RestController
@RequestMapping(Constants.BASE_PATH)
@Slf4j
public class TripplanResourcesController {

  /**
   * variable tipo TripplanCacheManagmentService.
   */
  @Autowired
  private TripplanCacheManagmentService tripplanCacheManagmentService;

  /**
   * Metodo que adminte llamados GET y obtiene la diferencia de los recursos nuevo y no disponibles.
   * 
   * @return ResponseEntity TripplanResourceDiffResponse.
   */
  @ApiOperation(value = Constants.API_RESOURCES_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
      @ApiResponse(code = Constants.CODE_OK, message = Constants.OK,
          response = TripplanResourceDiffResponse.class),
      @ApiResponse(code = Constants.CODE_BAD_REQUEST, message = Constants.BAD_REQUEST),
      @ApiResponse(code = Constants.CODE_INTERNAL_ERROR, message = Constants.INTERNAL_ERROR)})

  @GetMapping(value = Constants.GET_MAPPING_RESOURCE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TripplanResourceDiffResponse> getDifferencesResources() {
    TripplanResourceDiffResponse tripplanResourceDiffResponse =
        tripplanCacheManagmentService.getDifferenceResorces();
    log.debug(Constants.END_REQUEST, tripplanResourceDiffResponse);
    return ResponseEntity.ok(tripplanResourceDiffResponse);
  }
}
