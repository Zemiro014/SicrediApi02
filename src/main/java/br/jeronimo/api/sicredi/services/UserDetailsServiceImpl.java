package br.jeronimo.api.sicredi.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.jeronimo.api.sicredi.domain.Associate;
import br.jeronimo.api.sicredi.repositories.AssociateRepository;
import br.jeronimo.api.sicredi.security.AssociateSpringSecurity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final AssociateRepository associateRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Associate associate = associateRepository.findByEmail(email);
		
		if(associate == null) {
			throw new UsernameNotFoundException(email);			
		}
		
		return new AssociateSpringSecurity(associate.getId(), associate.getEmail(), associate.getSenha(), associate.getPerfis());
	}

}
