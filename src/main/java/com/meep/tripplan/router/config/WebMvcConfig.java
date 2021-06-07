package com.meep.tripplan.router.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.meep.tripplan.router.constant.ApiValues;

/**
 * Clase de configuración para el contexto web mvc.
 * 
 * @author .
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  /**
   * Bean de constantes obtenidas del archivo properties.
   */
  @Autowired
  private ApiValues apiValues;

  /**
   * Método de auto-configuración para ignorar el Media-Type.
   *
   * @param configurer clase para configurar la estrategia de manejo del Media-Type.
   */
  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    configurer.favorPathExtension(Boolean.TRUE).ignoreAcceptHeader(Boolean.TRUE);

  }

  /**
   * Metodo para agregar recursos estaticos.
   * 
   * @param registry objeto que registra los recursos.
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    registry.addResourceHandler(apiValues.getLabel())
        .addResourceLocations(apiValues.getResourceLocation());

    registry.addResourceHandler(apiValues.getWebjars())
        .addResourceLocations(apiValues.getWebjarsLocation());

    WebMvcConfigurer.super.addResourceHandlers(registry);

  }

}
