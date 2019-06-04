package com.server;

// TODO: Auto-generated Javadoc
//construtor de um contrato

/**
 * The Class contrato.
 */
public class contrato {

	/** The id. */
	public String id;
	
	/** The apolice. */
	public String apolice;

	/** The morada. */
	public String morada;

	/** The user id. */
	public String user_id;

	/** The descricao. */
	public String descricao;

	/** The data validade. */
	public String data_validade;
	
	
	/**
	 * Instantiates a new contrato.
	 *
	 * @param id 
	 * @param apolice 
	 * @param morada 
	 * @param user_id 
	 * @param descricao 
	 * @param data_validade
	 */
	public contrato(String id, String apolice, String morada, String user_id, String descricao, String data_validade)
	{
		this.id = id;
		
		this.apolice = apolice;
	
		this.morada = morada;
	
		this.user_id = user_id;
	
		this.descricao = descricao;
	
		this.data_validade = data_validade;
		
	}
}
