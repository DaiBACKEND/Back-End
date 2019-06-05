package com.server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class ConnectionBD.
 */
public class ConnectionBD {

	/** The connection. */
	static Connection connection = null;
	
	/** The database name. */
	static String databaseName = "insurapp";
	
	/** The url. */
	static String url = "jdbc:mysql://35.195.53.224:3306/insurapp?autoReconnect=true&useSSL=false";
	
	/** The username. */
	static String username = "insurapp";
	
	/** The password. */
	static String password = "insurappdai";
	
	/**
	 * Select query.
	 *
	 * @param tabela
	 * @return string
	 * @throws SQLException 
	 * @throws InstantiationException
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * @throws JSONException 
	 * código para buscar todos os dados à tabela escolhida e transforma em json
	 */
	
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
			e1.printStackTrace();
		}

		return jsonInString;
		
		  }

		
	/**
	 * Insert query.
	 *
	 * @param tabela 
	 * @param colunas 
	 * @param valores 
	 * @throws SQLException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * código para inserir os dados na tabela escolhida com os dados inseridos
	 */
	
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
	
	/**
	 * Update query.
	 *
	 * @param tabela 
	 * @param colunas 
	 * @param valores 
	 * @param id 
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * código para dar update aos dados de uma tabela escolhida
	 */
	
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
	
	/**
	 * Delete query.
	 *
	 * @param tabela 
	 * @param id 
	 * @throws SQLException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * código para eliminar uma linha da tabela escolhida usando o id
	 */
	
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
	
	/**
	 * Login.
	 *
	 * @param emaill 
	 * @param passwordl 
	 * @return string
	 * @throws SQLException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * código que procura se o utilizador existe e se a sua password está correta e envia o resultado, se o utilizador não existe, se a password está errada, ou se está tudo correto devolve o id, o tipo e o email, tudo em formato json
	 */
	
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
					 s = "Utilizador Não Existe!";
					 try {
						jsonInString = mapper.writeValueAsString(s);
					} catch (JsonProcessingException e) {
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
								e.printStackTrace();
							}
					
	                } 
	                else {
	                	s = "Password Errada!";
	                	try {
							jsonInString = mapper.writeValueAsString(s);
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
	                }
				 }
				 return jsonInString;
		
	}
	
	/**
	 * User id.
	 *
	 * @param tabela
	 * @param id
	 * @return string
	 * @throws SQLException
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException
	 * código para procurar numa tabela pelo id
	 */
	
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
			e1.printStackTrace();
		}

		return jsonInString;	
	}
	
	
	/**
	 * View.
	 *
	 * @param view
	 * @param dados
	 * @return string
	 * @throws SQLException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException
	 * código para mostrar a view escolhida 
	 */
	
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
			e1.printStackTrace();
		}

		return jsonInString;	
	}
	
	/**
	 * View ID.
	 *
	 * @param view
	 * @param dados
	 * @param id
	 * @return string
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * função para ir buscar por id o elemento da view selecionada
	 */
	public static String ViewID(String view, String dados, String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		connection = DriverManager.getConnection(url, username, password);

		String query = "SELECT * FROM " + view + " WHERE id = '" + id + "'";
		
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
			e1.printStackTrace();
		}

		return jsonInString;	
	}
} 



