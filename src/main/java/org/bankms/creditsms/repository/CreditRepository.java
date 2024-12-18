package org.bankms.creditsms.repository;

import org.bankms.creditsms.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditRepository extends JpaRepository<Credit,Long> {
}
