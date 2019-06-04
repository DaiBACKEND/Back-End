package com.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import com.mysql.jdbc.ResultSetMetaData;

// TODO: Auto-generated Javadoc
/**
 * The Class BuscarValores.
 */
public class BuscarValores {

	/**
	 * Gets the valores.
	 *
	 * @param tabela 
	 * @param resultset 
	 * @return valores
	 * @throws SQLException
	 */
	public static Object getValores(String tabela, ResultSet resultset) throws SQLException
	{
		Object o = new Object();
		Map<String,String> valores = new HashMap<String, String>();
		ResultSetMetaData rsmd = (ResultSetMetaData) resultset.getMetaData();
		
		int columnsNumber = rsmd.getColumnCount();
		
		for (int i = 1; i <= columnsNumber; i++)
		{
			valores.put(rsmd.getColumnName(i), resultset.getString(i));
		}
		
		switch (tabela)
		{
		case "user":
			o = new user(valores.get("id"), valores.get("tipo_id"), valores.get("nome"), valores.get("email"), valores.get("password"), valores.get("morada"), valores.get("contacto"), valores.get("nif"), valores.get("sexo"), valores.get("data_nascimento"), valores.get("numero_contrato"), valores.get("cidade"), valores.get("pais"), valores.get("codigo_postal"));
			break;
			
		case "sinistro":
			o = new sinistro(valores.get("id"), valores.get("descricao"), valores.get("titulo"));
			break;
			
		case "habitacao":
			o = new habitacao(valores.get("id"), valores.get("user_id"), valores.get("nome"));
			break;
		
		case "contrato":
			o = new contrato(valores.get("id"), valores.get("apolice"), valores.get("morada"), valores.get("user_id"), valores.get("descricao"), valores.get("data_validade"));
			break;
			
		case "compartimento":
			o = new compartimento(valores.get("id"), valores.get("habitacao_id"), valores.get("descricao"), valores.get("estado"));
			break;
		
		case "user_compartimento":
			o = new user_compartimento(valores.get("nome"), valores.get("nomeDaHab"), valores.get("descomp"));
			break;
			
		case "email":
			o = new email(valores.get("emails"));
			break;
		
		case "logins":
			o = new logins(valores.get("id"), valores.get("tipo_id"), valores.get("email"));
			break;
		}
		return o;
	}
}
