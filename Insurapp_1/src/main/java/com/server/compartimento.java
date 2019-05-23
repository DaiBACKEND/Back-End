package com.server;

public class compartimento {
	
	public String id;
	
	public String habitacao_id;

	public String descricao;

	public String estado;

	
	public compartimento(String id, String habitacao_id, String descricao, String estado)
	{
		this.id = id;
		
		this.habitacao_id = habitacao_id;
	
		this.descricao = descricao;
	
		this.estado = estado;
	
	}
}
