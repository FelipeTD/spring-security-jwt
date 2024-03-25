package tortora.spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tortora.spring.security.jwt.model.OurUsers;

import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);
}
