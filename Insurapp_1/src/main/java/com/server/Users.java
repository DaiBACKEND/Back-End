package com.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;



/**
 * Servlet implementation class Users
 */
@WebServlet({ "/users", "/users/*"})
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * quando o url apenas contem /users mostra todos e quando contem /users/* mostra pelo id do tipo inserido no url no *
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	
    	String id = "";
    	String tabela = "user";
		if(request.getPathInfo() == null)
		{
			
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
		else {
			
			response.setContentType("application/json");
			id = request.getPathInfo().substring(1);
			    try {
			    	response.getWriter().append((ConnectionBD.UserId(tabela, id)));
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
    		}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * função que cria um utilizador inserindo os dados necessários
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
		
		String tabela = "user";
		String[] colunas = {"tipo_id", "nome", "email", "password", "morada", "contacto", "nif", "sexo", "data_nascimento", "numero_contrato", "cidade", "pais", "codigo_postal"};		
		String password = request.getParameter("password");
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		Object[] valores = {request.getParameter("tipo_id"), request.getParameter("nome"), request.getParameter("email"), hashed , request.getParameter("morada"), request.getParameter("contacto"), request.getParameter("nif"), request.getParameter("sexo"), request.getParameter("data_nascimento"), request.getParameter("numero_contrato"), request.getParameter("cidade"), request.getParameter("pais"), request.getParameter("codigo_postal")};
		try {
			response.setContentType("application/json");
			ConnectionBD.InsertQuery(tabela, colunas, valores);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 * função que dá update aos dados de um utilizador já existente
	 */
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
	
		String tabela = "";
		String id= "";
		String colunas[] = {};
		String valores[]= {};
		String url = request.getRequestURI();
		
		
		if (BuscarURL.UrlContainsValues(url))
		{
			Map<String, String> valores1 = BuscarURL.UrlValues(url);
		    String route = valores1.get("route");
		    
			String tipo_id = valores1.get("tipo_id");
			String nome = valores1.get("nome");
			String email = valores1.get("email");
			String pass = valores1.get("password");
			String password = BCrypt.hashpw(pass, BCrypt.gensalt());
			String morada = valores1.get("morada");
			String contacto = valores1.get("contacto");
			String nif = valores1.get("nif");
			String sexo = valores1.get("sexo");
			String data_nascimento = valores1.get("data_nascimento");
			String numero_contrato = valores1.get("numero_contrato");
			String cidade = valores1.get("cidade");
			String pais = valores1.get("pais");
			String codigo_postal = valores1.get("codigo_postal");
			
			tabela = "user";
			String c[] = {"tipo_id", "nome", "email", "password", "morada", "contacto", "nif", "sexo", "data_nascimento", "numero_contrato", "cidade", "pais", "codigo_postal"};
			colunas = c;
			String v[] = {tipo_id, nome, email, password, morada, contacto, nif, sexo, data_nascimento, numero_contrato, cidade, pais, codigo_postal};
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
	 * função que elimina o user pelo id inserido no url
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
		
		String id = "";
		String tabela = "user";
		response.setContentType("application/json");
		id = request.getPathInfo().substring(1);
			    try {
					ConnectionBD.DeleteQuery(tabela, id);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		//}

	 private void setAccessControlHeaders(HttpServletResponse response) {
	      response.setHeader("Access-Control-Allow-Origin", "*");
	      response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
	  }

	 protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
		}
}
