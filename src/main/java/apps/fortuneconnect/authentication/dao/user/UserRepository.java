package apps.fortuneconnect.authentication.dao.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUidAndDeletedFlag(String uid, Boolean deleted);
    Page<User> findByDeletedFlagAndVerifiedFlag(Boolean deleted, Boolean verified, Pageable pageable);
    Optional<User> findByEmailAndDeletedFlag(String email, Boolean deleted);
    Page<User> findByEmailContainingIgnoreCaseAndDeletedFlag(String email, Boolean deleted,Pageable pageable);
    Page<User> findByEmailAndVerifiedFlagAndDeletedFlag(String email, Boolean verified,Boolean deleted,Pageable pageable);
    boolean existsByEmail(String email);
}
