package com.meep.tripplan.router.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.meep.tripplan.router.constant.ApiValues;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase de configuración para el swagger-ui.
 * 
 * @author .
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * Bean para obtener valores del archivo properties.
   */
  @Autowired
  private ApiValues apiValues;

  /**
   * Bean para escanear las APIs existentes y generar el swagger-ui.
   * 
   * @return Docket que contiene la informacion para documentar el API.
   */
  @Bean
  public Docket productApi() {
    
    return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(Boolean.FALSE)
        .select().apis(RequestHandlerSelectors.basePackage(apiValues.getBasePackage()))
        .paths(PathSelectors.any()).build().apiInfo(apiInfo());
  
  }

  /**
   * Builder para llenar la informacion general del API.
   * 
   * @return ApiInfo que contiene la informacion del API.
   */
  private ApiInfo apiInfo() {
  
    return new ApiInfoBuilder().title(apiValues.getTitle())
        .description(apiValues.getDescriptionApi()).version(apiValues.getVersion())
        .contact(new Contact(apiValues.getNameDeveloper(), apiValues.getContactUrl(),
            apiValues.getEmailDeveloper()))
        .build();
  
  }

}
