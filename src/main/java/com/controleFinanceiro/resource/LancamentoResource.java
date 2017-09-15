package com.controleFinanceiro.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controleFinanceiro.model.Lancamento;
import com.controleFinanceiro.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
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

}
