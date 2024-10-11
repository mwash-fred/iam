package apps.fortuneconnect.authentication.dao.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUidAndDeletedFlag(UUID uid, Boolean deleted);
    Page<User> findByDeletedFlag(Boolean deleted, Pageable pageable);
    Optional<User> findByEmailAndDeletedFlag(String email, Boolean deleted);
    Page<User> findByEmailContainingIgnoreCaseAndDeletedFlag(String email, Boolean deleted,Pageable pageable);
    Page<User> findByEmailAndDeletedFlag(String email, Boolean deleted,Pageable pageable);
    boolean existsByEmail(String email);
}
