package com.meep.tripplan.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Clase principal.
 * @author .
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class MsachBusinessTripplanRouterApplication {

  /**
   * Metodo principal.
   * @param args String[].
   */
  public static void main(String[] args) {
    SpringApplication.run(MsachBusinessTripplanRouterApplication.class);
  }

}
