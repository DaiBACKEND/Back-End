

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.google.gson.Gson;

/**
 * Servlet implementation class Homes
 */
@WebServlet({ "/homes", "/homes/*" })
public class Homes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Homes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //funciona
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String json = new Gson().toJson(ConnectionBD.SelectQuery("habitacao"));
			response.setContentType("application/json");
			response.getWriter().write(json);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//funciona
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tabela = "habitacao";
		String[] colunas = {"user_id", "nome"};		
		Object[] valores = {request.getParameter("user_id"), request.getParameter("nome")};
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
		String tabela = "habitacao";
		String[] colunas = {"user_id", "nome"};
		Object[] valores = {request.getParameter("user_id"), request.getParameter("nome")};
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
		String tabela = "habitacao";
		String url = request.getRequestURI();
		String route = "/Insurapp/homes/";
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
