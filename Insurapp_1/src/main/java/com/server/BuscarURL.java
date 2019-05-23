package com.server;
import java.util.HashMap;
import java.util.Map;

public class BuscarURL 
{
	/**
	 * 
	 * @param url Url recebido através de um pedido HTTP
	 * @return Tabela que contém os parâmetros e valores enviados pelo url, inclui também a rota original
	 */
	public static Map<String,String> UrlValues(String url)
	{
		Map<String,String> valores = new HashMap<String, String>();	
		
		String split_url[] = url.split("/");	
		String route = "";
		
	    String[] url_split = url.split("/!");
	    url_split = url_split[1].split("&");	
		
	    
	    for (int i=0; i<url_split.length; i++)
	    { 
	    	String[] url_values = url_split[i].split("=");
	    	url_values[0] = url_values[0].replaceAll("/", "");
	    	valores.put(url_values[0], url_values[1]);
	    	System.out.println(url_values[0] + ": " + url_values[1]);
	    }
	    
	    
	    for (int i=0; i < split_url.length; i++) {
			if (i < 2)
				route += "/" + split_url[i+1];
		}
	    
	    valores.put("route", route);		    
		return valores;
	}
	
	public static boolean UrlContainsValues(String url)
	{
		boolean contains_values = url.split("/!").length > 1;
		return contains_values;
	}
}
