package intens.finalTest.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;
import intens.finalTest.repository.CandidateRepository;
import intens.finalTest.service.CandidateService;

@Service
public class JPACandidateService implements CandidateService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	

	@Override
	public Candidate findById(Long id) {
		Optional<Candidate> candidate = candidateRepository.findById(id);
		if(candidate.isPresent()){
			return candidate.get();
		}
		return null;
	}

	@Override
	public List<Candidate> findByName(String name) {
		List<Candidate> candidates = candidateRepository.findByFullName(name);
		return candidates;
	}

	@Override
	public Candidate save(Candidate candidate) {
		System.out.println(candidate);
		return candidateRepository.save(candidate);
	}

	@Override
	public Candidate update(Long id, Skill newSkill) {
		Candidate candidate = candidateRepository.findOneById(id);
		candidate.getSkills().add(newSkill);
		return candidateRepository.save(candidate);
	}

	@Override
	public Candidate deleteSkill(Long id, Skill deletingSkill) {
		Candidate candidate = candidateRepository.findOneById(id);
		candidate.getSkills().remove(deletingSkill);
		return candidateRepository.save(candidate);
	}

	@Override
	public Candidate delete(Long id) {
		Optional<Candidate> candidate= candidateRepository.findById(id);
		if(candidate.isPresent()) {
			candidateRepository.deleteById(id);
			return candidate.get();
		}
		return null;
	}

}
