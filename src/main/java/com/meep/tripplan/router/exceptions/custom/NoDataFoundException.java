package com.meep.tripplan.router.exceptions.custom;

/**
 * Clase que representa la excepcion para cuando el servicio corta la peticion debido a una espera
 * demasiado larga de alguna servicio externo invocado.
 * 
 * @author.
 */
public class NoDataFoundException extends RuntimeException {

  /**
   * UID autogenerado para el versionado de la clase.
   */
  private static final long serialVersionUID = 5141264074683480037L;
  
  /**
   * Constructor por defecto.
   */
  public NoDataFoundException() {
    super();
  }
  
  /**
   * Constructor que recibe el mensaje de error que debera mostrarse.
   * 
   * @param message mensaje de la excepcion personalizado.
   */
  public NoDataFoundException(String message) {
    super(message);
  }

}
