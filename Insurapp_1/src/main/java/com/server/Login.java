package com.server;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Login.
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new login.
     *
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do post.
	 *
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * função de login, onde são inseridos os dados e é respondido em json o que está errado ou se está correto os dados necessários
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		String emaill = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			response.setContentType("application/json");
			if(ConnectionBD.Login(emaill, password).substring(0,1).contentEquals("[")) {
				Cookie ck = new Cookie ("email", emaill);
				ck.setMaxAge(30);
				ck.setSecure(true);
				response.addCookie(ck);
			}		
			 
				try {
						response.getWriter().append((ConnectionBD.Login(emaill, password)));
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		} catch (InstantiationException | ClassNotFoundException | SQLException | IllegalAccessException e) {
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
