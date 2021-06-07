package com.meep.tripplan.router.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Clase que implementa las pruebas unitarias para la clase ApplicationConfig.
 * 
 * @author .
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationConfigTest {

  /**
   * Mock de la clase que contiene los metodos a probar.
   */
  @InjectMocks
  private ApplicationConfig applicationConfig;

  /**
   * Metodo para inicializar variables de las pruebas unitarias.
   */
  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);

  }

  /**
   * Ejecuta la prueba unitaria para el metodo methodValidationPostProcessor.
   * 
   * @given una clase MethodValidationPostProcessor.
   * @when se instancia la clase.
   * @then se crea el bean MethodValidationPostProcessor.
   */
  @Test
  public void methodValidationPostProcessor() {

    Assert.assertNotNull(applicationConfig.methodValidationPostProcessor());

  }

}
