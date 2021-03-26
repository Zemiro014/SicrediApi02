package br.jeronimo.api.sicredi.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.jeronimo.api.sicredi.domain.Associate;
import br.jeronimo.api.sicredi.domain.util.AssociateRequest;
import br.jeronimo.api.sicredi.exception.handler.FieldMessage;
import br.jeronimo.api.sicredi.repositories.AssociateRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssociateInsertValidator implements ConstraintValidator<AssociateInsert, AssociateRequest>  {

	private final AssociateRepository associateRepository;
	@Override
	public boolean isValid(AssociateRequest obj, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Associate aux = associateRepository.findByEmail(obj.getEmail());
		
		if(aux != null) {
			list.add(new FieldMessage("Email","O email inserido já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
