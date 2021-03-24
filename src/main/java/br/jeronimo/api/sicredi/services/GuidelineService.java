package br.jeronimo.api.sicredi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.jeronimo.api.sicredi.domain.Guideline;
import br.jeronimo.api.sicredi.repositories.GuidelineRepository;
import br.jeronimo.api.sicredi.services.exception.ObjectNotFoundException;
import br.jeronimo.api.sicredi.services.exception.ObjectNullException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuidelineService implements SicrediService<Guideline, String> {

	private final GuidelineRepository guidelineRepository;
	
	@Override
	public Guideline create(Guideline obj) {
		if(obj==null)
			throw new ObjectNullException("Não é permitido criar uma pauta passando dados nulos");
		return guidelineRepository.insert(obj);
	}

	@Override
	public List<Guideline> findAll() {
		return guidelineRepository.findAll();
	}

	@Override
	public Guideline findById(String id) {
		Optional<Guideline> obj = guidelineRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("A pauta especificada não existe no nosso sistema"));
	}

	@Override
	public void deleteById(String id) {
		findById(id);
		guidelineRepository.deleteById(id);		
	}

	@Override
	public Guideline updateData(Guideline obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
