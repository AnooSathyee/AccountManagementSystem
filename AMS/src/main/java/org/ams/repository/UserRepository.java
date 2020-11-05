package org.ams.repository;

import org.ams.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	//The Repository represents the DAO layer, which typically does all the database operations.

	//updateUser(User user)

	//method Declaration
	public User findByEmail(String email);

}
