package intens.finalTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import intens.finalTest.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
