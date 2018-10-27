package com.av.test.controller.custom;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.av.test.controller.Const;

import javafx.util.Pair;



/**
 * @author alberto.verrini
 * factory che utilizza le classi di httpclient di Apache per eseguire chiamate GET e POST opportunamente formattate
 */
public class CustomHttpClient
{
	
	private String action = "";
	
	public CustomHttpClient(String action)
	{
		this.action = action;
	}
	
	public String callClientAction(String URI, String jsonEntity, Pair<String, String> header) throws Exception
	{
		HttpResponse resp = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
			
			switch (action)
				{
				case Const.POST:		// per le chiamate post ci sono jsonEntity da impostare
				    HttpPost postrequest = new HttpPost(URI);
				    StringEntity params = new StringEntity(jsonEntity);
				    if(header!=null)
				    	postrequest.addHeader(header.getKey(), header.getValue());
				    
				    postrequest.setEntity(params);
				    resp = httpClient.execute(postrequest);
				    return EntityUtils.toString(resp.getEntity());
					
				case Const.GET:			// per le chiamate GET 
				default:
					HttpGet getrequest = new HttpGet(URI);
				    if(header!=null)
				    	getrequest.addHeader(header.getKey(), header.getValue());
				    
					resp = httpClient.execute(getrequest);
				    return EntityUtils.toString(resp.getEntity());
				}
		
		} catch (Exception ex) {
		    throw ex;					// gestirà il chiamante
		    
		} finally {
		    httpClient.close();
		}
	
	}

}
