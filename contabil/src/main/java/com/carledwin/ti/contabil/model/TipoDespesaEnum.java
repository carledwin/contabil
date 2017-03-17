package com.carledwin.ti.contabil.model;

public enum TipoDespesaEnum {
	FIXA("Fixa"), PARCELADA("Parcelada"), NEGOCIACAO("Negociação"), VARIAVEL("Variável");
	
	private String descricao;

	TipoDespesaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
