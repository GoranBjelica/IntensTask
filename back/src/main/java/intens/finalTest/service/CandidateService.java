package intens.finalTest.service;

import java.util.List;
import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;

public interface CandidateService {
    
	Candidate save(Candidate candidate);
	
    Candidate findById(Long id);

//    List<Candidate> findAll();

    Candidate update(Long id, Skill skill);

    Candidate delete(Long id);

	Candidate deleteSkill(Long id, Skill deletingSkill);

	List<Candidate> findByName(String name);

   
}
