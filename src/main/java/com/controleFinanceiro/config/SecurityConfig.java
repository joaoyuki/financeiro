package com.controleFinanceiro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

//Essa classe mostra como fazer a autorização de forma simples, só com usuário e senha. Não utiliza o oauth2
//@Configuration //Indica que essa é uma classe de configuração
//@EnableWebMvcSecurity //Essa anotação habilita a segurança
public class SecurityConfig {//extends WebSecurityConfigurerAdapter{
	
	//@Override
	//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ROLE");
		
	//}
	
	//@Override
	//protected void configure(HttpSecurity http) throws Exception {
		
/*		http.authorizeRequests()
		.antMatchers("/categorias").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf().disable();*/
		
	//}

}
