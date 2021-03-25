package br.jeronimo.api.sicredi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.jeronimo.api.sicredi.domain.Associate;
import br.jeronimo.api.sicredi.domain.util.AssociateRequest;
import br.jeronimo.api.sicredi.services.SicrediService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value="/associates")
@Api(value="API REST Associates")
@CrossOrigin(origins="*")
@AllArgsConstructor
public class AssociateResource {

	private final SicrediService<Associate, String> associateService;
	private final BCryptPasswordEncoder senhaCodificado;
	
	@GetMapping
	@ApiOperation(value="This method returns all existing associate in mongoDB")
	public ResponseEntity<List<Associate>> findAllAssociates(){
		
		List<Associate> list = associateService.findAll();		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ApiOperation(value="This method returns the associate that corresponds to the specified Id")
	public ResponseEntity<Associate> findAssociate(@PathVariable String id){
		
		Associate obj = associateService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="This method allows inserting a new one associated with the system, inform in your body the values ​​of the fields: name and email")
	public ResponseEntity<Associate> createAssociate(@RequestBody AssociateRequest objRequest){
		Associate obj = Associate.builder()
				.name(objRequest.getName())
				.email(objRequest.getEmail())
				.cpf(objRequest.getCpf())
				.senha(senhaCodificado.encode(objRequest.getSenha())).build();
		
		obj = associateService.create(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="This method allow to delete the associate that corresponds to the specified Id")
	public ResponseEntity<Associate> deleteAssociate(@PathVariable String id){
		
		associateService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
