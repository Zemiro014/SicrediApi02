package br.jeronimo.api.sicredi.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.jeronimo.api.sicredi.domain.enums.Perfil;

public class AssociateSpringSecurity implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public AssociateSpringSecurity() {
		
	}
	public AssociateSpringSecurity(String id, String email, String senha, List<Perfil> perfis) {
		super();
		this.setId(id);
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
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
		return true;
	}

	public boolean hasRole(Perfil perfil) 
	{
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescription()));
	}
}
