package com.av.test.controller.bonifico;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.av.test.controller.Const;
import com.av.test.controller.custom.CustomHttpClient;
import com.av.test.controller.custom.IURIFormatter;
import com.av.test.controller.custom.RequestWrapper;

import javafx.util.Pair;

/**
 * Servlet implementation class inviaBonifico
 */
@WebServlet(description = "servlet di invio bonifico", urlPatterns = { "/CreateSCTOrder.do" })
public class inviaBonifico extends HttpServlet implements IURIFormatter
{
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public inviaBonifico() {
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("inviaBonifico.jsp");
		
		try {
		
			// recupero tutti i campi del form dalla request
			JSONObject json = new JSONObject();
			for(String key:request.getParameterMap().keySet())
				json.put(key, request.getParameterMap().get(key));    

			// invoco il mio httpclient
			CustomHttpClient customHttpClient = new CustomHttpClient(Const.POST);			
			String returnedValue = customHttpClient.callClientAction(getURI(), json.toString(), new Pair<String, String>(Const.AUTH_SCHEMA, Const.AUTH_SCHEMA_VAL));

			// parsing del risultato
		    JSONObject respJson = new JSONObject(returnedValue);
		    JSONObject status = respJson.getJSONObject("status");
		    
		    // aggiunta dei parametri aggiuntivi alla mappa originale
		    Map<String, String[]> extraParams = new HashMap<String, String[]>();
		    extraParams.put("risultatoBonifico", new String[] {status.getString("code")});
		    extraParams.put("descrizioneRisultatoBonifico", new String[] {status.getString("description")});
		    
		    // wrappo la request con la nuova mappa prima del forward
		    HttpServletRequest wrappedRequest = new RequestWrapper(request, extraParams);
		    view.forward(wrappedRequest, response);
		    
		    
		} catch (Exception ex) {
		    response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
		    
		} finally {
		}
	}

	// URI associata alla action
	@Override
	public String getURI()
	{
		return Const.API_BONIFICO.replaceAll("accountId", String.valueOf(getAccountId()));
	}

	// dato interno all'applicazione
	@Override
	public Long getAccountId()
	{
		return 1L;
	}
}
