package intens.finalTest.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import intens.finalTest.model.Skill;
import intens.finalTest.service.CandidateService;
import intens.finalTest.service.SkillService;
import intens.finalTest.web.dto.SkillDTO;

@Component
public class SkillDtoToSkillUpdate implements Converter<SkillDTO, Skill> {
	
	@Autowired
	SkillService skillService;
	
	@Autowired
	CandidateService candidateService;

	@Override
	public Skill convert(SkillDTO dto) {
		Skill skill = skillService.findOne(dto.getId());
		
		return skill;
	}

}
