package com.server;

//construtor de um sinistro

/**
 * The Class sinistro.
 */
public class sinistro {

	/** The id. */
	public String id;
	
	/** The descricao. */
	public String descricao;
	
	/** The titulo. */
	public String titulo;
	
	/** The estado_id. */
	public String estado_id;
	
	/** The user_id. */
	public String user_id;

	
	/**
	 * Instantiates a new sinistro.
	 *
	 * @param id
	 * @param descricao
	 * @param titulo
	 * @param estado_id
	 * @param user_id
	 */
	public sinistro(String id, String descricao, String titulo, String estado_id, String user_id)
	{
		this.id = id;
		
		this.descricao = descricao;
		
		this.titulo = titulo;
		
		this.estado_id = estado_id;
		
		this.user_id = user_id;
		
	}
}