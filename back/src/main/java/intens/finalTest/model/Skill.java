package intens.finalTest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Skill {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (nullable = false)
    private String title;
    
    @ManyToMany(mappedBy = "skills", cascade = CascadeType.ALL)
    //@JoinTable(name = "candidate_skill", joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"),
    //inverseJoinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"))
    private List<Candidate> candidates = new ArrayList<Candidate>();
    
	public Skill() {
		super();
	}

	public Skill(Long id, String title, List<Candidate> candidates) {
		super();
		this.id = id;
		this.title = title;
		this.candidates = candidates;
	}

	public Skill(String title) {
		super();
		this.title = title;
	}

	public Skill(String title, List<Candidate> candidates) {
		super();
		this.title = title;
		this.candidates = candidates;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	@Override
	public int hashCode() {
		return Objects.hash(candidates, id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		return Objects.equals(candidates, other.candidates) && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", title=" + title;
	}
	
}
