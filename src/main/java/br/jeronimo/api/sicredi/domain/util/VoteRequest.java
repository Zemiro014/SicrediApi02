package br.jeronimo.api.sicredi.domain.util;

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
	private String associateId;
	private String guidelineId;
	private String vote;
}
