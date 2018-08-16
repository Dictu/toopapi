package eu.toop.node.util;

import org.apache.http.client.HttpClient;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

public abstract class WSClient<T> extends WebServiceGatewaySupport {

	private HttpClient httpClient;
	private String actionURI;
	private String toURI;
	
	public abstract T provide(String nr);
	
	public WebServiceTemplate getProxiedWebServiceTemplate() {
		WebServiceTemplate template = getWebServiceTemplate();
		HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender(getHttpClient());
		template.setMessageSender(messageSender);
		return template;
	}
	
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	public HttpClient getHttpClient() {
		return this.httpClient;
	}
	
	/**
	 * @return the actionURI
	 */
	public String getActionURI() {
		return actionURI;
	}

	/**
	 * @param actionURI the actionURI to set
	 */
	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	/**
	 * @return the toURI
	 */
	public String getToURI() {
		return toURI;
	}

	/**
	 * @param toURI the toURI to set
	 */
	public void setToURI(String toURI) {
		this.toURI = toURI;
	}
	
}
