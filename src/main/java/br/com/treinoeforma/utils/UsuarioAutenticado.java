package br.com.treinoeforma.utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.treinoeforma.security.GpUserDetails;

public class UsuarioAutenticado {
	
	public static GpUserDetails obterUsuarioAutenticado() {
		SecurityContext context = SecurityContextHolder.getContext();
		return (GpUserDetails) context.getAuthentication().getPrincipal();
	}
	
	
}
