package com.meep.tripplan.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author .
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class MsachBusinessTripplanRouterApplication {

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(MsachBusinessTripplanRouterApplication.class);
  }

}
