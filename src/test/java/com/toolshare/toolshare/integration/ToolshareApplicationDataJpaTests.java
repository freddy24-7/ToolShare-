package com.toolshare.toolshare.integration;

import com.toolshare.toolshare.model.Participant;
import com.toolshare.toolshare.repository.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//Integration Testing - using DataJpaTest to test database integration
@DataJpaTest
class ToolshareApplicationDataJpaTests {

	@Autowired
	ParticipantRepository participantRepository;

	@Test
	void testPostingParticipant() {

			long countBefore = participantRepository.count();
			participantRepository.save(new Participant(
					"manfred@gmail.com",
					"tom",
					"hansen",
					"3543HT",
					"www.photoMe.com",
					"0909087876"));
			long countAfter = participantRepository.count();
			assertThat(countBefore).isLessThan(countAfter);
	}

	@Test
	void contextLoads() {

	}

}