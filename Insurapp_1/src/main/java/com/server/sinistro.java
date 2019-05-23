package com.server;

public class sinistro {

	public String id;
	
	public String estado_id;

	public String user_id;

	public String contrato_apolice;

	public String contrato_morada;

	public String data_hora;
	
	public String descricao;
	
	public String fotos;
	
	public String intervencao_autoridades;
	
	public String titulos;

	
	public sinistro(String id, String estado_id, String user_id, String contrato_apolice, String contrato_morada, String data_hora, String descricao, String fotos, String intervencao_autoridades, String titulos)
	{
		this.id = id;
		
		this.estado_id = estado_id;
		
		this.user_id = user_id;
	
		this.contrato_apolice = contrato_apolice;
	
		this.contrato_morada = contrato_morada;
	
		this.data_hora = data_hora;
	
		this.descricao = descricao;
		
		this.fotos = fotos;
		
		this.intervencao_autoridades = intervencao_autoridades;
		
		this.titulos = titulos;
		
	}
}
