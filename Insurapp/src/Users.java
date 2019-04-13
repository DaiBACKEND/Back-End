

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Users
 */
@WebServlet("/users")
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
	//est� despachado 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("application/json");
			response.getWriter().append((ConnectionBD.SelectQuery("user"))); 
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//est� despachado
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tabela = "user";
		String[] colunas = {"tipo_id", "nome", "username", "email", "password", "morada", "telemovel", "nif", "sexo", "foto", "data_nascimento", "numero_contrato"};		
		Object[] valores = {request.getParameter("tipo_id"), request.getParameter("nome"), request.getParameter("username"), request.getParameter("email"), request.getParameter("password"), request.getParameter("morada"), request.getParameter("telemovel"), request.getParameter("nif"), request.getParameter("sexo"), request.getParameter("foto"), request.getParameter("data_nascimento"), request.getParameter("numero_contrato")};
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
	//n�o funciona
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tabela = "user";
		String[] colunas = {"tipo_id", "nome", "username", "email", "password", "morada", "telemovel", "nif", "sexo", "foto", "data_nascimento", "numero_contrato"};		
		Object[] valores = {request.getParameter("tipo_id"), request.getParameter("nome"), request.getParameter("username"), request.getParameter("email"), request.getParameter("password"), request.getParameter("morada"), request.getParameter("telemovel"), request.getParameter("nif"), request.getParameter("sexo"), request.getParameter("foto"), request.getParameter("data_nascimento"), request.getParameter("numero_contrato")};
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
	//d� erro mas � est�pido n�o dar porque supostamente este c�digo funciona
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String tabela = "user";
		String id = request.getParameter("id");
		try {
			response.setContentType("application/json");
			ConnectionBD.DeleteQuery(tabela, id); 
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/
		String idcoluna = "id";
		String id = "";
		String tabela = "user";
		String url = request.getRequestURI();
		String route = "/Insurapp/users";
		String split_url[] = url.split("/");
		for (int i = 0; i < split_url.length; i++)
		{
			if (i < 2)
				route += "/" + split_url[i+1];
			else if (i == 3)
				id = split_url[i];
		}
		System.out.println(id);
	}

}
