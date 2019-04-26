

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.google.gson.Gson;

/**
 * Servlet implementation class Compartments
 */
@WebServlet({ "/compartments", "/compartments/*" })
public class Compartments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Compartments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //funciona
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	response.setContentType("application/json");
    	

    	String tabela = "compartimento";
		ArrayList<String> campos = new ArrayList<String>();
		ArrayList<Object> valores_campos = new ArrayList<Object>();
		String url = request.getRequestURI();
		String route = url;
		
		Map<String, String> valores = new HashMap<String,String>();
		boolean SearchByValue = BuscarURL.UrlContainsValues(url);
		
		
			if (SearchByValue) {
				valores = BuscarURL.UrlValues(url);
			    route = valores.get("route");
			    
				for(int i = 0; i < valores.keySet().size(); i++)
				{
					if (!valores.keySet().toArray()[i].equals("route"))
					{
						campos.add((String) valores.keySet().toArray()[i]);
						valores_campos.add(valores.values().toArray()[i]);
					}
				}
			}
			try {
				if (!SearchByValue)
					response.getWriter().append((ConnectionBD.SelectQuery(tabela)));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//funciona
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		
		String tabela = "compartimento";
		String[] colunas = {"habitacao_id", "descricao", "estado"};		
		Object[] valores = {request.getParameter("habitacao_id"), request.getParameter("descricao"), request.getParameter("estado")};
		try {
			response.setContentType("application/json");
			ConnectionBD.InsertQuery(tabela, colunas, valores);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	//funciona
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
	
		String tabela = "";
		String id= "";
		String colunas[] = {};
		String valores[]= {};
		String url = request.getRequestURI();
		
		
		if (BuscarURL.UrlContainsValues(url))
		{
			Map<String, String> valores1 = BuscarURL.UrlValues(url);
		    String route = valores1.get("route");
		    
			String habitacao_id = valores1.get("habitacao_id");
			String descricao = valores1.get("descricao");
			String estado = valores1.get("estado");
			
			tabela = "compartimento";
			String c[] = {"habitacao_id", "descricao", "estado"};
			colunas = c;
			String v[] = {habitacao_id, descricao, estado};
			valores = v;
			
			id = valores1.get("id");
			try {
				 ConnectionBD.UpdateQuery(tabela, colunas, valores, id);
			 } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			 
			 e.printStackTrace();
			 }
		}
		else
		{
			System.out.println("Nenhum valor foi recebido!!");
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	//funciona
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		
		String id = "";
		String tabela = "compartimento";
		String url = request.getRequestURI();
		String route = "/Insurapp/compartments/";
		String split_url[] = url.split("/");
		response.setContentType("application/json");
		for (int i = 0; i < split_url.length; i++)
		{
			if (i < 2)
				route += "/" + split_url[i+1];
			else if (i == 3)
				id = split_url[i];
			    try {
					ConnectionBD.DeleteQuery(tabela, id);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	 private void setAccessControlHeaders(HttpServletResponse response) {
	      response.setHeader("Access-Control-Allow-Origin", "*");
	      response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
	  }
}
