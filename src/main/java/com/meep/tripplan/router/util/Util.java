package com.meep.tripplan.router.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meep.tripplan.router.constant.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de utilerias que se requieran agregar funcionalidades especificas.
 * 
 * @author Nova Solution Systems.
 * @since 01.01.2020.
 */
@Slf4j
public class Util {

  /**
   * Convierte un objeto a un string con formato json.
   * 
   * @param object objeto a ser convertido.
   * @return cadena que representa el objeto serializado en formato json.
   */
  public static String getJson(Object object) {

    String jsonString = null;

    try {

      jsonString = new ObjectMapper().writeValueAsString(object);

    } catch (JsonProcessingException ex) {

      log.error(ex.getMessage(), ex);

    }

    return jsonString;

  }

  /**
   * Convierte una cadena de formato json a un Map.
   * 
   * @param jsonString con la cadena en formato json que sera convertida.
   * @return Map que contiene los datos del json en formato clave-valor.
   */
  public static Map<String, String> convertStringToMap(String jsonString) {

    Map<String, String> dataMap = new HashMap<>();

    try {

      ObjectMapper objectMapper = new ObjectMapper();

      dataMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {});

    } catch (IOException ex) {

      log.error(ex.getMessage(), ex);

    }

    return dataMap;

  }


  /**
   * Método para imprimir los headers de la petición http.
   * 
   * @param request objeto de la peticion http.
   */
  public static void printHeaders(HttpServletRequest request) {

    Enumeration<String> headerNames = request.getHeaderNames();

    if (Objects.nonNull(headerNames)) {

      while (headerNames.hasMoreElements()) {

        String key = headerNames.nextElement();

        log.info(Constants.MSG_TO_LOG_HEADER, key, request.getHeader(key));

      }

    }

  }

  /**
   * Constructor privado para evitar que la clase sea instanciada.
   */
  private Util() {

  }

}
