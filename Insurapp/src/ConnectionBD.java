import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.Map;


//import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;
//import com.google.gson.JsonElement;
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
		ResultSet resultSet = sp.executeQuery();
		ResultSetMetaData md = (ResultSetMetaData) resultSet.getMetaData();
		String json = null;
		int columns = md.getColumnCount();
	    List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

	    while (resultSet.next()) {
	        HashMap<String,Object> row = new HashMap<String, Object>(columns);
	        for(int i=1; i<=columns; ++i) {
	            row.put(md.getColumnName(i),resultSet.getObject(i));
	        }
	        list.add(row);
	        System.out.println(list);
	        json = new Gson().toJson(list);
	    }
	    
	    return json;
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
	//ainda dá erro
	public static void UpdateQuery(String tabela, String[] colunas, Object[] valores, String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		String query1 ="UPDATE " + tabela + " SET ";
		
				for (int i = 0,  j = 0; i < colunas.length && j < valores.length;  j++ , i++)
				{
					query1 += colunas[i] + " = " + "'" + valores[j].toString() + "' ,";
					
					if (i != colunas.length-1 && j != valores.length-1)
					{
						query1 += colunas[i] + " = " + "'" + valores[j].toString() + "' ,";
						
					}
					else
					{
						query1 += " WHERE (id = " + "'" + id + "')";
					}
				}
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
	public static String Login(String usernamel, String passwordl) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		String query3 ="SELECT password FROM user WHERE username = '" + usernamel + "'";
		String query2 ="SELECT * FROM user WHERE username = '" + usernamel + "' AND password = '" + passwordl + "'";
				
				System.out.println(query2);
				System.out.println(query3);
				PreparedStatement sp = connection.prepareStatement(query2);
				PreparedStatement sp1 = connection.prepareStatement(query3);
				 ResultSet rs = sp.executeQuery();
				 ResultSet rs1 = sp1.executeQuery();
				 rs.next();
				 rs1.next();
				 String pass = rs1.getString("password");
				 String s= "";
				 
					 if(BCrypt.checkpw(passwordl, pass)) {
						    s = "Dados de Login corretos!";
					 	    System.out.println(s);
					     
	                } 
	                else {
	                    s = "Dados de Login incorretos!";
	                    System.out.println(s);
	                }
				 
				 return s;
		
	}
	public static String UserTipoID(String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		PreparedStatement sp = connection.prepareStatement("SELECT * FROM user WHERE tipo_id = '" + id + "'");
		ResultSet resultSet = sp.executeQuery();
		ResultSetMetaData md = (ResultSetMetaData) resultSet.getMetaData();
		String json = null;
		int columns = md.getColumnCount();
	    List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

	    while (resultSet.next()) {
	        HashMap<String,Object> row = new HashMap<String, Object>(columns);
	        for(int i=1; i<=columns; ++i) {
	            row.put(md.getColumnName(i),resultSet.getObject(i));
	        }
	        list.add(row);
	        System.out.println(list);
	        json = new Gson().toJson(list);
	    }
	    
	    return json;
		  }
	}

