package com.server;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout.
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new logout.
     *
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
    }

	/**
	 * Do post.
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *  rota post para fazer logout de um user ("elimina" os cookies do utilizador)
	 */
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		Cookie ck = new Cookie("email", "");
		ck.setMaxAge(0);
		response.addCookie(ck);
		
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
