package eu.toop.node.consumer;

import eu.toop.node.model.DataSet;

/**
 * Defines a simple TOOP Consumer Service.
 * @author Sander IJpma
 *
 */
public interface ConsumerService {

	/**
	 * Provides a {@link DataSet} for given countryCode and requestedId. 
	 * Implementation generally will send data 1:1 to ProviderService of given countryCode.
	 * @param countryCode
	 * @param requestedId
	 * @return
	 */
	public DataSet provide(String countryCode, String requestedId);
	
	
}
