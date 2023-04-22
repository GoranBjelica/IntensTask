package intens.finalTest.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;
import intens.finalTest.web.dto.CandidateDTO;


@Component
public class CandidateToCandidateDto implements Converter<Candidate, CandidateDTO> {
	@Autowired
	private SkillToSkillDto skillToSkillDto;

	@Override
	public CandidateDTO convert(Candidate source) {

		CandidateDTO dto = new CandidateDTO();
		dto.setId(source.getId());
		dto.setFullName(source.getFullName());
		dto.seteMail(source.getEmail());
		dto.setContactNumber(source.getContactNumber());
		dto.setDateOfBirth(source.getDateOfBirth().toString());
		List<Skill> skills = new ArrayList<Skill>(source.getSkills());
		dto.setSkills(new HashSet<>(skillToSkillDto.convertAll(skills)));

		return dto;
	}
	public List<CandidateDTO> convertAll(List<Candidate> candidates){
		List<CandidateDTO> dtos = new ArrayList<CandidateDTO>();
		for(Candidate emp: candidates) {
			dtos.add(convert(emp));
		}
		return dtos;

	}


}
