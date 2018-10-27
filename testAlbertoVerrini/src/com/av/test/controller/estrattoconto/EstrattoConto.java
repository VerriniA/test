package com.av.test.controller.estrattoconto;

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
 * Servlet implementation class EstrattoConto
 */
@WebServlet(description = "servlet che richiede un estratto conto", urlPatterns = { "/Balance.do" })
public class EstrattoConto extends HttpServlet implements IURIFormatter
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EstrattoConto() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher view = request.getRequestDispatcher("estrattoConto.jsp");
		
		try {
		
			// invoco il mio httpclient
			CustomHttpClient customHttpClient = new CustomHttpClient(Const.GET);
		    
			// parsing del risultato
		    String returnedValue = customHttpClient.callClientAction(getURI(), null, new Pair<String, String>(Const.AUTH_SCHEMA, Const.AUTH_SCHEMA_VAL)); 
		    JSONObject respJson = new JSONObject(returnedValue);
		    String status = respJson.getString("status");
		    JSONObject payload = respJson.getJSONObject("payload");
		    
		    // aggiunta dei parametri aggiuntivi alla mappa originale
		    Map<String, String[]> extraParams = new HashMap<String, String[]>();
		    extraParams.put("amount", new String[] {payload.getString("balance")});
		    extraParams.put("risultatoEC", new String[] {status});
		    
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
		return Const.API_ESTRATTOCONTO.replaceAll("accountId", String.valueOf(getAccountId()));
	}

	// dato interno all'applicazione
	@Override
	public Long getAccountId()
	{
		return 2L;
	}

}
