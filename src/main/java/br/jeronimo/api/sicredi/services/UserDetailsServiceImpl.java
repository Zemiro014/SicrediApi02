package br.jeronimo.api.sicredi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	private final AssociateRepository associateRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Associate associate = associateRepository.findByEmail(email);
		
		if(associate == null) {
			logger.info("A busca deu errado");
			throw new UsernameNotFoundException(email);			
		}
		logger.info(String.format("Dados de busca -> %s",associate));
		
		return new AssociateSpringSecurity(associate.getId(), associate.getEmail(), associate.getSenha(), associate.getPerfis());
	}

}
