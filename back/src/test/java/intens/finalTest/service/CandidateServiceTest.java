package intens.finalTest.service;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import intens.finalTest.model.Candidate;
import intens.finalTest.model.Skill;
import intens.finalTest.repository.CandidateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class CandidateServiceTest {
	
	@Autowired
	private CandidateService candidateService;
	
	@MockBean
	private CandidateRepository candidateRepository;

	@Test
	void testSave() {
		LocalDate birth = LocalDate.of(1990, 1, 1);
		Set<Skill> skills = new HashSet<Skill>();
		Candidate cand = new Candidate("Novi Kandidat", "novi@email.com", "063-1548848", birth, skills);
		
		Mockito.when(candidateRepository.save(cand)).thenReturn(cand);
		assertEquals(candidateService.save(cand), cand);
		
	}
	
	@Test
	void testFindById() {
		LocalDate birth = LocalDate.of(1990, 1, 1);
		Set<Skill> skills = new HashSet<Skill>();
		Candidate cand = new Candidate(1L, "Novi Kandidat", "novi@email.com", "063-1548848", birth, skills);
		Mockito.when(candidateRepository.findOneById(1L)).thenReturn(cand);
		assertEquals(candidateService.findById(1l), cand);
		
	}
	
	@Test
	void testFindByName() {
		LocalDate birth = LocalDate.of(1990, 1, 1);
		Set<Skill> skills = new HashSet<Skill>();
		Candidate cand = new Candidate("Novi Kandidat", "novi@email.com", "063-1548848", birth, skills);
		List<Candidate> candidates = new ArrayList<Candidate>();
		candidates.add(cand);
		Mockito.when(candidateRepository.findByFullName("Novi Kandidat")).thenReturn(candidates);
		assertEquals(candidateService.findByName("Novi Kandidat"), candidates);
	}
	
	@Test
	void testDelete() {
		LocalDate birth = LocalDate.of(1990, 1, 1);
		Set<Skill> skills = new HashSet<Skill>();
		Candidate cand = new Candidate(1L, "Novi Kandidat", "novi@email.com", "063-1548848", birth, skills);
		Mockito.when(candidateRepository.findOneById(1L)).thenReturn(cand);
		Mockito.when(candidateRepository.existsById(cand.getId())).thenReturn(false);
		assertFalse(candidateRepository.existsById(cand.getId()));
		
	}
}
