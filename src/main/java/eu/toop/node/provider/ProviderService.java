package eu.toop.node.provider;

import eu.toop.node.model.DataSet;

/**
 * Defines a simple TOOP Provider Service.
 * @author Sander IJpma
 *
 */
public interface ProviderService {
	
	/**
	 * Provides a dataset for given requestedId. 
	 * Implementations will generally send given data 1:1 to country specific providerservice
	 * Result of country specific providerservice needs to be translated to TOOP {@link DataSet}
	 * @param requestedId
	 * @return
	 */
	public DataSet provide(String requestedId);
}
