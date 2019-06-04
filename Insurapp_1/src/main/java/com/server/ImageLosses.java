package com.server;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ImageLosses.
 */
@WebServlet("/imaloss/*")
public class ImageLosses extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new image losses.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ImageLosses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get.
	 *
	 * @param request 
	 * @param response 
	 * @throws ServletException 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * função de mostrar a imagem do sinistro pelo id inserido no url
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String dbURL = "jdbc:mysql://35.195.53.224:3306/insurapp?autoReconnect=true&useSSL=false";
	    final String dbUser = "insurapp";
	    final String dbPass = "insurappdai";

	    Connection conn = null;
	    Statement stmt = null;

	    try {
	        Class.forName("com.mysql.jdbc.Driver");

	        conn = (Connection) DriverManager.getConnection(dbURL, dbUser, dbPass);
	        stmt = (Statement) conn.createStatement();
    		String id = request.getPathInfo().substring(1);
	        ResultSet rs1;
	        rs1 = stmt.executeQuery("select fotos from sinistro where id = " + id );

	        if (rs1.next()) {
	            byte[] imgData = rs1.getBytes("fotos"); 
	            response.setHeader("expires", "0");
	            response.setContentType("image/jpg");

	            OutputStream os = response.getOutputStream(); 
	            os.write(imgData);
	            os.flush();
	            os.close();

	        }
	    } catch (SQLException ex) {
	        
	        ex.printStackTrace();
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (conn != null) {
	            
	            try {
	                conn.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	}

}