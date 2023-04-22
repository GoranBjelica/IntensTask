package intens.finalTest.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import intens.finalTest.model.Skill;
import intens.finalTest.web.dto.SkillDTO;

@Component
public class SkillDtoToSkill implements Converter<SkillDTO, Skill> {
	
	@Override
	public Skill convert(SkillDTO dto) {
		Skill skill = new Skill();
		
		skill.setTitle(dto.getTitle().trim());
			
		return skill;
	}
}
