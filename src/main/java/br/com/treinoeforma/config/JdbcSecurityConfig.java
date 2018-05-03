package br.com.treinoeforma.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class JdbcSecurityConfig {
	
	
	public static final String USUARIO_POR_LOGIN = "select u.login, u.senha, u.id, u.ativo, u.nome from usuario u where u.login=? ";
	
	public static final String PERMISSOES_POR_USUARIO = "SELECT u.login, p.nome as nome_permissao" 
					+ " FROM usuario_permissao up JOIN usuario u ON u.id = up.usuario_id "    
					+ " JOIN permissao p ON p.id = up.permissao_id WHERE u.login = ? ";
	
	public static final String PERMISSOES_POR_GRUPO = "SELECT g.id, g.nome, p.nome FROM grupo_permissao gp "  
			+ " JOIN grupo g ON g.id = gp.grupo_id "  
			+ " JOIN permissao p ON p.id = gp.permissao_id "  
			+ " JOIN usuario_grupo ug ON ug.grupo_id = g.id "  
			+ " JOIN usuario u ON u.id = ug.usuario_id WHERE u.login=?";
	
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder,PasswordEncoder passwordEncoder,DataSource dataSource) throws Exception {
		
		builder
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery(USUARIO_POR_LOGIN)
			.authoritiesByUsernameQuery(PERMISSOES_POR_USUARIO)
			.groupAuthoritiesByUsername(PERMISSOES_POR_GRUPO);
		
	}
	
}
