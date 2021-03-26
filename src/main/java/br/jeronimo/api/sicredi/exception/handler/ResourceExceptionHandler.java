package br.jeronimo.api.sicredi.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.jeronimo.api.sicredi.services.exception.AuthorizationException;
import br.jeronimo.api.sicredi.services.exception.ObjectNotFoundException;
import br.jeronimo.api.sicredi.services.exception.ObjectNullException;
import br.jeronimo.api.sicredi.services.exception.VotingNotAllowedException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Not found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(VotingNotAllowedException.class)
	public ResponseEntity<StandardError> votingNotAllowed(VotingNotAllowedException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Voto negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ObjectNullException.class)
	public ResponseEntity<StandardError> votingNotAllowed(ObjectNullException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "objecto nulo", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class) // Indica que este m�todo trata a exce��o do tipo "AuthorizationException"
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request)
	{
		StandardError err =  new StandardError(System.currentTimeMillis(),HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class) // Indica que este m�todo trata a exce��o do tipo "MethodArgumentNotValidException": Para vali��o de campos
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request)
	{
		ValidationError err = new ValidationError(System.currentTimeMillis(),HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de valida��o", e.getMessage(), request.getRequestURI());
		
		// Varrer a lista de exce��o "e" e pegar todos os campos de informa��o de erro
		for(FieldError x : e.getBindingResult().getFieldErrors()) 
		{
			err.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
}
