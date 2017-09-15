package com.controleFinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
