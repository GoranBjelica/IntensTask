package intens.finalTest.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import intens.finalTest.model.Candidate;
import intens.finalTest.service.CandidateService;
import intens.finalTest.web.dto.CandidateDTO;


@Component
public class CandidateDtoToCandidateUpdate implements Converter<CandidateDTO, Candidate> {
	
	@Autowired
	CandidateService candidateService;

	@Override
	public Candidate convert(CandidateDTO dto) {
		Candidate candidate = candidateService.findById(dto.getId());
		candidate.setFullName(dto.getFullName());
		candidate.setEmail(dto.geteMail());
		candidate.setContactNumber(dto.getContactNumber());
		candidate.setDateOfBirth(getLocalDate(dto.getDateOfBirth()));
		
		return candidate;
	}
	
	
	private LocalDate getLocalDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
	

}
