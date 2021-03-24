package br.jeronimo.api.sicredi.services.exception;

public class ObjectNullException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNullException(String msg) {
		super(msg);
	}
}
