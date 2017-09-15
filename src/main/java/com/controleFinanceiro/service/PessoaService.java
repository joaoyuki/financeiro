package com.controleFinanceiro.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.model.Pessoa;
import com.controleFinanceiro.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {

		Pessoa pessoaSalva = buscarPessoaPorCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");//Copia os dados de pessoa para pessoaSalva, ignorando o código
		return pessoaRepository.save(pessoaSalva);

	}

	private Pessoa buscarPessoaPorCodigo(Long codigo) {
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}

	public void atualizarPropriedadeAtivo(Long codigo, boolean ativo) {

		Pessoa pessoaSalva = buscarPessoaPorCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);

	}

}
