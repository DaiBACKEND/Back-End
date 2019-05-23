package com.server;

public class contrato {

	public String id;
	
	public String apolice;

	public String morada;

	public String user_id;

	public String descricao;

	public String data_validade;
	
	
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
