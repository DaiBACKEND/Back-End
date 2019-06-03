package com.server;

//construtor de um user

public class user
{
	public String id;
	
	public String tipo_id;

	public String nome;

	public String email;

	public String password;
	
	public String morada;
	
	public String contacto;
	
	public String nif;
	
	public String sexo;
	
	public String data_nascimento;
	
	public String numero_contrato;
	
	public String cidade;
	
	public String pais;
	
	public String codigo_postal;
	
	
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