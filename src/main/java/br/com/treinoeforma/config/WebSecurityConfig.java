package br.com.treinoeforma.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/* API fluente */
		http  
			.authorizeRequests()		/* Configurando a segurança das requisições	*/				
				.antMatchers("/home","/registro","/cadastro").permitAll()
				.antMatchers("/treinoeforma/treinos").hasAnyRole("ROLE_PG_MONTAR_TREINO")
				.antMatchers("/treinoeforma/listarTreino").hasAnyRole("ROLE_PG_LISTAR_TREINO")
				.antMatchers("/treinoeforma/listarExercicio").hasAnyRole("ROLE_PG_LISTAR_EXERCICIO")				
				.anyRequest()			/* Qualquer requisição */
				.authenticated() 		/* Para qualquer requisição o usuário precisa estar autenticado */
			.and() 
			  		.formLogin()			/* Formulário HTML definido pelo Spring */
			  		.loginPage("/login")
			  		.permitAll()
			.and()
			 	.logout()
			 	.logoutSuccessUrl("/login?logout")
			 	.permitAll();
	}
	
		
}
