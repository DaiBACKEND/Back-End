package com.server;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class BuscarURL.
 */
public class BuscarURL 
{
	
	/**
	 * URLValues.
	 *
	 * @param url url recebido
	 * @return Map que tem os parâmetros e valores inseridos no url
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public static Map<String,String> UrlValues(String url) throws UnsupportedEncodingException
	{
		Map<String,String> valores = new HashMap<String, String>();	
		
		String split_url[] = url.split("/");	
		String route = "";
		
	    String[] url_split = url.split("/!");	
	    System.out.println(url_split[1]);
	    url_split = url_split[1].split("&");
	    
	    for (int i=0; i<url_split.length; i++)
	    { 
	    	String[] url_values = url_split[i].split("=");
	    	url_values[0] = url_values[0].replaceAll("/", "");
	    	
	    	if(url_values[0].contentEquals("email"))
    		{
	    		valores.put(url_values[0], url_values[1]);
    		} 
    	else 
    		{
    		valores.put(url_values[0], URLDecoder.decode(url_values[1], "UTF-8"));
    		//URLEncoder.encode(url_values[1], "UTF-8")
	    	System.out.println(url_values[0] + ": " + url_values[1]);
    		}
    	
    }
	    
	    
	    for (int i=0; i < split_url.length; i++) {
			if (i < 2)
				route += "/" + split_url[i+1];
		}
	    
	    valores.put("route", route);		    
		return valores;
	}
	
	/**
	 * Url contains values.
	 *
	 * @param url 
	 * @return true, if successful
	 */
	public static boolean UrlContainsValues(String url)
	{
		boolean contains_values = url.split("/!").length > 1;
		return contains_values;
	}
}
