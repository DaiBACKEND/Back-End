

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.getWriter().append((ConnectionBD.SelectQuery("user")));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tabela = "user";
		String[] colunas = {"tipo_id", "nome", "username", "email", "password", "morada", "telemovel", "nif", "sexo", "foto", "data_nascimento", "numero_contrato"};		
		Object[] valores = {request.getParameter("tipo_id"), request.getParameter("nome"), request.getParameter("username"), request.getParameter("email"), request.getParameter("password"), request.getParameter("morada"), request.getParameter("telemovel"), request.getParameter("nif"), request.getParameter("sexo"), request.getParameter("foto"), request.getParameter("data_nascimento"), request.getParameter("numero_contrato")};
		try {
			ConnectionBD.InsertQuery(tabela, colunas, valores);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tabela = "user";
		String[] colunas = {"tipo_id", "nome", "username", "email", "password", "morada", "telemovel", "nif", "sexo", "foto", "data_nascimento", "numero_contrato"};		
		Object[] valores = {request.getParameter("tipo_id"), request.getParameter("nome"), request.getParameter("username"), request.getParameter("email"), request.getParameter("password"), request.getParameter("morada"), request.getParameter("telemovel"), request.getParameter("nif"), request.getParameter("sexo"), request.getParameter("foto"), request.getParameter("data_nascimento"), request.getParameter("numero_contrato")};
		String id = request.getParameter("id");
		try {
			ConnectionBD.UpdateQuery(tabela, colunas, valores, id);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
