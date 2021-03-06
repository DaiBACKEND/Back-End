import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.json.JSONException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.ResultSetMetaData;

public class ConnectionBD {

	static Connection connection = null;
	static String databaseName = "insurapp";
	static String url = "jdbc:mysql://35.195.53.224:3306/insurapp?autoReconnect=true&useSSL=false";
	
	static String username = "insurapp";
	static String password = "insurappdai";
	
	public static String SelectQuery(String tabela) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		connection = DriverManager.getConnection(url, username, password);

		PreparedStatement sp = connection.prepareStatement("SELECT * FROM " + tabela);

		ResultSet stat = sp.executeQuery();

		ArrayList<Object> object_list = new ArrayList<Object>();
	
		while (stat.next()) 
		{
			object_list.add(BuscarValores.getValores(tabela, stat));
		}
		
		if(connection != null) {

			System.out.println(sp);
			connection.close();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = "";
		
		try {
			jsonInString = mapper.writeValueAsString(object_list);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return jsonInString;
		
		  }

		
	public static void InsertQuery(String tabela, String[] colunas, Object[] valores) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		String query ="INSERT INTO " + tabela + " (";

		for (int i = 0; i < colunas.length; i++)
		{
			query += colunas[i];
			
			if (i != colunas.length-1)
			{
				query += ",";
			}
			else
			{
				query += ") VALUES (";
			}
		}
		
		for (int i = 0; i < valores.length; i++)
		{
			query += "'" + valores[i].toString() + "'";
			
			if (i != valores.length-1)
			{
				query += ",";
			}
			else
			{
				query += ")";
			}
		}
		
		System.out.println(query);
		
		PreparedStatement sp = connection.prepareStatement(query);
		sp.executeUpdate();		
	}
	//funciona
	public static void UpdateQuery(String tabela, String[] colunas, Object[] valores, String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		String query1 ="UPDATE " + tabela + " SET ";
		
		for (int i = 0; i < colunas.length; i++) {
			
			query1 += colunas[i] + " = '" + valores[i] + "'";			
			
			if (i != colunas.length-1)
			{
				query1 += ", ";
			}
		}
		
		query1 += " WHERE id = " + id; 
				System.out.println(query1);
				
				PreparedStatement sp = connection.prepareStatement(query1);
				sp.executeUpdate();	
		
	}
	//funciona
	public static void DeleteQuery(String tabela, String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		String query2 ="DELETE FROM " + tabela + " WHERE (id = ";
		query2 += "'" + id + "')";
				System.out.println(query2);
				
				PreparedStatement sp = connection.prepareStatement(query2);
				sp.executeUpdate();	
		
	}
	//falta implementar sessions
	public static String Login(String emaill, String passwordl) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		ArrayList<Object> object_list = new ArrayList<Object>();
		String query3 ="SELECT password FROM user WHERE email = '" + emaill + "'";
		 PreparedStatement sp1 = connection.prepareStatement(query3);
		 ResultSet rs1 = sp1.executeQuery();
				
		String jsonInString = "";
		String s= "";
	    ObjectMapper mapper = new ObjectMapper();
				 
				 if(!rs1.next()) {
					 s = "Utilizador Errado!";
					 try {
						jsonInString = mapper.writeValueAsString(s);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 } 
				 else {

					 String pass = rs1.getString("password");
					 rs1.next();
					 String query2 ="SELECT id, tipo_id, email FROM user WHERE email = '" + emaill + "' AND password = '" + pass + "'";
					 PreparedStatement sp = connection.prepareStatement(query2);
					 ResultSet rs = sp.executeQuery();
					 while (rs.next()) 
						{
							object_list.add(BuscarValores.getValores("logins", rs));
						}
					 
				 if(connection != null) {

						connection.close();
					}
	
					 if(BCrypt.checkpw(passwordl, pass)) {
						 	try {
								jsonInString = mapper.writeValueAsString(object_list);
							} catch (JsonProcessingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
	                } 
	                else {
	                	s = "Password Errada!";
	                	try {
							jsonInString = mapper.writeValueAsString(s);
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
				 }
				 return jsonInString;
		
	}
	
	public static String UserId(String tabela, String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		connection = DriverManager.getConnection(url, username, password);

		String query = "SELECT * FROM " + tabela + " WHERE id = '" + id + "'";

		
		PreparedStatement sp = connection.prepareStatement(query);

		ResultSet stat = sp.executeQuery();

		ArrayList<Object> object_list = new ArrayList<Object>();
	
		while (stat.next()) 
		{
			object_list.add(BuscarValores.getValores(tabela, stat));
		}
		
		if(connection != null) {

			System.out.println("");
			connection.close();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = "";
		
		try {
			jsonInString = mapper.writeValueAsString(object_list);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return jsonInString;	
	}


	
	public static String View(String view, String dados) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		connection = DriverManager.getConnection(url, username, password);

		String query = "SELECT * FROM " + view + "";
		
		PreparedStatement sp = connection.prepareStatement(query);

		ResultSet stat = sp.executeQuery();

		ArrayList<Object> object_list = new ArrayList<Object>();
	
		while (stat.next()) 
		{
			object_list.add(BuscarValores.getValores(dados, stat));
		}
		
		if(connection != null) {

			System.out.println("");
			connection.close();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = "";
		
		try {
			jsonInString = mapper.writeValueAsString(object_list);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return jsonInString;	
	}
}



