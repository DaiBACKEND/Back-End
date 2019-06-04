package com.server;

// TODO: Auto-generated Javadoc
//construtor de uma habitação

/**
 * The Class habitacao.
 */
public class habitacao {

	/** The id. */
	public String id;
	
	/** The user id. */
	public String user_id;

	/** The nome. */
	public String nome;

	
	/**
	 * Instantiates a new habitacao.
	 *
	 * @param id 
	 * @param user_id 
	 * @param nome 
	 */
	public habitacao(String id, String user_id, String nome)
	{
		this.id = id;
		
		this.user_id = user_id;
	
		this.nome = nome;
	
	}
}
