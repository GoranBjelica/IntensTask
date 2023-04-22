package intens.finalTest.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String fullName;
   
    @Column(nullable = false)
    private String contactNumber;
    
    @Column(nullable = false)
    private String email;
    
    @Column (nullable = false)
    private LocalDate dateOfBirth;
    
    @ManyToMany
    @JoinTable(name = "candidate_skill", joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"))
    private Set<Skill> skills = new HashSet<Skill>();
    
    public Candidate() {
		super();
    }

	public Candidate(Long id, String fullName, String email, String contactNumber, LocalDate dateOfBirth,	Set<Skill> skills) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.skills = skills;
	}

	public Candidate(String fullName, String email, String contactNumber, LocalDate dateOfBirth, Set<Skill> skills) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.skills = skills;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String phoneNumber) {
		this.contactNumber = phoneNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, dateOfBirth, email, fullName, id, skills);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(skills, other.skills);
	}
	
	public Candidate clone() {
		return new Candidate(fullName, email, contactNumber, dateOfBirth, skills);
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", fullName=" + fullName + ", email=" + email + ", contactNumber=" + contactNumber
				+ ", dateOfBirth=" + dateOfBirth +", skills"  + skills;
	}
    
}