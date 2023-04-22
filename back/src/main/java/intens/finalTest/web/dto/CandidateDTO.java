package intens.finalTest.web.dto;

import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

@Component
public class CandidateDTO {
	
	private long id;
	
	@NotBlank
	@Pattern(regexp = "^[A-Z][a-z]+( [A-Z][a-z]+)+",
			 message = "Full name in wrong format")
	private String fullName;
	
	@NotBlank
	@Pattern(regexp = "^[^\\s]+@[a-z]+(\\.[a-z]+)+$",
			 message = "E mail in wrong format")
	private String eMail;
	
	@NotBlank
	private String contactNumber;
	
	@NotBlank
    @Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",
    		message = "date and time must be in format yyyy-mm-dd")
	private String dateOfBirth;

//	@NotEmpty
	private Set<SkillDTO> skills;

	public CandidateDTO() {
		super();
	}

	public CandidateDTO(String fullName, String eMail, String contactNumber, String dateOfBirth) {
		super();
		this.fullName = fullName;
		this.eMail = eMail;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
	}

	public CandidateDTO(long id, String fullName, String eMail, String contactNumber, String dateOfBirth) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.eMail = eMail;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
	}
	
	public CandidateDTO(long id, String fullName, String eMail, String contactNumber, Set<SkillDTO> skills,
			 String dateOfBirth) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.eMail = eMail;
		this.contactNumber = contactNumber;
		this.skills = skills;
		this.dateOfBirth = dateOfBirth;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<SkillDTO> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillDTO> skills) {
		this.skills = skills;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, dateOfBirth, eMail, fullName, id, skills);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateDTO other = (CandidateDTO) obj;
		return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(eMail, other.eMail) && Objects.equals(fullName, other.fullName) && id == other.id
				&& Objects.equals(skills, other.skills);
	}

	@Override
	public String toString() {
		return "CandidateDTO [id=" + id + ", fullName=" + fullName + ", eMail=" + eMail + ", contactNumber="
				+ contactNumber + ", dateOfBirth=" + dateOfBirth + ", skills=" + skills + "]";
	}
	
}
