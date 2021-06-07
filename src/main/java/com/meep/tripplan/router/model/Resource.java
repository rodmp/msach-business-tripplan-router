package com.meep.tripplan.router.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	 * 
	 */
	private BigDecimal x;
	
	/**
	 * 
	 */
	private BigDecimal y;
	
	/**
	 * 
	 */
	private String licencePlate;
	
	/**
	 * 
	 */
	private Integer range;
	
	/**
	 * 
	 */
	private Integer batteryLevel;
	
	/**
	 * 
	 */
	private Integer helmets;
	
	/**
	 * 
	 */
	private String model;
	
	/**
	 * 
	 */
	private String resourceImageId;
	
	/**
	 * 
	 */
	private Boolean realTimeData;
	
	/**
	 * 
	 */
	private ResourceType resourceType;
	
	/**
	 * 
	 */
	private Integer companyZoneId;
}
