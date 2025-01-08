package org.bankms.clientsms.repository;

import org.bankms.clientsms.model.Client1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ClientRepository extends JpaRepository<Client1,Long> {

    Client1 findClientByClientId(Long clientId);

    boolean existsByCin(String cin);

    boolean existsByEmail( String emil);

    Page<Client1> findAll(Pageable pageable);

    long countByCreatedAtLessThanEqual(LocalDateTime endDateTime);
}
