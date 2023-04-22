package intens.finalTest.service;

import java.util.List;
import java.util.Set;
import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;

public interface SkillService {

    List<Skill> findAll();
    Skill findOne(Long id);
    Skill save(Skill skill);
    Skill update(Skill skill);
    Skill delete(long skillId);
    Set<Skill> find(List<Long> idSkills);
	List<Candidate> findAll(Skill skill);
	List<Skill> findSkills(Long id);
	List<Skill> findSkillsCandidateHas(Long candidateId);
   
}
