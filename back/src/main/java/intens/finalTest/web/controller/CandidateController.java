package intens.finalTest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;
import intens.finalTest.service.CandidateService;
import intens.finalTest.support.CandidateDtoToCandidate;
import intens.finalTest.support.CandidateToCandidateDto;
import intens.finalTest.support.SkillDtoToSkillUpdate;
import intens.finalTest.web.dto.CandidateDTO;
import intens.finalTest.web.dto.SkillDTO;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/candidates", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
    
    @Autowired
    private CandidateToCandidateDto toDto;
    
    @Autowired
    private CandidateDtoToCandidate toCandidate;
    
    @Autowired
    private SkillDtoToSkillUpdate toSkillUpdate;
    
    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getByName(
    		@RequestParam(required = false) String fullName){
    	List<Candidate> candidates = candidateService.findByName(fullName);
    	return new ResponseEntity<>(toDto.convertAll(candidates), HttpStatus.OK);
    }
    
    @GetMapping (value = "/{id}")
    public ResponseEntity<CandidateDTO> getOne(@PathVariable Long id){

    	Candidate candidate = candidateService.findById(id);
    	if(candidate == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<>(toDto.convert(candidate), HttpStatus.OK);
    }
    

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDTO> add(@Valid @RequestBody CandidateDTO candidateDTO){
    	
    	Candidate candidate = toCandidate.convert(candidateDTO);
    	Candidate savedEmployee = candidateService.save(candidate);
    	return new ResponseEntity<>(toDto.convert(savedEmployee), HttpStatus.OK); 
    }

    @PutMapping(value = "/updateskill/{id}")
    public ResponseEntity<CandidateDTO> update (@PathVariable Long id, @RequestBody SkillDTO skillDTO){
    	    	
    	if(candidateService.findById(id) == null) {
    		return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
    	}
    	Skill newSkillforCandidate = toSkillUpdate.convert(skillDTO);
    	Candidate updatedCandidate = candidateService.update(id, newSkillforCandidate);
    	return new ResponseEntity<>(toDto.convert(updatedCandidate), HttpStatus.OK);
    }

    @PutMapping(value ="/deleteskill/{id}")
    public ResponseEntity<CandidateDTO> deleteSkill(@PathVariable Long id, @RequestBody SkillDTO skillDTO){
    	if(candidateService.findById(id) == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	if(!candidateService.findById(id).getSkills().contains(toSkillUpdate.convert(skillDTO))) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	Skill deletingSkill = toSkillUpdate.convert(skillDTO);
    	Candidate updatedCnadidate = candidateService.deleteSkill(id, deletingSkill);
    	return new ResponseEntity<>(toDto.convert(updatedCnadidate), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
    	Candidate deleteCandidate = candidateService.delete(id);
    	if(deleteCandidate != null) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }

}
