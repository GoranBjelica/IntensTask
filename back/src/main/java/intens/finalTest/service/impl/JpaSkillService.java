package intens.finalTest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;
import intens.finalTest.repository.CandidateRepository;
import intens.finalTest.repository.SkillRepository;
import intens.finalTest.service.SkillService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JpaSkillService implements SkillService {

    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private CandidateRepository candidateRepository;

	@Override
	public List<Skill> findAll() {
		List<Skill> skills = skillRepository.findAll();
		return skills;
	}

	@Override
	public Skill findOne(Long id) {
		Optional<Skill> skill = skillRepository.findById(id);
		if(skill.isPresent()) {
			return skill.get();
		}
		return null;
	}

	@Override
	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}

	@Override
	public Skill update(Skill skill) {
		return skillRepository.save(skill);
	}

	@Override
	public Skill delete(long skillId) {
		Optional<Skill> skill = skillRepository.findById(skillId);
		if(skill.isPresent()) {
			skillRepository.deleteById(skillId);
			return skill.get();
		}
		return null;	
	}

	@Override
	public Set<Skill> find(List<Long> idSkills) {
		Set<Skill> skills = new HashSet<Skill>();
		List<Skill> skillsList = skillRepository.findAllById(idSkills);
		for(Skill s: skillsList)
			skills.add(s);
		return skills;	
	}

	@Override
	public List<Candidate> findAll(Skill skill) {
		return candidateRepository.findBySkills(skill);
		 
	}

	@Override
	public List<Skill> findSkills(Long id) {
		List<Skill> allSkills = skillRepository.findAll();
		Set<Skill> candidateSkills = candidateRepository.findOneById(id).getSkills();
		List<Skill> skillsCandidateDoNotHave = new ArrayList<Skill>();
		skillsCandidateDoNotHave = allSkills.stream().filter(s -> !candidateSkills.contains(s)).collect(Collectors.toList());
		
		return skillsCandidateDoNotHave;
	}

	@Override
	public List<Skill> findSkillsCandidateHas(Long candidateId) {
		return	candidateRepository.findOneById(candidateId).getSkills().stream().collect(Collectors.toList());
		
	}
}
