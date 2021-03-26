package br.jeronimo.api.sicredi.domain.util;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteRequest {

	private String id;
	
	@NotEmpty(message="Precisa ser informado um associateId")
	private String associateId;
	
	@NotEmpty(message="Precisa ser informado um guidelineId")
	private String guidelineId;
	
	@NotEmpty(message="Precisa ser informado o valor do voto (SIM/NAO)")
	private String vote;
}
