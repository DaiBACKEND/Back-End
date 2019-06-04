package com.server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.ResultSetMetaData;

// TODO: Auto-generated Javadoc
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get.
	 *
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * função que mostra todos os sinistros da base de dados
	 */
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	String tabela = "sinistro";
    	
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
	 * Do post.
	 *
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * função que cria sinistros na base de dados
	 */
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		Connection connection = null;
		String databaseName = "insurapp";
		String url = "jdbc:mysql://35.195.53.224:3306/insurapp?autoReconnect=true&useSSL=false";
		String username = "insurapp";
		String password = "insurappdai";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Part file = request.getPart("fotos");
        //InputStream inputstream = null;
        String descricao = request.getParameter("descricao");
        String titulo = request.getParameter("titulo");
        
        /*if(file != null){
        	inputstream = file.getInputStream();
        }*/
		String query ="INSERT INTO sinistro (descricao, titulo) VALUES (?, ?)";
		
		PreparedStatement sp = null;
		try {
			sp = connection.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			sp.setString(1, descricao);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		/*try {
			sp.setBlob(2, inputstream);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		
		try {
			sp.setString(2, titulo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sp.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	 * função que dá update de dados de um sinistro
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
		
		
		if (BuscarURL.UrlContainsValues(url))
		{

			id = valores1.get("id");
			String descricao = valores1.get("descricao");
			String titulo = valores1.get("titulo");
			
			tabela = "sinistro";
			String c[] = {"descricao", "titulo"};
			colunas = c;
			String v[] = { descricao, titulo};
			valores = v;
			
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
	 * Do delete.
	 *
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 * função que dá delete a um sinistro pelo id inserido no url
	 */
	//funciona
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");

		String id = "";
		String tabela = "sinistro";
		id = request.getPathInfo().substring(1);
			    try {
					ConnectionBD.DeleteQuery(tabela, id);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
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
