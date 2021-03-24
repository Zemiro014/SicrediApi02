package br.jeronimo.api.sicredi.domain.util;

import java.io.Serializable;

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
	private String title;
	private String description;
}
