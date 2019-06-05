package com.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 * Servlet implementation class Losses.
 */
@WebServlet({ "/losses", "/losses/*" })
@MultipartConfig(maxFileSize = 999999999)
public class Losses extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new losses.
     *
     * @see HttpServlet#HttpServlet()
     */
    public Losses() {
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
	 * rota get que mostra todos os sinistros da base de dados
	 */
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	String tabela = "sinistro";
    	String id = "";
		if(request.getPathInfo() == null) //verifica se o url tem algum elemento para além da rota
		{
			//se não tiver mostra todos os sinistros
			try {
					response.getWriter().append((ConnectionBD.SelectQuery(tabela)));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else {
			//se tiver mostra por id que está no url depois da rota
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
	 * rota post que cria sinistros na base de dados
	 */
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		Connection connection = null;
		String url = "jdbc:mysql://35.195.53.224:3306/insurapp?autoReconnect=true&useSSL=false";
		String username = "insurapp";
		String password = "insurappdai";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//Part file = request.getPart("fotos");
        //InputStream inputstream = null;
		
		 /*if(file != null){
    	inputstream = file.getInputStream();
    	}*/
			//buscar dados inseridos
        	 String estado_id = request.getParameter("estado_id");
        	 String descricao = request.getParameter("descricao");
             String titulo = request.getParameter("titulo");
             String user_id = request.getParameter("user_id");
             
             
             String query ="INSERT INTO sinistro (descricao, titulo, estado_id, user_id) VALUES (?, ?, ?, ?)";
             
             PreparedStatement sp = null;
      		
             try {
      			sp = connection.prepareStatement(query);
      		} catch (SQLException e1) {
      			e1.printStackTrace();
      		}
      		//inserir os dados na query
      		try {
      			sp.setString(1, descricao);
      		} catch (SQLException e3) {
      			e3.printStackTrace();
      		}
      		try {
      			sp.setString(2, titulo );
      		} catch (SQLException e2) {
      			e2.printStackTrace();
      		}
      		try {
      			sp.setString(3, estado_id);
      		} catch (SQLException e1) {
      			e1.printStackTrace();
      		}
      		try {
      			sp.setString(4, user_id);
      		} catch (SQLException e1) {
      			e1.printStackTrace();
      		}
      		try {
      			sp.executeUpdate();
      		} catch (SQLException e) {
      			e.printStackTrace();
      		}				
      		response.setContentType("application/json");
             
	}
        
        

	/**
	 * Do put.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 * rota put para o update de dados de um sinistro
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
		
		
		if (BuscarURL.UrlContainsValues(url)) //verifica se a rota tem valores
		{
			//se sim vai buscá-los
			id = valores1.get("id");
			String descricao = valores1.get("descricao");
			String titulo = valores1.get("titulo");
			String estado_id = valores1.get("estado_id");
			String user_id = valores1.get("user_id");
			
			tabela = "sinistro";
			String c[] = {"descricao", "titulo", "estado_id", "user_id"};
			colunas = c;
			String v[] = { descricao, titulo, estado_id, user_id};
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
	 * rota delete para eliminar um sinistro pelo id inserido no url
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
		String tabela = "sinistro";
		String id = request.getPathInfo().substring(1); //buscar resultado que está depois do nome da rota retirando a /
			    try {
					ConnectionBD.DeleteQuery(tabela, id);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		}

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
