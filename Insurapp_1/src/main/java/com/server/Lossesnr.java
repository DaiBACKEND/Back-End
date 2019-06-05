package com.server;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Lossesnr.
 */
@WebServlet({"/lossesnr", "/lossesnr/*"})
public class Lossesnr extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new lossesnr.
     *
     * @see HttpServlet#HttpServlet()
     */
    public Lossesnr() {
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
	 * função get para mostrar os sinistros não resolvidos, todos ou por id
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	String view = "sinistronr";
    	String dados = "sinistro";
		
			
			String id = "";
	    	String tabela = "sinistronr";
			if(request.getPathInfo() == null) //verifica se o url tem algum elemento para além da rota
			{
				//se não tiver mostra todos os elementos da view
				try {
					response.getWriter().append((ConnectionBD.View(view, dados)));
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			}
			else {
				//se tiver mostra os elementos pelo id inserido no url
				response.setContentType("application/json");
				id = request.getPathInfo().substring(1);
				    try {
				    	response.getWriter().append((ConnectionBD.ViewID(tabela, dados, id)));
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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

