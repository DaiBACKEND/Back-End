package com.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import com.mysql.jdbc.ResultSetMetaData;

/**
 * The Class BuscarValores.
 */
public class BuscarValores {

	/**
	 * Gets the valores.
	 *
	 * @param tabela da base de dados
	 * @param resultset
	 * @return valores dessa tabela
	 * @throws SQLException the SQL exception
	 */
	public static Object getValores(String tabela, ResultSet resultset) throws SQLException
	{
		Object ob = new Object();
		Map<String,String> valores = new HashMap<String, String>();
		ResultSetMetaData rsmd = (ResultSetMetaData) resultset.getMetaData(); //resultado da query especificada
		
		int columnsNumber = rsmd.getColumnCount(); //número de colunas que a query devolveu
		
		for (int i = 1; i <= columnsNumber; i++)
		{
			valores.put(rsmd.getColumnName(i), resultset.getString(i)); //insere no map valores o nome da coluna i e o seu valor
		}
		
		switch (tabela)
		{
		case "user":
			ob = new user(valores.get("id"), valores.get("tipo_id"), valores.get("nome"), valores.get("email"), valores.get("password"), valores.get("morada"), valores.get("contacto"), valores.get("nif"), valores.get("sexo"), valores.get("data_nascimento"), valores.get("numero_contrato"), valores.get("cidade"), valores.get("pais"), valores.get("codigo_postal"));
			break;
			
		case "sinistro":
			ob = new sinistro(valores.get("id"), valores.get("descricao"), valores.get("titulo"), valores.get("estado_id"), valores.get("user_id"));
			break;
			
		case "habitacao":
			ob = new habitacao(valores.get("id"), valores.get("user_id"), valores.get("nome"));
			break;
		
		case "contrato":
			ob = new contrato(valores.get("id"), valores.get("apolice"), valores.get("morada"), valores.get("user_id"), valores.get("descricao"), valores.get("data_validade"));
			break;
			
		case "compartimento":
			ob = new compartimento(valores.get("id"), valores.get("habitacao_id"), valores.get("descricao"), valores.get("estado"));
			break;
		
		case "user_compartimento":
			ob = new user_compartimento(valores.get("nome"), valores.get("nomeDaHab"), valores.get("descomp"));
			break;
			
		case "email":
			ob = new email(valores.get("emails"));
			break;
		
		case "logins":
			ob = new logins(valores.get("id"), valores.get("tipo_id"), valores.get("email"));
			break;
			
		case "validar":
			ob = new validar(valores.get("email"), valores.get("password"));
			break;
		}
		return ob;
	}
}
