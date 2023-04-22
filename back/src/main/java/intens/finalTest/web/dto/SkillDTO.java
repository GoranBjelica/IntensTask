package intens.finalTest.web.dto;

import javax.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class SkillDTO {

    private Long id;
    
    @NotBlank
    private String title;

	public SkillDTO() {
		super();
	}

	public SkillDTO(@NotBlank String title) {
		super();
		this.title = title;
	}

	public SkillDTO(Long id, @NotBlank String title) {
		super();
		this.id = id;
		this.title = title;
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

	@Override
	public String toString() {
		return "SkillDTO [id=" + id + ", title=" + title + "]";
	}
    
    
    

	
    
}
