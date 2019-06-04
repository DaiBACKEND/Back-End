package com.server;

// TODO: Auto-generated Javadoc
//construtor de um sinistro

/**
 * The Class sinistro.
 */
public class sinistro {

	/** The id. */
	public String id;
	
	/** The descricao. */
	public String descricao;
	
	/** The titulos. */
	public String titulos;

	
	/**
	 * Instantiates a new sinistro.
	 *
	 * @param id 
	 * @param estado_id 
	 * @param user_id 
	 * @param contrato_apolice 
	 * @param contrato_morada 
	 * @param descricao 
	 * @param intervencao_autoridades 
	 * @param titulos 
	 */
	public sinistro(String id, String descricao, String titulos)
	{
		this.id = id;
		
		this.descricao = descricao;
		
		this.titulos = titulos;
		
	}
}