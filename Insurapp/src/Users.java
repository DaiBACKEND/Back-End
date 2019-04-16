
import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet({ "/users", "/users/*", "/user/*" })
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
	//funciona (falta ver como fazer para selecionar os users pelo tipo
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				String json = new Gson().toJson(ConnectionBD.SelectQuery("user"));
				response.setContentType("application/json");
				response.getWriter().write(json);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException
					| JSONException e1) {
				e1.printStackTrace();
			}
			
    		
			//código de ver users pelo tipo, ver como pôr a funcionar aqui neste get
			/*String idcoluna = "id";
			String id = "";
			String tabela = "user";
			String route = "/Insurapp/users/";
			String url = request.getRequestURI();
			String split_url[] = url.split("/");
			response.setContentType("application/json");
			for (int i = 0; i < split_url.length; i++)
			{
				if (i < 2)
					route += "/" + split_url[i+1];
				else if (i == 3)
					id = split_url[i];
				    try {
						ConnectionBD.UserTipoID(id);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
    	}
		*/
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//funciona
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	//não funciona
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tabela = "user";
		String[] colunas = {"tipo_id", "nome", "username", "email", "password", "morada", "telemovel", "nif", "sexo", "foto", "data_nascimento", "numero_contrato"};
		String password = request.getParameter("password");
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		Object[] valores = {request.getParameter("tipo_id"), request.getParameter("nome"), request.getParameter("username"), request.getParameter("email"), hashed, request.getParameter("morada"), request.getParameter("telemovel"), request.getParameter("nif"), request.getParameter("sexo"), request.getParameter("foto"), request.getParameter("data_nascimento"), request.getParameter("numero_contrato")};
		String id = request.getParameter("id");
		try {
			response.setContentType("application/json");
			ConnectionBD.UpdateQuery(tabela, colunas, valores, id);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	//funciona
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idcoluna = "id";
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


}
