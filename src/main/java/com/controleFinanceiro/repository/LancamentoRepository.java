package com.controleFinanceiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.controleFinanceiro.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	
	@Query(nativeQuery = true, value = "select * from lancamento  WHERE descricao like ?1 LIMIT ?2")
	public List<Lancamento> obterLancamentoPorDescricao(String descricao, int totalRegistros);
	
	@Query(nativeQuery =  true, value = "SELECT * FROM distance.lancamento WHERE descricao like ?1 AND data_pagamento = ?2 AND data_vencimento = ?3")
	public List<Lancamento> obterLancamentoPorDescricaoEDatas(String descricao, LocalDate dataPagamento, LocalDate dataVencimento);

}
