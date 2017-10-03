package com.controleFinanceiro.exceptionHandler;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controleFinanceiro.exceptionHandler.ExceptionHandler.Erro;
import com.controleFinanceiro.service.exception.LancamentoInexistenteException;

@ControllerAdvice
public class LancamentoExceptionHandler {
	
	@ExceptionHandler({LancamentoInexistenteException.class})
	public ResponseEntity<Object> handleLancamentoInexistente(LancamentoInexistenteException ex){
		
		String mensagemUsuario = "Lançamento inexistente";//messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
		
	}

}
