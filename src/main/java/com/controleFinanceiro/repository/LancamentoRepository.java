package com.controleFinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
