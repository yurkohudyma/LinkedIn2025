package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.profile.User;
import ua.hudyma.domain.profile.UserConnection;

import java.util.Optional;

public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {
    boolean existsByUserAndContact(User initUser, User connectingUser);

    Optional<UserConnection> findByUserAndContactOrContactAndUser(
            User initUser1, User connectingUser1, User initUser2, User connectingUser2);
}
