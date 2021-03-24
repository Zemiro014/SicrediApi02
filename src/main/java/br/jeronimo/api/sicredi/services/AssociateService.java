package br.jeronimo.api.sicredi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.jeronimo.api.sicredi.domain.Associate;
import br.jeronimo.api.sicredi.repositories.AssociateRepository;
import br.jeronimo.api.sicredi.services.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AssociateService implements SicrediService<Associate, String> {

	private final AssociateRepository associateRepository;
	
	@Override
	public Associate create(Associate obj) {
		return associateRepository.insert(obj);
	}

	@Override
	public List<Associate> findAll() {
		return associateRepository.findAll();
	}

	@Override
	public Associate findById(String id) {
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
