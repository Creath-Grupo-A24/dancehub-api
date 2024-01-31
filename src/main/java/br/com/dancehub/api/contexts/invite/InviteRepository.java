package br.com.dancehub.api.contexts.invite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InviteRepository extends JpaRepository<Invite, UUID> {

    Page<Invite> findAllBySentIsFalse(Pageable pageable);
}
