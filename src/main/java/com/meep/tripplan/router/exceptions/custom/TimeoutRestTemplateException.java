package com.meep.tripplan.router.exceptions.custom;

/**
 * Clase que representa la excepcion para cuando el servicio corta la peticion debido a una espera
 * demasiado larga de alguna servicio externo invocado.
 * 
 * @author.
 */
public class TimeoutRestTemplateException extends RuntimeException {

  /**
   * UID autogenerado para el versionado de la clase.
   */
  private static final long serialVersionUID = 4278975578613243889L;

  /**
   * Constructor por defecto.
   */
  public TimeoutRestTemplateException() {
    super();
  }

  /**
   * Constructor que recibe el mensaje de error que debera mostrarse.
   * 
   * @param message mensaje de la excepcion personalizado.
   */
  public TimeoutRestTemplateException(String message) {
    super(message);
  }

  /**
   * Constructor que recibe una excepcion a ser disparada.
   * 
   * @param throwable de la excepción.
   */
  public TimeoutRestTemplateException(Throwable throwable) {
    super(throwable);
  }

}
