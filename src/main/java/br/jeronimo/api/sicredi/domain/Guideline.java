package br.jeronimo.api.sicredi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Guideline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String title;
	private String description;
	
	@Builder.Default
	private List<String> votes = new ArrayList<>();

	public void setVotes(String vote) {
		votes.add(vote);
	}
}
