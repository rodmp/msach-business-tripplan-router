package com.meep.tripplan.router.exceptions.custom;

/**
 * Clase que representa la excepcion para cuando un cliente no esta autorizado para realizar alguna
 * operacion especifica.
 * 
 * @author .
 */
public class UnauthorizedException extends RuntimeException {

  /**
   * UID autogenerado para el versionado de la clase.
   */
  private static final long serialVersionUID = -2122252302133789399L;

  /**
   * Constructor por defecto.
   */
  public UnauthorizedException() {
    super();
  }

  /**
   * Constructor que recibe el mensaje de error que debera mostrarse.
   * 
   * @param message mensaje de la excepcion personalizado.
   */
  public UnauthorizedException(String message) {
    super(message);
  }

  /**
   * Constructor que recibe una excepcion a ser disparada.
   * 
   * @param throwable de la excepción.
   */
  public UnauthorizedException(Throwable throwable) {
    super(throwable);
  }

}
