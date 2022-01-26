package com.nn.vehicles.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.nn.vehicles.model.Role;
import com.nn.vehicles.model.User;

@DataJpaTest
class UserRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager ;
	@Autowired
	private UserRepository userRepository;
	

	@Test
	void test() {
		User user1 = new User("user1","pass1",Role.ADMIN,null,null);
		User user2 = new User("user2","pass2",Role.ADMIN,null,null);
		testEntityManager.persistAndFlush(user1);
		testEntityManager.persistAndFlush(user2);
		
		List<User> tmp = userRepository.findAll();
		
		assertThat(userRepository.findAll().size()).isEqualTo(2);
	}

}
