package br.jeronimo.api.sicredi.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.jeronimo.api.sicredi.security.AssociateSpringSecurity;

public class UserService {

	public static AssociateSpringSecurity authenticated() {
		try {
			return (AssociateSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e) 
		{
			return null;
		}
	}
}
