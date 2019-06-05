package com.server;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

/**
 * Servlet implementation class Homes.
 */
@WebServlet({ "/homes", "/homes/*" })
public class Homes extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new homes.
     *
     * @see HttpServlet#HttpServlet()
     */
    public Homes() {
        super();
    }

	/**
	 * Do get.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * rota get para mostrar todas as casas presentes na base de dados
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	response.setContentType("application/json");
    	request.setCharacterEncoding("UTF-8");
    	String tabela = "habitacao";

			try {
					response.getWriter().append((ConnectionBD.SelectQuery(tabela)));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Do post.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * rota post para a criação de uma habitação
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
		String tabela = "habitacao";
		//nome das colunas
		String[] colunas = {"user_id", "nome"};		
		//buscar valores inseridos
		Object[] valores = {request.getParameter("user_id"), request.getParameter("nome")};
		try {
			response.setContentType("application/json");
			ConnectionBD.InsertQuery(tabela, colunas, valores);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Do put.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 * rota put para o update dos dados de uma casa
	 */

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
			
				String tabela = "";
				String id= "";
				String colunas[] = {};
				String valores[]= {};
				String url = request.getRequestURI();
				Map<String, String> valores1 = BuscarURL.UrlValues(url);
			    String route = valores1.get("route");
				
				
				if (BuscarURL.UrlContainsValues(url)) //verifica se a rota tem valores
				{
					//se sim vai buscá-los
					id = valores1.get("id");
					String user_id = valores1.get("user_id");
					String nome = valores1.get("nome");
					
					tabela = "habitacao";
					String c[] = {"user_id", "nome"};
					colunas = c;
					String v[] = {user_id, nome};
					valores = v;
					
					try {
						 ConnectionBD.UpdateQuery(tabela, colunas, valores, id);
					 } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					 
					 e.printStackTrace();
					 }
				}
				else
				{
					//se não dá um aviso que nenhum valor foi recebido
					System.out.println("Nenhum valor foi recebido!!");
				}
	}

	/**
	 * Do delete.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 * rota delete para eliminar uma casa pelo id inserido no url
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
		String tabela = "habitacao";
		String id = request.getPathInfo().substring(1); //buscar resultado que está depois do nome da rota retirando a /
			    try {
					ConnectionBD.DeleteQuery(tabela, id);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		}

	 /**
 	 * Sets the access control headers.
 	 *
 	 * @param response the new access control headers
 	 */
 	private void setAccessControlHeaders(HttpServletResponse response) {
	      response.setHeader("Access-Control-Allow-Origin", "*");
	      response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
	  }
	 
 	/**
	  * Do options.
	  *
	  * @param request
	  * @param response
	  * @throws ServletException
	  * @throws IOException Signals that an I/O exception has occurred.
	  */
 	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
		}
}
