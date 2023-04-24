package intens.finalTest.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import intens.finalTest.model.Skill;
import intens.finalTest.repository.SkillRepository;

@SpringBootTest
class SkillServiceTest {
	
	@MockBean
	private SkillRepository skillRepository;
	
	@Autowired
	private SkillService skillService;

	@Test
	void testSave() {
		Skill newSkill = new Skill("New skill");
		Mockito.when(skillRepository.save(newSkill)).thenReturn(newSkill);
		assertEquals(skillService.save(newSkill), newSkill);		
	}
}
