package com.controleFinanceiro.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.model.Lancamento;
import com.controleFinanceiro.model.Pessoa;
import com.controleFinanceiro.repository.LancamentoRepository;
import com.controleFinanceiro.repository.PessoaRepository;
import com.controleFinanceiro.repository.filter.LancamentoFilter;
import com.controleFinanceiro.service.LancamentoService;


@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping("/listarLancamentos")
	public ResponseEntity<?> listarLancamentos(){
		
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		
		return null==lancamentos ? ResponseEntity.notFound().build() : ResponseEntity.ok(lancamentos);
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity buscaLancamentoPorId(@PathVariable Long id) {
		
		Lancamento lancamento = lancamentoRepository.findOne(id);
		
		return null==lancamento ? ResponseEntity.notFound().build() : ResponseEntity.ok(lancamento);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)//Ao terminar a execução desse método, vai retornar um status 201
	public ResponseEntity<Lancamento> cadastraLancamento(@Valid @RequestBody Lancamento lancamento){
		
		Lancamento lancamentoSalco = lancamentoService.salvarLancamento(lancamento);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalco);
		
	}
	
	@GetMapping("/pesquisaLancamentos")
	public List<Lancamento> pesquisaLancamentos(LancamentoFilter lancamentoFilter){
		return null;
		
	}

}
