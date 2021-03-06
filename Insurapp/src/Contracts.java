

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
 * Servlet implementation class Contracts
 */
@WebServlet({ "/contracts", "/contracts/*" })
public class Contracts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contracts() {
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

    	String tabela = "contrato";

			try {
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
		
		String tabela = "contrato";
		String[] colunas = {"apolice", "morada", "user_id", "descricao", "data_validade"};		
		Object[] valores = {request.getParameter("apolice"), request.getParameter("morada"), request.getParameter("user_id"), request.getParameter("descricao"), request.getParameter("data_validade")};
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
				    
					String apolice = valores1.get("apolice");
					String morada = valores1.get("morada");
					String user_id = valores1.get("user_id");
					String descricao = valores1.get("descricao");
					String data_validade = valores1.get("data_validade");
					
					tabela = "contrato";
					String c[] = {"apolice", "morada", "user_id", "descricao", "data_validade"};
					colunas = c;
					String v[] = {apolice, morada, user_id, descricao, data_validade};
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
		String tabela = "contrato";
		response.setContentType("application/json");
		id = request.getPathInfo().substring(1);
			    try {
					ConnectionBD.DeleteQuery(tabela, id);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	 
private void setAccessControlHeaders(HttpServletResponse response) {
	      response.setHeader("Access-Control-Allow-Origin", "*");
	      response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
	  }
	
}
