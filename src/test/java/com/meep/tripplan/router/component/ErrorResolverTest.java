package com.meep.tripplan.router.component;

import com.meep.tripplan.router.constant.Constants;
import com.meep.tripplan.router.constant.ErrorResolverConstants;
import com.meep.tripplan.router.constant.SpecialCharacterConstants;
import com.meep.tripplan.router.exceptions.custom.BadRequestException;
import com.meep.tripplan.router.exceptions.custom.ForbiddenException;
import com.meep.tripplan.router.exceptions.custom.InvalidOptionException;
import com.meep.tripplan.router.exceptions.custom.NoDataFoundException;
import com.meep.tripplan.router.exceptions.custom.UnauthorizedException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Clase que implementa las pruebas unitarias para la clase ErrorResolver.
 * 
 * @author .
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ErrorResolverTest {

  /**
   * Inyecta el bean ErrorResolver.
   */
  @InjectMocks
  private ErrorResolver errorResolver;

  /**
   * Mock constantes de la api.
   */
  @MockBean
  private ErrorResolverConstants errorResolverConstants;



  /**
   * Inicia las variables de la prueba.
   */
  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);

  }

  /**
   * Test para resolver la excepcion BadRequestException.
   * 
   * @given una peticion HttpServletRequest y una excepcion BadRequestException.
   * @when se recibe la excepcion BadRequestException.
   * @then se dispara dicha excepcion BadRequestException.
   */
  @Test
  public void resolveBadRequestException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    BadRequestException ex = Mockito.mock(BadRequestException.class);

    List<String> list = new ArrayList<>();

    list.add(SpecialCharacterConstants.EMPTY_STRING);

    Mockito.when(ex.getBadFields()).thenReturn(list);

    errorResolver.resolveBadRequestException(req, ex);

    Assert.assertNotNull(errorResolver.resolveBadRequestException(req, ex));

  }

  /**
   * Test para resolver la excepcion BadRequestException con campos especificos de error.
   * 
   * @given una peticion HttpServletRequest, la excepcion BadRequestException y una lista de campos
   *        erroneos.
   * @when se recibe la excepcion BadRequestException.
   * @then se dispara dicha excepcion BadRequestException.
   */
  @Test
  public void resolveBadRequestException_withBadFieldsNull() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    BadRequestException ex = Mockito.mock(BadRequestException.class);

    Mockito.when(ex.getBadFields()).thenReturn(null);

    errorResolver.resolveBadRequestException(req, ex);

    Assert.assertNull(ex.getBadFields());

  }

  /**
   * Test para resolver la excepcion ConstraintViolationException.
   * 
   * @given una peticion HttpServletRequest y la excepcion ConstraintViolationException.
   * @when se recibe la excepcion ConstraintViolationException.
   * @then se dispara dicha excepcion ConstraintViolationException.
   */
  @Test
  public void resolveConstraintViolationException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    ConstraintViolationException exception = Mockito.mock(ConstraintViolationException.class);

    errorResolver.resolveConstraintViolation(req, exception);

    Assert.assertNotNull(errorResolver.resolveConstraintViolation(req, exception));

  }


  /**
   * Test para resolver la excepcion Exception.
   * 
   * @given una peticion HttpServletRequest y la excepcion generica Exception.
   * @when se recibe una excepcion generica Exception,
   * @then se dispara dicha excepcion Exception.
   */
  @Test
  public void resolveException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    Exception ex = Mockito.mock(Exception.class);

    errorResolver.resolveException(req, ex);

    Assert.assertNotNull(errorResolver.resolveException(req, ex));

  }

  /**
   * Test para resolver la excepcion HttpMediaTypeNotAcceptableException.
   * 
   * @given una peticion HttpServletRequest y la excepcion HttpMediaTypeNotAcceptableException.
   * @when se recibe una excepcion HttpMediaTypeNotAcceptableException,
   * @then se dispara dicha excepcion HttpMediaTypeNotAcceptableException.
   */
  @Test
  public void resolveHttpMediaTypeNotAcceptableException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    HttpMediaTypeNotAcceptableException ex =
        Mockito.mock(HttpMediaTypeNotAcceptableException.class);

    errorResolver.resolveHttpMediaTypeNotAcceptableException(req, ex);

    Assert.assertNotNull(errorResolver.resolveHttpMediaTypeNotAcceptableException(req, ex));

  }

  /**
   * Test para resolver la excepcion HttpMediaTypeNotAcceptableException.
   * 
   * @given una peticion HttpServletRequest y la excepcion HttpMediaTypeNotAcceptableException.
   * @when se recibe una excepcion HttpMediaTypeNotAcceptableException,
   * @then se dispara dicha excepcion HttpMediaTypeNotAcceptableException.
   */
  @Test
  public void resolveHttpMediaTypeNotSupportedException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    HttpMediaTypeNotSupportedException ex = Mockito.mock(HttpMediaTypeNotSupportedException.class);

    errorResolver.resolveHttpMediaTypeNotSupportedException(req, ex);

    Assert.assertNotNull(errorResolver.resolveHttpMediaTypeNotSupportedException(req, ex));

  }

  /**
   * Test para resolver la excepcion HttpMessageNotReadableException.
   * 
   * @given una peticion HttpServletRequest y la excepcion HttpMessageNotReadableException.
   * @when se recibe una excepcion HttpMessageNotReadableException,
   * @then se dispara dicha excepcion HttpMessageNotReadableException.
   */
  @Test
  public void resolveHttpMessageNotReadableException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    HttpMessageNotReadableException exception = Mockito.mock(HttpMessageNotReadableException.class);

    Mockito.when(exception.getMessage())
        .thenReturn(Constants.N + SpecialCharacterConstants.SPACE_STRING + Constants.JSON_STRING
            + SpecialCharacterConstants.COLON + SpecialCharacterConstants.EMPTY_STRING);

    errorResolver.resolveHttpMessageNotReadableException(req, exception);

    Assert.assertNotNull(errorResolver.resolveHttpMessageNotReadableException(req, exception));

  }

  /**
   * Test para resolver la excepcion HttpMessageNotReadableException.
   * 
   * @given una peticion HttpServletRequest y la excepcion HttpMessageNotReadableException.
   * @when se recibe una excepcion HttpMessageNotReadableException,
   * @then se dispara dicha excepcion HttpMessageNotReadableException.
   */
  @Test
  public void resolveHttpMessageNotReadableException_withMessageNull() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    Mockito.when(errorResolverConstants.getExceptionMessage())
        .thenReturn(SpecialCharacterConstants.EMPTY_STRING);

    HttpMessageNotReadableException exception = Mockito.mock(HttpMessageNotReadableException.class);

    Mockito.when(exception.getMessage()).thenReturn(null);

    errorResolver.resolveHttpMessageNotReadableException(req, exception);

    Assert.assertNotNull(errorResolver.resolveHttpMessageNotReadableException(req, exception));

  }

  /**
   * Test para resolver la excepcion HttpRequestMethodNotSupportedException.
   * 
   * @given una peticion HttpServletRequest y la excepcion HttpRequestMethodNotSupportedException.
   * @when se recibe una excepcion HttpRequestMethodNotSupportedException,
   * @then se dispara dicha excepcion HttpRequestMethodNotSupportedException.
   */
  @Test
  public void resolveHttpRequestMethodNotSupportedException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    HttpRequestMethodNotSupportedException exception =
        Mockito.mock(HttpRequestMethodNotSupportedException.class);

    errorResolver.resolveHttpRequestMethodNotSupportedException(req, exception);

    Assert
        .assertNotNull(errorResolver.resolveHttpRequestMethodNotSupportedException(req, exception));

  }

  /**
   * Test para resolver la excepcion HystrixRuntimeException.
   * 
   * @given una peticion HttpServletRequest y la excepcion HystrixRuntimeException.
   * @when se recibe una excepcion HystrixRuntimeException,
   * @then se dispara dicha excepcion HystrixRuntimeException.
   */
  @Test
  public void resolveHystrixRuntimeException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    HystrixRuntimeException exception = Mockito.mock(HystrixRuntimeException.class);

    errorResolver.resolveHystrixRuntimeException(req, exception);

    Assert.assertNotNull(errorResolver.resolveHystrixRuntimeException(req, exception));

  }

  /**
   * Test para resolver la excepcion MethodArgumentNotValidException.
   * 
   * @given una peticion HttpServletRequest y la excepcion MethodArgumentNotValidException.
   * @when se recibe una excepcion MethodArgumentNotValidException,
   * @then se dispara dicha excepcion MethodArgumentNotValidException.
   */
  @Test
  public void resolveMethodArgumentNotValidException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    MethodArgumentNotValidException exception = Mockito.mock(MethodArgumentNotValidException.class);

    List<FieldError> fieldErrors = new ArrayList<>();

    FieldError fieldError = Mockito.mock(FieldError.class);

    fieldErrors.add(fieldError);

    BindingResult bindingResult = Mockito.mock(BindingResult.class);

    Mockito.when(exception.getBindingResult()).thenReturn(bindingResult);

    Mockito.when(exception.getBindingResult().getFieldErrors()).thenReturn(fieldErrors);

    errorResolver.resolveMethodArgumentNotValidException(req, exception);

    Assert.assertNotNull(errorResolver.resolveMethodArgumentNotValidException(req, exception));

  }

  /**
   * Test para resolver la excepcion NoHandlerFoundException.
   * 
   * @given una peticion HttpServletRequest y la excepcion NoHandlerFoundException.
   * @when se recibe una excepcion NoHandlerFoundException,
   * @then se dispara dicha excepcion NoHandlerFoundException.
   */
  @Test
  public void resolveNoHandlerFoundException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    NoHandlerFoundException exception = Mockito.mock(NoHandlerFoundException.class);

    errorResolver.resolveNoHandlerFoundException(req, exception);

    Assert.assertNotNull(errorResolver.resolveNoHandlerFoundException(req, exception));

  }


  /**
   * Test para resolver la excepcion ServletRequestBindingException.
   * 
   * @given una peticion HttpServletRequest y la excepcion ServletRequestBindingException.
   * @when se recibe una excepcion ServletRequestBindingException,
   * @then se dispara dicha excepcion ServletRequestBindingException.
   */
  @Test
  public void resolveServletRequestBindingException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    ServletRequestBindingException exception = Mockito.mock(ServletRequestBindingException.class);

    Mockito.when(exception.getMessage()).thenReturn(Constants.OK);

    errorResolver.resolveServletRequestBindingException(req, exception);

    Assert.assertNotNull(errorResolver.resolveServletRequestBindingException(req, exception));

  }

  /**
   * Test para resolver la excepcion UnauthorizedException.
   * 
   * @given una peticion HttpServletRequest y la excepcion UnauthorizedException.
   * @when se recibe una excepcion UnauthorizedException,
   * @then se dispara dicha excepcion UnauthorizedException.
   */
  @Test
  public void resolveUnAuthorizedException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    UnauthorizedException exception = Mockito.mock(UnauthorizedException.class);

    errorResolver.resolveUnAuthorizedException(req, exception);

    Assert.assertNotNull(errorResolver.resolveUnAuthorizedException(req, exception));

  }

  /**
   * Test para resolver la excepcion ForbiddenException.
   * 
   * @given una peticion HttpServletRequest y la excepcion ForbiddenException.
   * @when se recibe una excepcion ForbiddenException,
   * @then se dispara dicha excepcion ForbiddenException.
   */
  @Test
  public void resolveForbiddenException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    ForbiddenException ex = Mockito.mock(ForbiddenException.class);

    Assert.assertNotNull(errorResolver.resolveForbiddenException(req, ex));

  }

  /**
   * Test para resolver la excepcion InvalidOptionException.
   * 
   * @given una peticion HttpServletRequest y la excepcion InvalidOptionException.
   * @when se recibe una excepcion InvalidOptionException,
   * @then se dispara dicha excepcion InvalidOptionException.
   */
  @Test
  public void resolveInvalidOptionException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    InvalidOptionException ex = Mockito.mock(InvalidOptionException.class);

    Assert.assertNotNull(errorResolver.resolveInvalidOptionException(req, ex));

  }

  /**
   * Test para resolver la excepcion NoDataFoundException.
   * 
   * @given una peticion HttpServletRequest y la excepcion NoDataFoundException.
   * @when se recibe una excepcion NoDataFoundException,
   * @then se dispara dicha excepcion NoDataFoundException.
   */
  @Test
  public void resolveNoDataFoundException() {

    HttpServletRequest req = Mockito.mock(HttpServletRequest.class);

    NoDataFoundException ex = Mockito.mock(NoDataFoundException.class);

    Assert.assertNotNull(errorResolver.resolveNoDataFoundException(req, ex));

  }

}
