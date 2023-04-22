package intens.finalTest.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;
import intens.finalTest.service.SkillService;
import intens.finalTest.web.dto.CandidateDTO;
import intens.finalTest.web.dto.SkillDTO;

@Component
public class CandidateDtoToCandidate implements Converter<CandidateDTO, Candidate> {
	
	@Autowired
	private SkillService skillService;

	@Override
	public Candidate convert(CandidateDTO dto) {
		Candidate candidate = new Candidate();
		candidate.setFullName(dto.getFullName().trim());
		candidate.setEmail(dto.geteMail().trim());
		candidate.setContactNumber(dto.getContactNumber().trim());
		candidate.setDateOfBirth(getLocalDate(dto.getDateOfBirth()));
		List<Long> idSkills = dto.getSkills().stream().map(SkillDTO :: getId).collect(Collectors.toList()); 
		Set<Skill> skills = skillService.find(idSkills);
		candidate.setSkills(skills);

		return candidate;
	}
	
	private LocalDate getLocalDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
	

}
