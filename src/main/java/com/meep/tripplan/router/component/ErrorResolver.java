package com.meep.tripplan.router.component;

import com.meep.tripplan.router.constant.Constants;
import com.meep.tripplan.router.constant.ErrorResolverConstants;
import com.meep.tripplan.router.constant.SpecialCharacterConstants;
import com.meep.tripplan.router.exceptions.ErrorResponse;
import com.meep.tripplan.router.exceptions.ErrorType;
import com.meep.tripplan.router.exceptions.custom.BadRequestException;
import com.meep.tripplan.router.exceptions.custom.ForbiddenException;
import com.meep.tripplan.router.exceptions.custom.InvalidOptionException;
import com.meep.tripplan.router.exceptions.custom.NoDataFoundException;
import com.meep.tripplan.router.exceptions.custom.UnauthorizedException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Clase que intercepta las excepciones y maneja los mensajes y codigos http correspondientes.
 * 
 * @author .
 */
@RestControllerAdvice
@Slf4j
public class ErrorResolver {

  /**
   * Bean de constantes de errores definidos en el yaml.
   */
  @Autowired
  private ErrorResolverConstants errorResolverConstants;

  /**
   * Método para logear el detalle de la excepción.
   */
  private static void writeToLog(ErrorResponse errorResponse, Exception exception) {

    log.error(Constants.ERROR_TYPE, errorResponse.getType());
    log.error(Constants.ERROR_CODE, errorResponse.getCode());
    log.error(Constants.ERROR_DETAILS, errorResponse.getDetails());
    log.error(Constants.ERROR_LOCATION, errorResponse.getLocation());
    log.error(Constants.ERROR_MORE_INFORMATION, errorResponse.getMoreInfo());

    String message =
        Objects.isNull(exception) ? SpecialCharacterConstants.EMPTY_STRING : exception.getMessage();

    log.error(message, exception);

  }

  /**
   * Metodo para manejar una excepcion de tipo BadRequestException.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida BadRequestException.
   * @return errorResponse Objeto de respuesta específica para BadRequestException.
   */
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveBadRequestException(HttpServletRequest req, BadRequestException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    List<String> badFields = ex.getBadFields();

    String moreinfo = null;

    if (Objects.nonNull(badFields) && !badFields.isEmpty()) {
      moreinfo = String.join(SpecialCharacterConstants.COMMA_SEPARATOR, badFields);
    }

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getBadRequestException());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setMoreInfo(moreinfo);
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link UnauthorizedException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida UnauthorizedException.
   * @return errorResponse Objeto de respuesta específica para UnauthorizedException.
   */
  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  public ErrorResponse resolveUnAuthorizedException(HttpServletRequest req,
      UnauthorizedException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.ERROR.name());
    errorResponse.setCode(errorResolverConstants.getUnauthorizedException());
    errorResponse.setDetails(errorResolverConstants.getUnauthorizedExceptionMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link NoHandlerFoundException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida UnauthorizedException.
   * @return errorResponse Objeto de respuesta específica para NoHandlerFoundException.
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorResponse resolveNoHandlerFoundException(HttpServletRequest req,
      NoHandlerFoundException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.ERROR.name());
    errorResponse.setCode(errorResolverConstants.getNoHandlerFoundException());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link HttpRequestMethodNotSupportedException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida UnauthorizedException.
   * @return errorResponse Objeto de respuesta específica para
   *         HttpRequestMethodNotSupportedException.
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveHttpRequestMethodNotSupportedException(HttpServletRequest req,
      HttpRequestMethodNotSupportedException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getHttpRequestMethodNotSupportedException());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link HttpMediaTypeNotAcceptableException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida HttpMediaTypeNotAcceptableException.
   * @return errorResponse Objeto de respuesta específica para HttpMediaTypeNotAcceptableException.
   */
  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveHttpMediaTypeNotAcceptableException(HttpServletRequest req,
      HttpMediaTypeNotAcceptableException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getHttpMediaTypeNotAcceptableException());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link HttpMediaTypeNotSupportedException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida HttpMediaTypeNotAcceptableException.
   * @return errorResponse Objeto de respuesta específica para HttpMediaTypeNotSupportedException.
   */
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveHttpMediaTypeNotSupportedException(HttpServletRequest req,
      HttpMediaTypeNotSupportedException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getHttpMediaTypeNotSupportedException());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link ServletRequestBindingException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida ServletRequestBindingException.
   * @return errorResponse Objeto de respuesta específica para ServletRequestBindingException.
   */
  @ExceptionHandler(ServletRequestBindingException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveServletRequestBindingException(HttpServletRequest req,
      ServletRequestBindingException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.ERROR.name());
    errorResponse.setCode(errorResolverConstants.getServletRequestBindingException());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setTimestamp(ZonedDateTime.now());
    errorResponse.setUuid(req.getHeader(Constants.UUID));

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link HttpMessageNotReadableException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida HttpMessageNotReadableException.
   * @return errorResponse Objeto de respuesta específica para HttpMessageNotReadableException.
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveHttpMessageNotReadableException(HttpServletRequest req,
      HttpMessageNotReadableException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    String message = Objects.isNull(ex) ? SpecialCharacterConstants.EMPTY_STRING : ex.getMessage();
    message = Objects.isNull(message) ? SpecialCharacterConstants.EMPTY_STRING : message;
    int index = message.indexOf(SpecialCharacterConstants.COLON);
    message = (index != SpecialCharacterConstants.INT_NEGATIVE_ONE)
        ? message.substring(SpecialCharacterConstants.INT_ZERO_VALUE, index)
        : errorResolverConstants.getBadRequestException();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getHttpMessageNotReadableException());
    errorResponse.setDetails(message);
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link MethodArgumentNotValidException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida MethodArgumentNotValidException.
   * @return errorResponse Objeto de respuesta específica para MethodArgumentNotValidException.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveMethodArgumentNotValidException(HttpServletRequest req,
      MethodArgumentNotValidException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getMethodArgumentNotValidException());

    Map<String, List<String>> groupedErrors = new HashMap<>();

    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    List<String> fields = new ArrayList<>();

    for (FieldError fieldError : fieldErrors) {
      String message = fieldError.getDefaultMessage();
      String field = fieldError.getField();
      groupedErrors.computeIfAbsent(message, key -> Collections.singletonList(field));
      fields.add(field);
    }

    if (!groupedErrors.isEmpty()) {
      errorResponse.setDetails(groupedErrors.toString());
    }

    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setMoreInfo(fields.toString());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link ConstraintViolationException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida ConstraintViolationException.
   * @return errorResponse Objeto de respuesta específica para ConstraintViolationException.
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveConstraintViolation(HttpServletRequest req,
      ConstraintViolationException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getConstraintViolationException());

    List<String> violationMessages = new ArrayList<>();

    ex.getConstraintViolations()
        .forEach(violation -> violationMessages.add(violation.getMessage()));

    errorResponse
        .setDetails(String.join(SpecialCharacterConstants.COMMA_SEPARATOR, violationMessages));
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link Exception}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida Exception.
   * @return errorResponse Objeto de respuesta específica para Exception.
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse resolveException(HttpServletRequest req, Exception ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.FATAL.name());
    errorResponse.setCode(errorResolverConstants.getException());
    errorResponse.setDetails(errorResolverConstants.getExceptionMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link HystrixRuntimeException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida HystrixRuntimeException.
   * @return errorResponse Objeto de respuesta específica para HystrixRuntimeException.
   */
  @ExceptionHandler(HystrixRuntimeException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse resolveHystrixRuntimeException(HttpServletRequest req,
      HystrixRuntimeException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.ERROR.name());
    errorResponse.setCode(errorResolverConstants.getHystrixRuntimeException());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Metodo para manejar una excepcion de tipo {@link ForbiddenException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida ForbiddenException.
   * @return errorResponse Objeto de respuesta específica para ForbiddenException.
   */
  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ErrorResponse resolveForbiddenException(HttpServletRequest req, Exception ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.ERROR.name());
    errorResponse.setCode(errorResolverConstants.getForbiddenException());
    errorResponse.setDetails(errorResolverConstants.getForbiddenExceptionMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    ErrorResolver.writeToLog(errorResponse, ex);

    return errorResponse;

  }

  /**
   * Handler para manejar la excepcion {@link InvalidOptionException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida InvalidOptionException.
   * @return errorResponse Objeto de respuesta específica para InvalidOptionException.
   */
  @ExceptionHandler(InvalidOptionException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveInvalidOptionException(HttpServletRequest req,
      InvalidOptionException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.INVALID.name());
    errorResponse.setCode(errorResolverConstants.getBadRequestException());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    return errorResponse;

  }

  /**
   * Handler para manejar la excepcion {@link NoDataFoundException}.
   *
   * @param req Objeto Http Servlet de petición.
   * @param ex Excepción recibida NoDataFoundException.
   * @return errorResponse Objeto de respuesta específica para NoDataFoundException.
   */
  @ExceptionHandler(NoDataFoundException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveNoDataFoundException(HttpServletRequest req,
      NoDataFoundException ex) {

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setType(ErrorType.ERROR.name());
    errorResponse.setCode(errorResolverConstants.getNoDataFoundException());
    errorResponse.setDetails(ex.getMessage());
    errorResponse.setLocation(req.getRequestURI());
    errorResponse.setUuid(req.getHeader(Constants.UUID));
    errorResponse.setTimestamp(ZonedDateTime.now());

    return errorResponse;

  }

}
