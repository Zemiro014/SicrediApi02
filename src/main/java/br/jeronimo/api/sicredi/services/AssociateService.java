package br.jeronimo.api.sicredi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.jeronimo.api.sicredi.domain.Associate;
import br.jeronimo.api.sicredi.domain.enums.Perfil;
import br.jeronimo.api.sicredi.repositories.AssociateRepository;
import br.jeronimo.api.sicredi.security.AssociateSpringSecurity;
import br.jeronimo.api.sicredi.services.exception.AuthorizationException;
import br.jeronimo.api.sicredi.services.exception.ObjectNotFoundException;
import br.jeronimo.api.sicredi.services.exception.ObjectNullException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AssociateService implements SicrediService<Associate, String> {

	private final AssociateRepository associateRepository;
	
	@Override
	public Associate create(Associate obj) {
		if(obj.getName()==null || obj.getEmail()==null)
			throw new ObjectNullException("Não é permitido criar um associado com name ou email nulos");
		obj.addPerfil(Perfil.ASSOCIATE);
		return associateRepository.insert(obj);
	}

	@Override
	public List<Associate> findAll() {
		return associateRepository.findAll();
	}

	@Override
	public Associate findById(String id) {
		
		AssociateSpringSecurity assciate = UserService.authenticated();
		
		if(assciate == null || !assciate.hasRole(Perfil.ADMIN) && !id.equals(assciate.getId())) {
			throw new AuthorizationException("Acesso negado !");
		}
			
		Optional<Associate> obj = associateRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("O Associado especificado não existe no nosso sistema"));
	}

	@Override
	public void deleteById(String id) {
		findById(id);
		associateRepository.deleteById(id);		
	}

	@Override
	public Associate updateData(Associate obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
