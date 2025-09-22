package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
    Optional<ApplicationUser> findByEmail(String email);
}
