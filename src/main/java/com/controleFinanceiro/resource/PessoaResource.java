package com.controleFinanceiro.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.controleFinanceiro.evento.RecursoCriadoEvento;
import com.controleFinanceiro.model.Endereco;
import com.controleFinanceiro.model.Pessoa;
import com.controleFinanceiro.repository.EnderecoRepository;
import com.controleFinanceiro.repository.PessoaRepository;
import com.controleFinanceiro.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ApplicationEventPublisher publicador;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> cadastrarPessoa(@RequestBody Pessoa pessoa, HttpServletResponse response){

		Endereco endereco = enderecoRepository.findOne(pessoa.getEndereco().getCodigo());
		pessoa.setEndereco(endereco);
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		publicador.publishEvent(new RecursoCriadoEvento(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);

	}

	@GetMapping("/listarEnderecos")
	public ResponseEntity<?> listarPessoas(){

		List<Pessoa> pessoas = pessoaRepository.findAll();

		return !pessoas.isEmpty() ? ResponseEntity.ok(pessoas) : ResponseEntity.noContent().build();

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscaPorId(@PathVariable Long codigo) {

		Pessoa pessoa = pessoaRepository.findOne(codigo);
		//Endereco endereco = enderecoRepository.findOne(pessoa.getEndereco().getCodigo());
		//pessoa.setEndereco(endereco);
		return null == pessoa ? ResponseEntity.notFound().build() : ResponseEntity.ok(pessoa);

	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaPessoaPorId(@PathVariable Long codigo){

		pessoaRepository.delete(codigo);

	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){

		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);

	}

	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody boolean ativo) {

		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);

	}

}
