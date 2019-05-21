package com.yousoff.rest.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * HTTP request wrapped using Spring RestTemplate approach.
 * 
 * @author yousoff
 *
 */
public class SpringRestService {
	
	/**
	 * For POST method.
	 * 
	 * @param <T>
	 * @param <E>
	 * @param url. The full qualified request URL.
	 * @param headerMap. Map object of request header.
	 * @param data. The input object for request body.
	 * @param responseType. The expected return type. Put String.class for normal json response.
	 * @return T
	 */
	public <T, E> T sendPostRequest(String url, Map<String, String> headerMap, E data, Class<T> responseType) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		if (!CollectionUtils.isEmpty(headerMap)) {
			for (Entry<String, String> e : headerMap.entrySet()) {
				headers.set(e.getKey(), e.getValue());
			}
		}
		
		HttpEntity<E> entity = new HttpEntity<E>(data, headers);
		
		try {
			T t = restTemplate.postForObject(url, entity, responseType);
			return t;
		} catch (HttpStatusCodeException e) {
			System.out.println(e.getStatusCode() + "\t" + e.getStatusText() + "\t" + e.getResponseBodyAsString());
		}
		return null;
	}
	
	/**
	 * For GET request method.
	 * 
	 * @param <T> 
	 * @param url. The full qualified request URL.
	 * @param headerMap. Map object of request header.
	 * @param uriParamMap. Map object of URI paramter.
	 * @param queryParamMap. Map object of query string parameter.
	 * @param responseType. The expected return type. Put String.class for normal json response.
	 * @return T
	 */
	public <T> T sendGetRequest(String url, Map<String, String> headerMap, Map<String, String> uriParamMap,
			Map<String, String> queryParamMap, Class<T> responseType) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		HttpHeaders headers = new HttpHeaders();

		if (!CollectionUtils.isEmpty(headerMap)) {
			for (Entry<String, String> e : headerMap.entrySet()) {
				headers.set(e.getKey(), e.getValue());
			}
		}

		if (!CollectionUtils.isEmpty(queryParamMap)) {
			for (Entry<String, String> e : queryParamMap.entrySet()) {
				builder.queryParam(e.getKey(), e.getValue());
			}
		}

		HttpEntity<T> entity = new HttpEntity<T>(headers);
		ResponseEntity<T> response;
		try {
			if (!CollectionUtils.isEmpty(uriParamMap)) {
				response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, responseType, uriParamMap);
			} else {
				response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, responseType);
			}
			return response.getBody();
		} catch (HttpStatusCodeException e) {
			System.out.println(e.getStatusCode() + "\t" + e.getStatusText() + "\t" + e.getResponseBodyAsString());
		}
		return null;
	}

}
