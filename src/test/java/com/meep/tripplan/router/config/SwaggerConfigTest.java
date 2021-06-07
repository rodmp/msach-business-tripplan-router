package com.meep.tripplan.router.config;

import com.meep.tripplan.router.constant.ApiValues;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import springfox.documentation.spring.web.plugins.Docket;

/**
 * Clase que implementa las pruebas unitarias para la clase SwaggerConfig.
 * 
 * @author .
 */
@RunWith(MockitoJUnitRunner.class)
public class SwaggerConfigTest {

  /**
   * Mock del componente a ser validado.
   */
  @InjectMocks
  private SwaggerConfig swaggerConfig;

  /**
   * Variable que contiene valores externalizados.
   */
  @Mock
  private ApiValues apiValues;
  
  /**
   * Mock para la clase de documentacion.
   */
  @Mock
  private Docket docket;
  
  /**
   * Ejecuta la prueba unitaria para el metodo productApi.
   * 
   * @given a Bean docker.
   * @when el productApi es invocado,
   * @then un nuevo Docket con la informacion de la api se añade a la informacion
   */
  @Test
  public void productApi() {
    
    Mockito.when(apiValues.getBasePackage()).thenReturn(StringUtils.EMPTY);

    swaggerConfig.productApi();
    
    Mockito.verify(apiValues, Mockito.times(1)).getBasePackage();

  }

}
