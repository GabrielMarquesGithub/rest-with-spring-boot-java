package br.com.nikisgabriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.nikisgabriel.model.User;

public interface UserRespository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User WHERE u.userName = :userName")
	User findByUserName(@Param("userName") String userName);
}
