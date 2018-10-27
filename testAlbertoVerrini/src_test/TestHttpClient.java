import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.av.test.controller.Const;
import com.av.test.controller.custom.CustomHttpClient;

import javafx.util.Pair;

class TestHttpClient
{

	// Test della chiamata GET all'URI di getBalance con tutti i parametri corretti	: così si ottiene uno status OK
	@Test
	void testHttpClientGetOK()
	{
		CustomHttpClient customHttpClient = new CustomHttpClient(Const.GET);	
		
		String URI = Const.API_ESTRATTOCONTO.replaceAll("accountId", "1");
		
		try {
			
		    String returnedValue = customHttpClient.callClientAction(URI,null, new Pair<String, String>(Const.AUTH_SCHEMA, Const.AUTH_SCHEMA_VAL));
		    JSONObject respJson = new JSONObject(returnedValue);
		    String status = respJson.getString("status");
	
		    assertTrue("OK".equals(status));
		
		
		} catch (Exception ex) {
			assertTrue(false);
		    
		} finally {
		}
		
	}

	// Test della chiamata GET all'URI di getBalance in cui manca il parametro di Schema sul header: così si ottiene uno status KO
	@Test
	void testHttpClientGetKO()
	{
		CustomHttpClient customHttpClient = new CustomHttpClient(Const.GET);	
		
		String URI = Const.API_ESTRATTOCONTO.replaceAll("accountId", "1");
		
		try {
			
		    String returnedValue = customHttpClient.callClientAction(URI,null, null);
		    JSONObject respJson = new JSONObject(returnedValue);
		    String status = respJson.getString("status");
	
		    assertTrue("KO".equals(status));
		
		
		} catch (Exception ex) {
			assertTrue(false);
		    
		} finally {
		}
		
	}

	// Test della chiamata POST all'URI di sctOrder con tutti i parametri corretti: così si ottiene uno status OK
	@Test
	void testHttpClientPostOK()
	{
		JSONObject json = new JSONObject();
		CustomHttpClient customHttpClient = new CustomHttpClient(Const.POST);	
		
		String URI = Const.API_BONIFICO.replaceAll("accountId", "1");
		
		try {
			
		    String returnedValue = customHttpClient.callClientAction(URI, json.toString(), new Pair<String, String>(Const.AUTH_SCHEMA, Const.AUTH_SCHEMA_VAL));
		    JSONObject respJson = new JSONObject(returnedValue);
		    JSONObject status = respJson.getJSONObject("status");
	
		    assertTrue("OK".equals(status.getString("code")));
		
		
		} catch (Exception ex) {
			assertTrue(false);
		    
		} finally {
		}
		
	}
	
	// Test della chiamata POST all'URI di sctOrder con tutti i parametri corretti: così si ottiene uno status KO
	@Test
	void testHttpClientPostKO()
	{
		JSONObject json = new JSONObject();
		CustomHttpClient customHttpClient = new CustomHttpClient(Const.POST);	
		
		String URI = Const.API_BONIFICO.replaceAll("accountId", "1");
		
		try {
			
		    String returnedValue = customHttpClient.callClientAction(URI, json.toString(), null);
		    JSONObject respJson = new JSONObject(returnedValue);
		    JSONObject status = respJson.getJSONObject("status");
	
		    assertTrue("KO".equals(status.getString("code")));
		
		} catch (Exception ex) {
			assertTrue(false);
		    
		} finally {
		}
		
	}

}
