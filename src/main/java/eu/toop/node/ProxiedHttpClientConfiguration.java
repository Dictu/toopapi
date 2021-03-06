package eu.toop.node;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProxiedHttpClientConfiguration {
	
	/**
	 * From System property (or application.properties). Set on commandline using -Dhttps.proxyHost=
	 */
	@Value("${https.proxyHost}")
	private String proxyHost;
	
	/**
	 * From System property (or application.properties). Set on commandline using -Dhttps.proxyPort=
	 */
	@Value("${https.proxyPort}")
	private String proxyPort;
	
	/**
	 * From System property (or application.properties). Set on commandline using -Dhttps.nonProxyHosts= using '|' as separator between hosts.
	 */
	@Value("${http.nonProxyHosts}")
	private String nonProxyHosts;
	
	@Bean
	public HttpHost proxy() {
		if (null != proxyHost && !proxyHost.isEmpty()) {
			return new HttpHost(proxyHost, Integer.parseInt(proxyPort));
		}
		else {
			return null;
		}
	}
	
	@Bean
	public List<String> nonProxyHosts() {
		return  Arrays.asList(nonProxyHosts.split("\\|"));
	}
	
	@Bean
	public RestTemplate localRestTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(localHttpClient());
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}
	
	@Bean
	public RestTemplate restTemplate() 
	                throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
	    HttpComponentsClientHttpRequestFactory requestFactory =
	                    new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient());
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    return restTemplate;
	 }
	
	@Bean
	public TrustStrategy acceptingTrustStrategy() {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		return acceptingTrustStrategy;
	}
	
	@Bean
	public HttpClient httpClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(acceptingTrustStrategy())
                .build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
	    CloseableHttpClient httpClient = HttpClients.custom()
	    				.setProxy(proxy())
	                    .setSSLSocketFactory(csf)
	                    .build();
	    return httpClient;
	}
	
	@Bean
	public HttpClient localHttpClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(acceptingTrustStrategy())
                .build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
	    CloseableHttpClient httpClient = HttpClients.custom()
	                    .setSSLSocketFactory(csf)
	                    .build();
	    return httpClient;
	}

	
}
