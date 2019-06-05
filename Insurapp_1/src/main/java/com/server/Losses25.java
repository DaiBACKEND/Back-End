package com.server;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Losses25.
 */
@WebServlet("/losses25")
public class Losses25 extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new losses 25.
     *
     * @see HttpServlet#HttpServlet()
     */
    public Losses25() {
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
     * rota get que mostra todos os elementos da view sinistros25 da base de dados
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	String view = "sinistro25";
    	String dados = "sinistro";
		
			try {
					response.getWriter().append((ConnectionBD.View(view, dados)));
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


