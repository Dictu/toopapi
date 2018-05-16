package eu.toop.node.model;

import lombok.Data;

/**
 * Represents a standard TOOP Address dataset 
 * @author Sander IJpma
 *
 */
@Data
public class Address implements DataSet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3027519256614129791L;
	private String streetName;
	private String postalCode;
	private String city;
	private String country;
}