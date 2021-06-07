package com.meep.tripplan.router.constant;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * clase de constantes correspondientes al ErrorResolver.
 * 
 * @author Nova Solution Systems.
 * @since 01.01.2020.
 */
@Component
@Getter
public class ErrorResolverConstants {

  /**
   * Constante para representar un código de error causado por
   * DataNotFoundException.
   */
  @Value("${constants.errorResolver.errorCodes.noDataFoundException}")
  private String noDataFoundException;

  /**
   * Constante para representar un código de error causado por
   * BadRequestException.
   */
  @Value("${constants.errorResolver.errorCodes.badRequestException}")
  private String badRequestException;

  /**
   * Constante para representar un código de error causado por
   * UnauthorizedException.
   */
  @Value("${constants.errorResolver.errorCodes.unauthorizedException}")
  private String unauthorizedException;

  /**
   * Constante para representar un código de error causado por forbiddenException.
   */
  @Value("${constants.errorResolver.errorCodes.forbiddenException}")
  private String forbiddenException;

  /**
   * Constante para representar un código de error causado por
   * NoHandlerFoundException.
   */
  @Value("${constants.errorResolver.errorCodes.noHandlerFoundException}")
  private String noHandlerFoundException;

  /**
   * Constante para representar un código de error causado por
   * HttpRequestMethodNotSupportedException.
   */
  @Value("${constants.errorResolver.errorCodes.httpRequestMethodNotSupportedException}")
  private String httpRequestMethodNotSupportedException;

  /**
   * Constante para representar un código de error causado por
   * HttpMediaTypeNotAcceptableException.
   */
  @Value("${constants.errorResolver.errorCodes.httpMediaTypeNotAcceptableException}")
  private String httpMediaTypeNotAcceptableException;

  /**
   * Constante para representar un código de error causado por
   * HttpMediaTypeNotSupportedException.
   */
  @Value("${constants.errorResolver.errorCodes.httpMediaTypeNotSupportedException}")
  private String httpMediaTypeNotSupportedException;

  /**
   * Constante para representar un código de error causado por
   * ServletRequestBindingException.
   */
  @Value("${constants.errorResolver.errorCodes.servletRequestBindingException}")
  private String servletRequestBindingException;

  /**
   * Constante para representar un código de error causado por
   * HttpMessageNotReadableException.
   */
  @Value("${constants.errorResolver.errorCodes.httpMessageNotReadableException}")
  private String httpMessageNotReadableException;

  /**
   * Constante para representar un código de error causado por
   * MethodArgumentNotValidException.
   */
  @Value("${constants.errorResolver.errorCodes.methodArgumentNotValidException}")
  private String methodArgumentNotValidException;

  /**
   * Constante para representar un código de error causado por
   * ConstraintViolationException.
   */
  @Value("${constants.errorResolver.errorCodes.constraintViolationException}")
  private String constraintViolationException;

  /**
   * Constante para representar un código de error causado por
   * HystrixRuntimeException.
   */
  @Value("${constants.errorResolver.errorCodes.hystrixRuntimeException}")
  private String hystrixRuntimeException;

  /**
   * Constante para representar un código de error causado por
   * microserviceClientException.
   */
  @Value("${constants.errorResolver.errorCodes.microserviceClientException}")
  private String microserviceClientException;

  /**
   * Constante para representar un código de error causado por
   * externalResourceException.
   */
  @Value("${constants.errorResolver.errorCodes.externalResourceException}")
  private String externalResourceException;

  /**
   * Constante para representar un código de error causado por
   * requestTimeoutException.
   */
  @Value("${constants.errorResolver.errorCodes.requestTimeoutException}")
  private String requestTimeoutException;

  /**
   * Constante para representar un código de error causado por exception.
   */
  @Value("${constants.errorResolver.errorCodes.exception}")
  private String exception;

  /**
   * Constante para mostrar un mensaje cuando no se encuentran los datos
   * solicitados.
   */
  @Value("${constants.errorResolver.messages.noDataFoundException}")
  private String noDataFoundExceptionMessage;

  /**
   * Constante para mostrar un mensaje cuando un cliente no esta autorizado.
   */
  @Value("${constants.errorResolver.messages.unauthorizedException}")
  private String unauthorizedExceptionMessage;

  /**
   * Constante para mostrar un mensaje cuando un cliente no tiene permisos de
   * consumir un recurso.
   */
  @Value("${constants.errorResolver.messages.forbiddenException}")
  private String forbiddenExceptionMessage;

  /**
   * Constante para representar un mensaje de error causado por una peticion
   * incorrecta.
   */
  @Value("${constants.errorResolver.messages.badRequestException}")
  private String badRequestExceptionMessage;

  /**
   * Constante para representar un mensaje de error causado porque no se encontro
   * un recurso solicitado.
   */
  @Value("${constants.errorResolver.messages.noHandlerFoundException}")
  private String noHandlerFoundExceptionMessage;

  /**
   * Constante para representar un mensaje de error causado porque un recurso
   * externo no respondio en el tiempo esperado.
   */
  @Value("${constants.errorResolver.messages.requestTimeoutException}")
  private String requestTimeoutExceptionMessage;

  /**
   * Constante para representar un mensaje de error causado por un error
   * desconocido.
   */
  @Value("${constants.errorResolver.messages.exception}")
  private String exceptionMessage;

  /**
   * Constructor privado para evitar la instancia de la clase.
   */
  private ErrorResolverConstants() {

  }

}
