package com.meep.tripplan.router.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import com.meep.tripplan.router.constant.ApiValues;
import com.meep.tripplan.router.constant.SpecialCharacterConstants;

/**
 * Clase de prueba para WebMvcConfig class.
 * 
 * @author .
 */
@RunWith(MockitoJUnitRunner.class)
public class WebMvcConfigTest {

  /**
   * Mock de la clase que contiene los metodos a probar.
   */
  @InjectMocks
  private WebMvcConfig webMvcConfig;

  /**
   * Mock de la clase que contiene valores externalizadas.
   */
  @Mock
  private ApiValues apiValues;

  /**
   * Metodo para inicializar variables de las pruebas unitarias.
   */
  @Before
  public void setUp() {
    
    MockitoAnnotations.initMocks(this);
  
  }

  /**
   * Ejecuta el test para el metodo configureContentNegotiation.
   * 
   * @given una clase de configuracion ContentNegotiationConfigurer. 
   * @when el metodo configureContentNegotiation es invocado.
   * @then se valida que se haya registrado la estrategia de negociacion.
   */
  @Test
  public void configureContentNegotiation() {

    ContentNegotiationConfigurer configurer = Mockito.mock(ContentNegotiationConfigurer.class);

    Mockito.when(configurer.favorPathExtension(Boolean.TRUE)).thenReturn(configurer);
    Mockito.when(configurer.ignoreAcceptHeader(Boolean.TRUE)).thenReturn(configurer);
    
    webMvcConfig.configureContentNegotiation(configurer);

    Mockito.verify(configurer, Mockito.times(1)).favorPathExtension(Boolean.TRUE);
    Mockito.verify(configurer, Mockito.times(1)).ignoreAcceptHeader(Boolean.TRUE);

  }

  /**
   * Test para webConfig ignora accept header.
   * 
   * @given ContentNegotiationConfigurer y WebMvcConfig,
   * @when el metodo configureContentNegotiation es invocado,
   * @then Añade al ContentNegotiationConfigurer.
   */
  @Test
  public void testResourceHandlers() {

    Mockito.when(apiValues.getLabel()).thenReturn(SpecialCharacterConstants.EMPTY_STRING);
    Mockito.when(apiValues.getResourceLocation())
        .thenReturn(SpecialCharacterConstants.EMPTY_STRING);
    Mockito.when(apiValues.getWebjars()).thenReturn(SpecialCharacterConstants.EMPTY_STRING);
    Mockito.when(apiValues.getWebjarsLocation()).thenReturn(SpecialCharacterConstants.EMPTY_STRING);

    ResourceHandlerRegistry registry = Mockito.mock(ResourceHandlerRegistry.class);
    
    ResourceHandlerRegistration resourceHandlerRegistration =
        Mockito.mock(ResourceHandlerRegistration.class);

    Mockito.when(registry.addResourceHandler(ArgumentMatchers.anyString()))
        .thenReturn(resourceHandlerRegistration);

    webMvcConfig.addResourceHandlers(registry);

    Assert.assertNotNull(registry);

  }

}
