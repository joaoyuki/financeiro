package com.controleFinanceiro.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.ExampleMatcher.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

import com.controleFinanceiro.model.Categoria;
import com.controleFinanceiro.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
    @PersistenceContext
    private EntityManager em;	
	
	public Page<Categoria> buscaCategoriaPorFiltro(Categoria categoria, Pageable pageable){

		//ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("nome", GenericPropertyMatchers.contains());
		//Example<Categoria> example = Example.of(categoria, matcher);
		
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(" select categoria from com.controleFinanceiro.model.Categoria categoria ");
        adicionaOrdenacao(sb, pageable);
        Query query = this.em.createQuery(sb.toString());
        query.setFirstResult(primeiroRegistro);
        query.setMaxResults(totalRegistrosPorPagina); 
		List<Categoria> lista = query.getResultList();
		
		return new PageImpl<>(lista, pageable, 5);
		
	}
	
    private void adicionaOrdenacao(final StringBuilder query, Pageable pageable) {
        Sort sort = pageable.getSort();
        if (sort != null) {
            sort.forEach(order -> {
                if (order.getDirection().isAscending()) {
                	
                	query.append(" order by ").append(order.getProperty()).append(" asc ");
                }
                if (order.getDirection().isDescending()) {
                	query.append(" order by ").append(order.getProperty()).append(" desc ");
                }
            });
        }
    }

}
