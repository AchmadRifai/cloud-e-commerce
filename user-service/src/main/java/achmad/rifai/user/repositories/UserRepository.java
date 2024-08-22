package achmad.rifai.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import achmad.rifai.user.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
