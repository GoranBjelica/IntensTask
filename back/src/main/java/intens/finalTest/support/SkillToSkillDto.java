package intens.finalTest.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import intens.finalTest.model.Skill;
import intens.finalTest.web.dto.SkillDTO;
import java.util.ArrayList;
import java.util.List;

@Component
public class SkillToSkillDto implements Converter<Skill, SkillDTO> {
	
    @Override
    public SkillDTO convert(Skill source) {
        SkillDTO dto = new SkillDTO();
        dto.setId(source.getId());
        dto.setTitle(source.getTitle());

        return dto;
    }

    public List<SkillDTO> convertAll(List<Skill> skills){
        List<SkillDTO> dtos = new ArrayList<>();
        for(Skill skill: skills){
            dtos.add(convert(skill));
        }
            return dtos;
    }

}
