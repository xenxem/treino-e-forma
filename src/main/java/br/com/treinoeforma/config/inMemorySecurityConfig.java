package br.com.treinoeforma.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

//@Configuration
public class inMemorySecurityConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		
		builder 
			.inMemoryAuthentication() 
				.withUser("carlos").password("123").roles("PG_LISTAR_TREINO")
					.and() .withUser("patrick").password("123").roles("PG_LISTAR_TREINO","PG_MONTAR_TREINO") 
					.and() .withUser("salome").password("123").roles("PG_LISTAR_EXERCICIO");	
		
	}
	
	
	
}
