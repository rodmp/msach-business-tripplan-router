package com.meep.tripplan.router.exceptions.custom;

/**
 * Clase que maneja las excepciones del manejo de la cache.
 * @author .
 *
 */
public class RedisOperationException extends RuntimeException {

  /**
   * UID autogenerado para el versionado de la clase.
   */
  private static final long serialVersionUID = 8925303792177335247L;


  /**
   * Constructor para inizializar la lista de campos incorrectos.
   * 
   * @param message mensaje de excepción arrojada por bad request.
   */
  public RedisOperationException(String message) {
    super(message);
  }

}
