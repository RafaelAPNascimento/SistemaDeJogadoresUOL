package com.uol.service.domain;

public enum Grupo {

	VINGADORES ("Vingadores"),
	LIGA_DA_JUSTICA ("Liga da Justiça");
	
	String nome;

	private Grupo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}	
}
