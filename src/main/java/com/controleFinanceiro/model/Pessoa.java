package com.controleFinanceiro.model;

import com.controleFinanceiro.model.converter.BooleanToStringConverter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Convert;

@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	private String nome;
        
        @Convert(converter = BooleanToStringConverter.class)
	private boolean ativo;
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(orphanRemoval = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_endereco", nullable = false)
	private Endereco endereco;
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@JsonIgnore //Ignora essa propriedade quando for fazer o parse do json
	@Transactional //Indica para o hibernate ignorar essa propriedade
	public boolean isPessoaInativo() {
		return !this.ativo;
	}

        public Pessoa(Long codigo, String nome, boolean ativo, Endereco endereco) {
            this.codigo = codigo;
            this.nome = nome;
            this.ativo = ativo;
            this.endereco = endereco;
        }
        
        public Pessoa(){
            
        }
	
        public Pessoa(String nome, boolean ativo, Endereco endereco) {
            this.nome = nome;
            this.ativo = ativo;
            this.endereco = endereco;
        }
        
}
