package org.bankms.clientsms.repository;

import org.bankms.clientsms.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findClientByClientId(Long clientId);

    boolean existsByCin(String cin);

    boolean existsByEmail( String emil);

    Page<Client> findAll(Pageable pageable);

    long countByCreatedAtLessThanEqual(LocalDateTime endDateTime);
}
