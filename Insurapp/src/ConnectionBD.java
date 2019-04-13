import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.mysql.jdbc.ResultSetMetaData;


public class ConnectionBD {

	static Connection connection = null;
	static String databaseName = "insurapp";
	static String url = "jdbc:mysql://35.195.53.224:3306/insurapp?autoReconnect=true&useSSL=false";
	
	static String username = "daiuser";
	static String password = "daiuser";
	
	public static String SelectQuery(String tabela) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		PreparedStatement sp = connection.prepareStatement("SELECT * FROM " + tabela);

		ResultSet stat = sp.executeQuery();
		ResultSetMetaData rsmd = (ResultSetMetaData) stat.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		
		String s = "";
		
		while (stat.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) s+= ",  ";
		        String columnValue = stat.getString(i);
		        s += rsmd.getColumnName(i) + " - " + columnValue;
		        s += "\n";
		    }
		    s += "\n";
		}
		if(connection != null) {
			System.out.println("Conexão feita!");
		}
		
		return s;
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
	public static void DeleteQuery(String tabela, String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		
		String query2 ="DELETE FROM " + tabela + " WHERE ";
		query2 += "(id = '" + id + "')";
				System.out.println(query2);
				
				PreparedStatement sp = connection.prepareStatement(query2);
				sp.executeUpdate();	
		
	}
}
