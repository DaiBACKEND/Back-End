
import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//import java.util.ArrayList;
//import java.security.SecureRandom;
import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Users
 */
@WebServlet({ "/users", "/users/*"})
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//funciona, quando apenas /users mostra todos e quando /users/* mostra pelo id do tipo inserido no url
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	setAccessControlHeaders(response);
    	response.setContentType("application/json");
    	
    	String id = "";
    	String tabela = "user";
		ArrayList<String> campos = new ArrayList<String>();
		ArrayList<Object> valores_campos = new ArrayList<Object>();
		String url = request.getRequestURI();
		String route = url;
		
		Map<String, String> valores = new HashMap<String,String>();
		boolean SearchByValue = BuscarURL.UrlContainsValues(url);
		
		if(request.getPathInfo() == null)
		{
			if (SearchByValue) {
				valores = BuscarURL.UrlValues(url);
			    route = valores.get("route");
			    
				for(int i = 0; i < valores.keySet().size(); i++)
				{
					if (!valores.keySet().toArray()[i].equals("route"))
					{
						campos.add((String) valores.keySet().toArray()[i]);
						valores_campos.add(valores.values().toArray()[i]);
					}
				}
			}
			try {
				if (!SearchByValue)
					response.getWriter().append((ConnectionBD.SelectQuery(tabela)));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
			
			//String split_url[] = url.split("/");
			response.setContentType("application/json");
			id = request.getPathInfo().substring(1);
			//id = split_url[3];
			    try {
			    	response.getWriter().append((ConnectionBD.UserId(tabela, id)));
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
    		}




	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//funciona
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		
		String tabela = "user";
		String[] colunas = {"tipo_id", "nome", "username", "email", "password", "morada", "contacto", "nif", "sexo", "data_nascimento", "numero_contrato", "cidade", "pais", "codigo_postal"};		
		String password = request.getParameter("password");
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		Object[] valores = {request.getParameter("tipo_id"), request.getParameter("nome"), request.getParameter("username"), request.getParameter("email"), hashed , request.getParameter("morada"), request.getParameter("contacto"), request.getParameter("nif"), request.getParameter("sexo"), request.getParameter("data_nascimento"), request.getParameter("numero_contrato"), request.getParameter("cidade"), request.getParameter("pais"), request.getParameter("codigo_postal")};
		try {
			response.setContentType("application/json");
			ConnectionBD.InsertQuery(tabela, colunas, valores);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	//funciona
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
	
		String tabela = "";
		String id= "";
		String colunas[] = {};
		String valores[]= {};
		String url = request.getRequestURI();
		
		
		if (BuscarURL.UrlContainsValues(url))
		{
			Map<String, String> valores1 = BuscarURL.UrlValues(url);
		    String route = valores1.get("route");
		    
			String tipo_id = valores1.get("tipo_id");
			String nome = valores1.get("nome");
			String username = valores1.get("username");
			String email = valores1.get("email");
			String pass = valores1.get("password");
			String password = BCrypt.hashpw(pass, BCrypt.gensalt());
			String morada = valores1.get("morada");
			String contacto = valores1.get("contacto");
			String nif = valores1.get("nif");
			String sexo = valores1.get("sexo");
			String data_nascimento = valores1.get("data_nascimento");
			String numero_contrato = valores1.get("numero_contrato");
			String cidade = valores1.get("cidade");
			String pais = valores1.get("pais");
			String codigo_postal = valores1.get("codigo_postal");
			
			tabela = "user";
			String c[] = {"tipo_id", "nome", "username", "email", "password", "morada", "contacto", "nif", "sexo", "data_nascimento", "numero_contrato", "cidade", "pais", "codigo_postal"};
			colunas = c;
			String v[] = {tipo_id, nome, username, email, password, morada, contacto, nif, sexo, data_nascimento, numero_contrato, cidade, pais, codigo_postal};
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
		
		String id = "";
		String tabela = "user";
		String url = request.getRequestURI();
		String route = "/Insurapp/users/";
		String split_url[] = url.split("/");
		response.setContentType("application/json");
		for (int i = 0; i < split_url.length; i++)
		{
			if (i < 2)
				route += "/" + split_url[i+1];
			else if (i == 3)
				id = split_url[i];
			    try {
					ConnectionBD.DeleteQuery(tabela, id);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		}

	 private void setAccessControlHeaders(HttpServletResponse response) {
	      response.setHeader("Access-Control-Allow-Origin", "*");
	      response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
	  }

}
