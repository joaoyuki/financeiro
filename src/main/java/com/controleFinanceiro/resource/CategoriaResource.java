package com.controleFinanceiro.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.controleFinanceiro.evento.RecursoCriadoEvento;
import com.controleFinanceiro.model.Categoria;
import com.controleFinanceiro.repository.CategoriaRepository;
import com.controleFinanceiro.service.CategoriaService;

@RestController //Diz que é um controlador rest
@RequestMapping("/categoria")
//@CrossOrigin //Permite que todos os métodos dessa classe possam ser acessados por outros servidores
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ApplicationEventPublisher publicador;	

	//@CrossOrigin //Permite que todas as origens podem chamar esse método	
	@GetMapping("/listarEntidade") //Mapeamento do GET para esse método
	public List<Categoria> listarEntidade(){
		
		return categoriaRepository.findAll();
		
	}
	
	@GetMapping("/buscaCateriaFiltro")
	public Page<Categoria> buscaCategoriaPorFiltro(Categoria categoria, Pageable pageable){
		
		return categoriaService.buscaCategoriaPorFiltro(categoria, pageable);
		
	}
	
	@GetMapping("/listarResponseEntity") //Mapeamento do GET para esse método
	public ResponseEntity<?> listarResponseEntity(){
		
		List<Categoria> categorias = categoriaRepository.findAll();
		return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)//Ao terminar a execução desse método, vai retornar um status 201
	public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody Categoria categoria, HttpServletResponse httpServletResponse) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		//URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri() 
		//.path("/{codigo}")
		//.buildAndExpand(categoriaSalva.getCodigo()).toUri(); //Vai pegar a URI atual e vai adicionar informação
		//O REST diz que por padrão, quando é adicionado um novo recurso, precisamos mostrar aonde pegalo no Location do Header
		//ServletUriComponentsBuilder Classe helper do spring que pega a requisição atual
		
		//httpServletResponse.setHeader("Location", uri.toASCIIString());
		publicador.publishEvent(new RecursoCriadoEvento(this, httpServletResponse, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity buscarPorId(@PathVariable Long codigo) {
		Categoria categoria = categoriaRepository.findOne(codigo); 
		return null == categoria ? ResponseEntity.notFound().build() : ResponseEntity.ok(categoria);
	}
	
}
