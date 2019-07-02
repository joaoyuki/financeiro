package com.controleFinanceiro.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component //Para ser um componente do spring
@Order(Ordered.HIGHEST_PRECEDENCE) // Diz que essa requisição tem uma alta prioridade de execução
public class RefreshTokenCookiePreProcessorFilter implements Filter{

	// Verifica se a chamada vem de /oauth/token, caso sim, verifica se tem um parametro grant_type com valor refresh_tokenm
	// caso sim, percorre os cookies e procura um chamado refreshToken, cria um wrapper e adiciona esse cookie na requisição
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		
		if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
			&& "refresh_token".equals(req.getParameter("grant_type"))
			&& req.getCookies() != null) {
			for (Cookie cooki : req.getCookies()) {
				if (cooki.getName().equals("refreshToken")) {
					String refreshToken = cooki.getValue();
					req = new MyServletRequestWrapper(req, refreshToken);
				}
			}
		}
		
		chain.doFilter(req, response);
		
	}	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	// Cria uma classe wrapper que extende a classe HttpServletRequestWrapper e adiciona no contrutor o refresh_token obtido
	static class MyServletRequestWrapper extends HttpServletRequestWrapper {

		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = new ParameterMap<String, String[]>(getRequest().getParameterMap()); 
			map.put("refresh_token", new String[] { refreshToken });
			map.setLocked(true);
			return map;
		}

		private String refreshToken;
		
		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
	}

}
