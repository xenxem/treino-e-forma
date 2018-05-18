package br.com.treinoeforma.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class GpUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private boolean ativo;
	private Collection<GrantedAuthority> permissoes = new ArrayList<>();
	
	
	
	public GpUserDetails(Long id, String nome, String login, 
			String senha, boolean ativo) {
		
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}
	
	

	public Long getId() {
		return id;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return permissoes;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return ativo;
	}		
}