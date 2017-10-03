package com.controleFinanceiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.model.Categoria;
import com.controleFinanceiro.model.Lancamento;
import com.controleFinanceiro.model.Pessoa;
import com.controleFinanceiro.repository.CategoriaRepository;
import com.controleFinanceiro.repository.LancamentoRepository;
import com.controleFinanceiro.repository.PessoaRepository;
import com.controleFinanceiro.repository.filter.LancamentoFilter;
import com.controleFinanceiro.service.exception.PessoaInexistenteOuPessoaInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	public Lancamento salvarLancamento(Lancamento lancamento) {
		
		Pessoa buscaPessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if (null == buscaPessoa || buscaPessoa.isPessoaInativo()) {
			throw new PessoaInexistenteOuPessoaInativaException();
		}
		lancamento.setPessoa(buscaPessoa);
		
		Categoria buscaCategoria = categoriaRepository.findOne(lancamento.getCategoria().getCodigo());
		lancamento.setCategoria(buscaCategoria);
		
		Lancamento lancamentoSalco = lancamentoRepository.save(lancamento);		
		
		return lancamentoSalco;
		
	}

	public void delataLancamentoPorID(Long id) {

		lancamentoRepository.delete(id);
		
	}

	public Page<Lancamento> pesquisarLancamentosPorFiltro(LancamentoFilter lancamentoFilter, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		List<Lancamento> lancamentos = lancamentoRepository.obterLancamentoPorDescricao("%" + lancamentoFilter.getDescricao() + "%", totalRegistrosPorPagina);
		

		
		return new PageImpl<>(lancamentos, pageable, 1);
		//return lancamentoRepository.obterLancamentoPorDescricao("%" + lancamentoFilter.getDescricao() + "%");
		
	}

}
