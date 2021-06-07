package com.meep.tripplan.router.exceptions.custom;

/**
 * Clase que representa la excepcion para cuando un cliente no tiene una sesion valida y no puede
 * realizar una operacion especifica.
 * 
 * @author.
 */
public class ForbiddenException extends RuntimeException {

  /**
   * UID autogenerado para el versionado de la clase.
   */
  private static final long serialVersionUID = -2353346493876392526L;

  /**
   * Constructor por defecto.
   */
  public ForbiddenException() {
    super();
  }

  /**
   * Constructor que recibe el mensaje de error que debera mostrarse.
   * 
   * @param message mensaje de la excepcion personalizado.
   */
  public ForbiddenException(String message) {
    super(message);
  }

  /**
   * Constructor que recibe una excepcion a ser disparada.
   * 
   * @param throwable de la excepción.
   */
  public ForbiddenException(Throwable throwable) {
    super(throwable);
  }

}
