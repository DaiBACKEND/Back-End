package com.server;

//construtor de um user

/**
 * The Class user.
 */
public class user
{
	
	/** The id. */
	public String id;
	
	/** The tipo id. */
	public String tipo_id;

	/** The nome. */
	public String nome;

	/** The email. */
	public String email;

	/** The password. */
	public String password;
	
	/** The morada. */
	public String morada;
	
	/** The contacto. */
	public String contacto;
	
	/** The nif. */
	public String nif;
	
	/** The sexo. */
	public String sexo;
	
	/** The data nascimento. */
	public String data_nascimento;
	
	/** The numero contrato. */
	public String numero_contrato;
	
	/** The cidade. */
	public String cidade;
	
	/** The pais. */
	public String pais;
	
	/** The codigo postal. */
	public String codigo_postal;
	
	
	/**
	 * Instantiates a new user.
	 *
	 * @param id
	 * @param tipo_id
	 * @param nome
	 * @param email
	 * @param password
	 * @param morada
	 * @param contacto
	 * @param nif 
	 * @param sexo 
	 * @param data_nascimento
	 * @param numero_contrato
	 * @param cidade 
	 * @param pais
	 * @param codigo_postal
	 */
	public user(String id, String tipo_id, String nome, String email, String password, String morada, String contacto, String nif, String sexo, String data_nascimento, String numero_contrato, String cidade, String pais, String codigo_postal)
	{
		this.id = id;
		
		this.tipo_id = tipo_id;
	
		this.nome = nome;
	
		this.email = email;
	
		this.password = password;
		
		this.morada = morada;
		
		this.contacto = contacto;
		
		this.nif = nif;
		
		this.sexo = sexo;
		
		this.data_nascimento = data_nascimento;
		
		this.numero_contrato = numero_contrato;
		
		this.cidade = cidade;
		
		this.pais = pais;
		
		this.codigo_postal = codigo_postal;
	}
	
	
}