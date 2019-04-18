
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
 * Servlet implementation class Losses
 */
@WebServlet({ "/losses", "/losses/*" })
public class Losses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Losses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  //funciona 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String json = new Gson().toJson(ConnectionBD.SelectQuery("sinistro"));
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
		String tabela = "sinistro";
		String[] colunas = {"estado_id", "user_id", "contrato_apolice", "contrato_morada", "data_hora", "descricao", "fotos", "intervencao_autoridades", "titulo"};		
		Object[] valores = {request.getParameter("estado_id"), request.getParameter("user_id"), request.getParameter("contrato_apolice"), request.getParameter("contrato_morada"), request.getParameter("data_hora"), request.getParameter("descricao"), request.getParameter("fotos"), request.getParameter("intervencao_autoridades"), request.getParameter("titulo")};
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
		String tabela = "sinistro";
		String[] colunas = {"estado_id", "user_id", "contrato_apolice", "contrato_morada", "data_hora", "descricao", "fotos", "intervencao_autoridades"};		
		Object[] valores = {request.getParameter("estado_id"), request.getParameter("user_id"), request.getParameter("contrato_apolice"), request.getParameter("contrato_morada"), request.getParameter("data_hora"), request.getParameter("descricao"), request.getParameter("fotos"), request.getParameter("intervencao_autoridades")};
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
		String tabela = "sinistro";
		String url = request.getRequestURI();
		String route = "/Insurapp/losses/";
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
