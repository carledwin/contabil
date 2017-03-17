package com.carledwin.ti.contabil.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Despesa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@NotNull(message="Data de vencimento obrigatória.")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataVencimento;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataPagamento;
	
	@NotNull(message="Valor é obrigatório.")
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal valor;
	
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal valorPago;
	
	@Enumerated(EnumType.STRING)
	private TipoDespesaEnum tipo;
	
	@Enumerated(EnumType.STRING)
	private StatusDespesaEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public TipoDespesaEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoDespesaEnum tipo) {
		this.tipo = tipo;
	}

	public StatusDespesaEnum getStatus() {
		return status;
	}

	public void setStatus(StatusDespesaEnum status) {
		this.status = status;
	}
	
}
