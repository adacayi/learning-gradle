package uk.co.sancode.learning_gradle.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.sancode.learning_gradle.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
