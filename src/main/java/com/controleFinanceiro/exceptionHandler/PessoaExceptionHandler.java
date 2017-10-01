package com.controleFinanceiro.exceptionHandler;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controleFinanceiro.exceptionHandler.ExceptionHandler.Erro;
import com.controleFinanceiro.service.exception.PessoaInexistenteOuPessoaInativaException;

@ControllerAdvice
public class PessoaExceptionHandler {
	
	@ExceptionHandler({PessoaInexistenteOuPessoaInativaException.class})
	public ResponseEntity<Object> handlerPessoaExceptionHandler(PessoaInexistenteOuPessoaInativaException ex){
		
		String mensagemUsuario = "Pessoa inexistente ou inativa";//messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
		
	}

}
