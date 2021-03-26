package br.jeronimo.api.sicredi.domain.util;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssociateRequest {

	@NotEmpty(message="O name precisa ser Preenchido obrigat�riamente")
	private String name;
	
	@NotEmpty(message="O Email precisa ser Preenchido obrigat�riamente")
	@Email(message="O formato do E-mail n�o � v�lido")
	private String email;
	
	@NotEmpty(message="O CPF precisa ser Preenchido obrigat�riamente")
	@CPF(message="Digite um CPF v�lido")
	private String cpf;
	
	@NotEmpty(message="A senha precisa ser Preenchida obrigat�riamente")
	private String senha;
}
