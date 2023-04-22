package intens.finalTest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	
	Candidate findOneById(Long id);
	
	List<Candidate> findByFullName(String name);

	List<Candidate> findBySkills(Skill skill);
	

}
