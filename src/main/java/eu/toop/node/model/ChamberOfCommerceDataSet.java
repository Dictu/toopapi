package eu.toop.node.model;

import lombok.Data;

/**
 * Represents a standard TOOP Chamber Of Commercer dataset 
 * @author Sander IJpma
 *
 */
@Data
public class ChamberOfCommerceDataSet implements DataSet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2372389912472179297L;
	
	/**
	 * The unique official company identifier, NL:	KvK-nummer
	 */
	private String companyCode;
	
	/**
	 * 	The official current company name as stored in the business register, NL: Handelsnaam
	 */
	private String companyName;
	
	/**
	 * The legal type of the company as part of the CompanyTypeScheme, NL: Rechtspersoon
	 */
	private String companyType;
	
	/**
	 * The legal status of the company (active, ended,...), NL: RechtsvormRechtspersoon
	 */
	private String legalStatus;
	
	/**
	 * The date at which the current legal status became effective, NL: StatusDatum
	 */
	private String legalStatusEffectiveDate;
	
	/**
	 * The reference to the official register that has registered the company, NL: KamerVanKoophandel
	 */
	private String registrationAuthority;
	
	/**
	 * The date at which the company registered at the registration authority, NL: RegistratieDatum
	 */
	private String registrationDate;	
	
	/**
	 * The number of the company as recorded on the register. In some jurisdictions, the number on the register may be different from the official company number.	not applicable!
	 */
	private String registrationNumber;
	
	/**
	 * The description of the main activity of the company as free text description recorded on the registry. This tag should be used to give a description of the company activity in the local language.	NL: Activiteit
	 */
	private String activityDeclaration;
	
	/**
	 * The postal address and/or the visiting address of a physical site of the company, NL: Locatie
	 */
	private Address headOfficeAddres;
	
	
}
