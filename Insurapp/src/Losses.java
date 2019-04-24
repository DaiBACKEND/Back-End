
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.google.gson.Gson;

/**
 * Servlet implementation class Losses
 */
@WebServlet({ "/losses", "/losses/*" })
public class Losses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Losses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  //funciona 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		
		try {
			String json = new Gson().toJson(ConnectionBD.SelectQuery("sinistro"));
			response.setContentType("application/json");
			response.getWriter().write(json);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//funciona 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		
		String tabela = "sinistro";
		String[] colunas = {"estado_id", "user_id", "contrato_apolice", "contrato_morada", "data_hora", "descricao", "fotos", "intervencao_autoridades", "titulo"};		
		Object[] valores = {request.getParameter("estado_id"), request.getParameter("user_id"), request.getParameter("contrato_apolice"), request.getParameter("contrato_morada"), request.getParameter("data_hora"), request.getParameter("descricao"), request.getParameter("fotos"), request.getParameter("intervencao_autoridades"), request.getParameter("titulo")};
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
		
		
		if (URLHelper.UrlContainsValues(url))
		{
			Map<String, String> valores1 = URLHelper.UrlValues(url);
		    String route = valores1.get("route");
		    
			String estado_id = valores1.get("estado_id");
			String user_id = valores1.get("user_id");
			String contrato_apolice = valores1.get("contrato_apolice");
			String contrato_morada = valores1.get("contrato_morada");
			String data_hora = valores1.get("data_hora");
			String descricao = valores1.get("descricao");
			String fotos = valores1.get("fotos");
			String intervencao_autoridades = valores1.get("intervencao_autoridades");
			
			tabela = "sinistro";
			String c[] = {"estado_id", "user_id", "contrato_apolice", "contrato_morada", "data_hora", "descricao", "fotos", "intervencao_autoridades"};
			colunas = c;
			String v[] = {estado_id, user_id, contrato_apolice, contrato_morada, data_hora, descricao, fotos, intervencao_autoridades};
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
		String tabela = "sinistro";
		String url = request.getRequestURI();
		String route = "/Insurapp/losses/";
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
