package com.controleFinanceiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.model.Categoria;
import com.controleFinanceiro.model.Lancamento;
import com.controleFinanceiro.model.Pessoa;
import com.controleFinanceiro.repository.CategoriaRepository;
import com.controleFinanceiro.repository.LancamentoRepository;
import com.controleFinanceiro.repository.PessoaRepository;
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

}
