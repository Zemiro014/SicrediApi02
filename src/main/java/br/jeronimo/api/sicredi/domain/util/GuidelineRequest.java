package br.jeronimo.api.sicredi.domain.util;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuidelineRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	@NotEmpty(message="O campo title deve ser preenchido")
	private String title;
	
	@NotEmpty(message="O campo description deve ser preenchido")
	private String description;
}
