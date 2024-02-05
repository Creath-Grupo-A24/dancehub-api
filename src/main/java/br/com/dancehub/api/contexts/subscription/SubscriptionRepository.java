package br.com.dancehub.api.contexts.subscription;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {

    Page<SubscriptionEntity> findAll(Specification<SubscriptionEntity> specification, Pageable pageable);
    Page<SubscriptionEntity> findAllByEventId (UUID eventId, Pageable pageable);
}
