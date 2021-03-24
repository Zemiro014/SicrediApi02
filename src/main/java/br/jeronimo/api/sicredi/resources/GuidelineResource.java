package br.jeronimo.api.sicredi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.jeronimo.api.sicredi.domain.Guideline;
import br.jeronimo.api.sicredi.domain.util.GuidelineRequest;
import br.jeronimo.api.sicredi.domain.util.GuidelineWithVotingResults;
import br.jeronimo.api.sicredi.services.SicrediService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value="/guidelines")
@Api(value="API REST guideline (subject) -Pautas-")
@CrossOrigin(origins="*")
@AllArgsConstructor
public class GuidelineResource {

	private final SicrediService<Guideline, String> guidelineService;
	
	@GetMapping
	@ApiOperation(value="This method returns all existing guideline (subject) -Pautas- in mongoDB")
	public ResponseEntity<List<Guideline>> findAllGuidelines(){
		
		List<Guideline> list = guidelineService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ApiOperation(value="This method returns the guideline (subject) -Pautas- that corresponds to the specified Id. "
			+ "It brings with it the voting results of the agenda corresponding to the Id")
	public ResponseEntity<GuidelineWithVotingResults> findGuideline(@PathVariable String id){
		
		Guideline obj = guidelineService.findById(id);		
		GuidelineWithVotingResults gdWithresult = new GuidelineWithVotingResults(obj);
		
		return ResponseEntity.ok().body(gdWithresult);
	}

	
	@RequestMapping(value="/{id}/votes", method=RequestMethod.GET)
	@ApiOperation(value="This method returns guideline (subject) -Pautas- votes of guideline that corresponds to the specified Id")
	public ResponseEntity<List<String>> getVotesOfGuideline(@PathVariable String id){
		
		Guideline obj = guidelineService.findById(id);
		return ResponseEntity.ok().body(obj.getVotes());
	}
	
	@PostMapping
	@ApiOperation(value="This method allows inserting a new guideline (subject) -Pautas- in system, "
			+ "inform in your body the values ​​of the fields: title and description")
	public ResponseEntity<Void> insertNewGuideline(@RequestBody GuidelineRequest objRequest){
		Guideline obj = Guideline.builder().title(objRequest.getTitle()).description(objRequest.getDescription()).build();
		obj = guidelineService.create(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="This method allow to delete the guideline (subject) -Pautas- that corresponds to the specified Id")
	public ResponseEntity<Void> deleteGuideline(@PathVariable String id){
		guidelineService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
