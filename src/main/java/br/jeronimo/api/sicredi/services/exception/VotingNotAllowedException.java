package br.jeronimo.api.sicredi.services.exception;

public class VotingNotAllowedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public VotingNotAllowedException(String msg) {
		super(msg);
	}

}
