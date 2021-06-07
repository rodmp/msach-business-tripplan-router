package com.meep.tripplan.router.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Clase pojo para representar las posibles recursos en una ruta.
 * 
 * @author .
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Resource {

  /**
   * Identificador del objeto.
   */
  private String id;

  /**
   * Codigo del recuerso.
   */
  private String name;

  /**
   * Coordenada x.
   */
  private BigDecimal x;

  /**
   * Coordenada y.
   */
  private BigDecimal y;

  /**
   * Licencia.
   */
  private String licencePlate;

  /**
   * Rango.
   */
  private Integer range;

  /**
   * Nivel de bateria.
   */
  private Integer batteryLevel;

  /**
   * Helmets.
   */
  private Integer helmets;

  /**
   * Modelo.
   */
  private String model;

  /**
   * Identificador de imagen.
   */
  private String resourceImageId;

  /**
   * Tiempo real.
   */
  private Boolean realTimeData;

  /**
   * Tipo de recurso.
   */
  private ResourceType resourceType;

  /**
   * Zona id.
   */
  private Integer companyZoneId;
}
