package com.controleFinanceiro.resource;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.controleFinanceiro.model.Categoria;
import com.controleFinanceiro.model.Endereco;
import com.controleFinanceiro.repository.EnderecoRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {
	
	@Autowired
	private EnderecoRepository enderecoRepository; 
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco){
		
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri() 
				.path("/{codigo}")
				.buildAndExpand(enderecoSalvo.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(enderecoSalvo);
	}
	
	@GetMapping("/listarEnderecos")
	public ResponseEntity<?> listarEnderecos(){
		
		RestTemplate restTemplate = new RestTemplate();
		Categoria categoria = restTemplate.getForObject("http://localhost:8080/categoria/listarEntidade", Categoria.class);
		
		System.out.println(categoria);
		
		List<Endereco> enderecos = enderecoRepository.findAll();
		
		return !enderecos.isEmpty() ? ResponseEntity.ok(enderecos) : ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity buscaPorId(@PathVariable Long codigo) {
		
		Endereco endereco = enderecoRepository.findOne(codigo);
		
		return null == endereco ? ResponseEntity.notFound().build() : ResponseEntity.ok(endereco);
		
	}

}
