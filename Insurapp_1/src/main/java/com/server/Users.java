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
 * Servlet implementation class Users.
 */
@WebServlet({ "/users", "/users/*"})
public class Users extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new users.
     *
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
        super();
    }
   
	/**
	 * Do get.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * rota get que mostra os users presentes na base de dados, todos quando apenas é chamada a rota /users e por id quando é chamada com o argumento adicional, em json
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	
    	String id = "";
    	String tabela = "user";
		if(request.getPathInfo() == null) //verifica se o url tem algum elemento para além da rota
		{
			//se não, mostra todos os elementos da tabela
			try {
					response.getWriter().append((ConnectionBD.SelectQuery(tabela)));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else {
			//se sim, apresenta o elemento com o id inserido
			response.setContentType("application/json");
			id = request.getPathInfo().substring(1); //buscar resultado que está depois do nome da rota retirando a /
			    try {
			    	response.getWriter().append((ConnectionBD.UserId(tabela, id)));
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		}
	
    		}



	/**
	 * Do post.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * rota post que cria um utilizador inserindo os dados necessários
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
	 * Do put.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 * rota put que dá update aos dados de um utilizador já existente
	 */
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
	
		String tabela = "";
		String id= "";
		String colunas[] = {};
		String valores[]= {};
		String url = request.getRequestURI();
		Map<String, String> valores1 = BuscarURL.UrlValues(url);
	    String route = valores1.get("route");
		
		if (BuscarURL.UrlContainsValues(url)) //verifica se o url tem valores para além da rota
		{

			//se sim vai buscá-los
			id = valores1.get("id");
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
			
			
			try {
				 ConnectionBD.UpdateQuery(tabela, colunas, valores, id);
			 } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			 
			 e.printStackTrace();
			 }
		}
		else
		{
			//se não dá um aviso que nenhum valor foi recebido
			System.out.println("Nenhum valor foi recebido!!");
		}
	}

	/**
	 * Do delete.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 * rota delete que elimina o user pelo id inserido no url
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
		String tabela = "user";
		response.setContentType("application/json");
		String id = request.getPathInfo().substring(1); //buscar resultado que está depois do nome da rota retirando a /
			    try {
					ConnectionBD.DeleteQuery(tabela, id);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		}
		//}

	 /**
		 * Sets the access control headers.
		 *
		 * @param response the new access control headers
		 */
    private void setAccessControlHeaders(HttpServletResponse response) {
	  response.setHeader("Access-Control-Allow-Origin", "*");
	  response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
	  }

	 /**
 	 * Do options.
 	 *
 	 * @param request 
 	 * @param response
 	 * @throws ServletException
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
 	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
		}
}
