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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mysql.jdbc.ResultSetMetaData;

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
    	response.setContentType("application/json");
		Connection connection = null;
    	String databaseName = "insurapp";
    	String url = "jdbc:mysql://35.195.53.224:3306/insurapp?autoReconnect=true&useSSL=false";
    	String username = "insurapp";
    	String password = "insurappdai";
    	Object o = new Object();
		int columnsNumber = 0;
		ResultSet stat = null;
		
		Map<String,String> valores = new HashMap<String, String>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PreparedStatement sp = null;
		try {
			sp = connection.prepareStatement("SELECT id, estado_id, user_id, contrato_apolice, contrato_morada, data_hora, descricao ,intervencao_autoridades, titulo FROM sinistro");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			ResultSetMetaData rsmd = (ResultSetMetaData) sp.getMetaData();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			stat = sp.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			columnsNumber =  sp.getMetaData().getColumnCount();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//byte[] imageBytes;
		//Image image;
		//String tabela = "sinistro";
		ArrayList<Object> object_list = new ArrayList<Object>();
		try {
			while (stat.next()) 
			{
				for (int i = 1; i <= columnsNumber; i++)
				{
					try {
						valores.put(sp.getMetaData().getColumnName(i),  stat.getString(i));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//imageBytes = stat.getBytes(7);
				//InputStream in = new ByteArrayInputStream(imageBytes);
				//BufferedImage bImageFromConvert = ImageIO.read(in);
				o = new sinistro(valores.get("id"), valores.get("estado_id"), valores.get("user_id"), valores.get("contrato_apolice"), valores.get("contrato_morada"), valores.get("data_hora"), valores.get("descricao") , valores.get("intervencao_autoridades"), valores.get("titulo"));
				System.out.println(valores);
				object_list.add(valores);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if(connection != null) {
			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = "";
		
		try {
			jsonInString = mapper.writeValueAsString(object_list);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		response.getWriter().append(jsonInString);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//funciona 
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
		Part file = request.getPart("fotos");
        InputStream inputstream = null;
        String estado_id = request.getParameter("estado_id");
        String user_id = request.getParameter("user_id");
        String contrato_apolice = request.getParameter("contrato_apolice");
        String contrato_morada = request.getParameter("contrato_morada");
        String data_hora = request.getParameter("data_hora");
        String descricao = request.getParameter("descricao");
        String intervencao_autoridades = request.getParameter("intervencao_autoridades");
        String titulo = request.getParameter("titulo");
        
        if(file != null){
        	inputstream = file.getInputStream();
        }
		String query ="INSERT INTO sinistro (estado_id, user_id, contrato_apolice, contrato_morada, data_hora, descricao, fotos, intervencao_autoridades, titulo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement sp = null;
		try {
			sp = connection.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sp.setString(1, estado_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sp.setString(2, user_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sp.setString(3, contrato_apolice);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sp.setString(4, contrato_morada);
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		try {
			sp.setString(5, data_hora);
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		try {
			sp.setString(6, descricao);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			sp.setBlob(7, inputstream);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			sp.setString(8, intervencao_autoridades);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			sp.setString(9, titulo);
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
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	//funciona
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

	 private void setAccessControlHeaders(HttpServletResponse response) {
	      response.setHeader("Access-Control-Allow-Origin", "*");
	      response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
	  }
	 protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
		}
}
