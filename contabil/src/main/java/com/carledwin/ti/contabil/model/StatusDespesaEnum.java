package com.carledwin.ti.contabil.model;

public enum StatusDespesaEnum {
	A_VENCER("A vencer"), VENCE_HOJE("Vence hoje"), VENCIDA("Vencida"), PAGA("Paga");

	private String descricao;

	StatusDespesaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
