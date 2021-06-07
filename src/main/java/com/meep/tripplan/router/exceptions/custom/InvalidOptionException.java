package com.meep.tripplan.router.exceptions.custom;

/**
 * Clase que representa la excepcion para cuando el servicio recibe una opcion incorrecta dentro del
 * request esperado.
 * 
 * @author.
 */
public class InvalidOptionException extends RuntimeException {

  /**
   * UID autogenerado para el versionado de la clase.
   */
  private static final long serialVersionUID = 2920243542505819515L;
  
  /**
   * Constructor por defecto.
   */
  public InvalidOptionException() {
    super();
  }

  /**
   * Constructor que recibe el mensaje de error que debera mostrarse.
   * 
   * @param message mensaje de la excepcion personalizado.
   */
  public InvalidOptionException(String message) {
    super(message);
  }

  /**
   * Constructor que recibe una excepcion a ser disparada.
   * 
   * @param throwable de la excepción.
   */
  public InvalidOptionException(Throwable throwable) {
    super(throwable);
  }

}
