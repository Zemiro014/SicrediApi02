package br.jeronimo.api.sicredi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.jeronimo.api.sicredi.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection="associate")
public class Associate implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String cpf;
	
	@JsonIgnore
	private String senha;	
	
	@Builder.Default
	private List<Integer> perfis = new ArrayList<>();
	
	public void addPerfil(Perfil perfil) 
	{
		perfis.add(perfil.getCod());
	}	

	public List<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toList());
	}	
}
