package br.jeronimo.api.sicredi.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Document
public class VotingSession implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date dateStart;
	private Date dateFinish;
	private Associate associate;
	private Guideline guideline;
}
