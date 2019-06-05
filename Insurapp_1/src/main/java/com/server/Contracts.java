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
 * Servlet implementation class Contracts.
 */
@WebServlet({ "/contracts", "/contracts/*" })
public class Contracts extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new contracts.
     *
     * @see HttpServlet#HttpServlet()
     */
    public Contracts() {
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
	 * rota get para mostrar todos os contratos da base de dados
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json");
    	String tabela = "contrato";

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
	 * rota post para a criação de um contrato mediantes os dados inseridos
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
		String tabela = "contrato";
		//nome das colunas
		String[] colunas = {"apolice", "morada", "user_id", "descricao", "data_validade"};		
		//buscar os valores
		Object[] valores = {request.getParameter("apolice"), request.getParameter("morada"), request.getParameter("user_id"), request.getParameter("descricao"), request.getParameter("data_validade")};
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
	 * rota put para o update dos dados de um contrato já existente
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
					String apolice = valores1.get("apolice");
					String morada = valores1.get("morada");
					String user_id = valores1.get("user_id");
					String descricao = valores1.get("descricao");
					String data_validade = valores1.get("data_validade");
					
					tabela = "contrato";
					String c[] = {"apolice", "morada", "user_id", "descricao", "data_validade"};
					colunas = c;
					String v[] = {apolice, morada, user_id, descricao, data_validade};
					valores = v;
					
					id = valores1.get("id");
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
	 * rota delete para a eliminação de um contrato com o id inserido no url
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
    	request.setCharacterEncoding("UTF-8");
		String tabela = "contrato";
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
