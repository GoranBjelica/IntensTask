package intens.finalTest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;
import intens.finalTest.service.CandidateService;
import intens.finalTest.service.SkillService;
import intens.finalTest.support.CandidateToCandidateDto;
import intens.finalTest.support.SkillDtoToSkill;
import intens.finalTest.support.SkillToSkillDto;
import intens.finalTest.web.dto.CandidateDTO;
import intens.finalTest.web.dto.SkillDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/skills")
@Validated
public class SkillController {

    @Autowired
    private SkillService skillService;
    
    @Autowired
    private SkillToSkillDto toDto;
    
    @Autowired
    private SkillDtoToSkill toSkill;
       
    @Autowired
    private CandidateToCandidateDto toCandidateDto;
    
    @Autowired
    private CandidateService candidateService;

    
    @GetMapping(value = "/{skillId}")
    public ResponseEntity<List<CandidateDTO>> getAllBySkill(@PathVariable  Long skillId){
    	if(skillService.findOne(skillId) == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    
        List<Candidate> candidates = skillService.findAll(skillService.findOne(skillId));
        return new ResponseEntity<>(toCandidateDto.convertAll(candidates), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAll(){
    	List<Skill> skills = new ArrayList<Skill>();
    	skills = skillService.findAll();
    	return new ResponseEntity<List<SkillDTO>>(toDto.convertAll(skills), HttpStatus.OK);
    }
    
    @GetMapping (value = "/candidatedoesnthave/{candidateId}")
    public ResponseEntity<List<SkillDTO>> getAllSkillsCandidateDoesNotHaveYet(@PathVariable Long candidateId){
    	if(candidateService.findById(candidateId) == null) 
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	List<Skill> skills = skillService.findSkills(candidateId);
    	
    	return new ResponseEntity<List<SkillDTO>>(toDto.convertAll(skills), HttpStatus.OK);
    }
    
    @GetMapping (value = "/candidatehas/{candidateId}")
    public ResponseEntity<List<SkillDTO>> getAllSkillsCandidateHas(@PathVariable Long candidateId){
    	if(candidateService.findById(candidateId) == null) 
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	List<Skill> skills = skillService.findSkillsCandidateHas(candidateId);
    	
    	return new ResponseEntity<List<SkillDTO>>(toDto.convertAll(skills), HttpStatus.OK);
    }
    
    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkillDTO> add(@RequestBody SkillDTO skillDTO){
    	
    	Skill skill = toSkill.convert(skillDTO);
    	Skill savedTask = skillService.save(skill);

    	return new ResponseEntity<>(toDto.convert(savedTask) ,HttpStatus.OK);	
    }
  /*  
    @PutMapping(value = "/{id}")
    public ResponseEntity<SkillDTO> update(@PathVariable Long id, @Valid @RequestBody SkillDTO skillDTO){
    	
    	if(!id.equals(skillDTO.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    	if(skillService.findOne(id) == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	Skill skill = toSkillUpdate.convert(skillDTO);
    	Skill updatedTask = skillService.update(skill);
    	return new ResponseEntity<>(toDto.convert(updatedTask), HttpStatus.OK);
       	
    }
    
    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<Void> delete (@PathVariable Long id){
    	Skill deletedTask = skillService.delete(id);
    	if(deletedTask != null) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }
       	*/
} 
    
    

