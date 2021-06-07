package com.meep.tripplan.router.constant;

import org.springframework.stereotype.Component;

/**
 * 
 * @author .
 *
 */
@Component
public class RedisCacheConstants {

  /**
   * Valor de la llave en redis.
   */
  public static final String KEY = "resources";
  
  /**
   * Valor de la llave en redis.
   */
  public static final String KEY_NEW = "newResources";

  /**
   * Valor de la llave en redis.
   */
  public static final String KEY_OLD = "oldResources";

  /**
   * Constructor privado.
   */
  private RedisCacheConstants() {}
}
