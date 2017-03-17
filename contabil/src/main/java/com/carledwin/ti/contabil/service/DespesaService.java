package com.carledwin.ti.contabil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.carledwin.ti.contabil.model.Despesa;
import com.carledwin.ti.contabil.model.StatusDespesaEnum;
import com.carledwin.ti.contabil.repository.Despesas;
import com.carledwin.ti.contabil.repository.filter.DespesaFilter;

@Service
public class DespesaService {

	@Autowired
	private Despesas repository;

	public void save(Despesa despesa){
		try{
			repository.save(despesa);
		}catch(DataIntegrityViolationException e){
			throw new IllegalArgumentException("Formato de data inválido.");
		}
	}
	
	public String pagar(Long codigo){
		Despesa despesa = repository.findOne(codigo);
		despesa.setStatus(StatusDespesaEnum.PAGA);
		repository.save(despesa);
		return despesa.getStatus().toString();
	}
	
	public void delete(Long codigo){
		repository.delete(codigo);
	}
	
	public List<Despesa> findByFilter(DespesaFilter filter){
		String descricao = filter.getDescricao() == null ? "%" : filter.getDescricao();
		return repository.findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(descricao);
	}

	public List<Despesa> findAll(){
		return repository.findAll();
	}
	
}
