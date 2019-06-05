package com.server;
//construtor de um compartimento

/**
 * The Class compartimento.
 */
public class compartimento {
	
	/** The id. */
	public String id;
	
	/** The habitacao id. */
	public String habitacao_id;

	/** The descricao. */
	public String descricao;

	/** The estado. */
	public String estado;

	
	/**
	 * Instantiates a new compartimento.
	 *
	 * @param id
	 * @param habitacao_id
	 * @param descricao
	 * @param estado
	 */
	public compartimento(String id, String habitacao_id, String descricao, String estado)
	{
		this.id = id;
		
		this.habitacao_id = habitacao_id;
	
		this.descricao = descricao;
	
		this.estado = estado;
	
	}
}
