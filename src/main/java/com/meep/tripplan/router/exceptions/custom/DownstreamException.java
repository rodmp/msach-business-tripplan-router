package com.meep.tripplan.router.exceptions.custom;

import java.time.ZonedDateTime;
import java.util.Map;

import com.meep.tripplan.router.constant.Constants;
import com.meep.tripplan.router.constant.SpecialCharacterConstants;
import com.meep.tripplan.router.exceptions.ErrorResponse;
import com.netflix.hystrix.exception.ExceptionNotWrappedByHystrix;

import lombok.Getter;

/**
 * Clase de la excepcion DownstreamException que hereda de RuntimeException e implementa
 * ExceptionNotWrappedByHystrix.
 * 
 * @author .
 */
@Getter
public class DownstreamException extends RuntimeException implements ExceptionNotWrappedByHystrix {

  /**
   * UID autogenerado para el versionado de la clase.
   */
  private static final long serialVersionUID = 3404054956828772740L;

  /**
   * Http Status que se asignara.
   */
  private final int status;

  /**
   * Tipo de error.
   */
  private final String type;

  /**
   * Codigo que se definio para el error especifico.
   */
  private final String code;

  /**
   * Detalles del error.
   */
  private final String details;

  /**
   * URI que se invoco y que esta regresando el error.
   */
  private final String location;

  /**
   * Informacion adicional para casos especificos.
   */
  private final String moreInfo;

  /**
   * Identificador unico de la peticion.
   */
  private final String uuid;

  /**
   * Fecha y hora en que ocurrio el error..
   */
  private final ZonedDateTime timestamp;

  /**
   * Constructor que inicializa los valores por defecto.
   */
  public DownstreamException() {

    super();

    this.status = Constants.DEFAULT_STATUS_HTTP;
    this.type = SpecialCharacterConstants.EMPTY_STRING;
    this.code = SpecialCharacterConstants.EMPTY_STRING;
    this.details = SpecialCharacterConstants.EMPTY_STRING;
    this.location = SpecialCharacterConstants.EMPTY_STRING;
    this.moreInfo = SpecialCharacterConstants.EMPTY_STRING;
    this.uuid = SpecialCharacterConstants.EMPTY_STRING;
    this.timestamp = ZonedDateTime.now();

  }

  /**
   * Constructor que recibe el estatus Http de la respuesta y los valores de la misma.
   * 
   * @param status of the response.
   * @param errorResponse response of the error from the ms feign.
   */
  public DownstreamException(int status, Map<String, String> errorResponse) {

    super(Constants.DOWNSTREAMEXCEPTION + SpecialCharacterConstants.SPACE_STRING + status
        + SpecialCharacterConstants.COLON + SpecialCharacterConstants.SPACE_STRING
        + errorResponse.get(Constants.ERROR_RESPONSE_DETAILS));

    this.status = status;
    this.type = errorResponse.get(Constants.ERROR_RESPONSE_TYPE);
    this.code = errorResponse.get(Constants.ERROR_RESPONSE_CODE);
    this.details = errorResponse.get(Constants.ERROR_RESPONSE_DETAILS);
    this.location = errorResponse.get(Constants.ERROR_RESPONSE_LOCATION);
    this.moreInfo = errorResponse.get(Constants.ERROR_RESPONSE_MORE_INFORMATION);
    this.uuid = errorResponse.get(Constants.UUID);

    String timeStamp = errorResponse.get(Constants.ERROR_RESPONSE_TIMESTAMP);

    timeStamp = timeStamp.substring(SpecialCharacterConstants.INT_ZERO_VALUE,
        timeStamp.length() - SpecialCharacterConstants.INT_TWO_VALUE)
        + SpecialCharacterConstants.COLON
        + timeStamp.substring(timeStamp.length() - SpecialCharacterConstants.INT_TWO_VALUE);

    ZonedDateTime zonedDateTime = ZonedDateTime.parse(timeStamp);

    this.timestamp = zonedDateTime;

  }

  /**
   * Constructor que recibe el estatus Http de la respuesta y los valores de la misma.
   * 
   * @param status of the response.
   * @param errorResponse response of the error from the ms feign.
   */
  public DownstreamException(int status, ErrorResponse errorResponse) {

    super(Constants.DOWNSTREAMEXCEPTION + SpecialCharacterConstants.SPACE_STRING + status
        + SpecialCharacterConstants.COLON + SpecialCharacterConstants.SPACE_STRING
        + errorResponse.getDetails());

    this.status = status;
    this.type = errorResponse.getType();
    this.code = errorResponse.getCode();
    this.details = errorResponse.getDetails();
    this.location = errorResponse.getLocation();
    this.moreInfo = errorResponse.getMoreInfo();
    this.uuid = errorResponse.getUuid();
    this.timestamp = errorResponse.getTimestamp();

  }

}
