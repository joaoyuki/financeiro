package com.controleFinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controleFinanceiro.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
