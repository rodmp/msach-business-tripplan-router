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
   * 
   */
  public static final String KEY = "resources";
  
  /**
   * 
   */
  public static final String KEY_NEW = "newResources";

  /**
   * 
   */
  public static final String KEY_OLD = "oldResources";

  /**
   * Constructor privado.
   */
  private RedisCacheConstants() {}
}
