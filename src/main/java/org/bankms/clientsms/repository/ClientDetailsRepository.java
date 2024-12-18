package org.bankms.clientsms.repository;

import org.bankms.clientsms.model.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails,Long> {

    ClientDetails findClientDetailsByClientId(Long clientId);

    @Query("SELECT c FROM ClientDetails c WHERE " +
            "(:autoLoan IS NULL OR c.autoLoan = :autoLoan) AND " +
            "(:creditBuilderLoan IS NULL OR c.creditBuilderLoan = :creditBuilderLoan) AND " +
            "(:debtConsolidationLoan IS NULL OR c.debtConsolidationLoan = :debtConsolidationLoan) AND " +
            "(:homeEquityLoan IS NULL OR c.homeEquityLoan = :homeEquityLoan) AND " +
            "(:mortgageLoan IS NULL OR c.mortgageLoan = :mortgageLoan) AND " +
            "(:personalLoan IS NULL OR c.personalLoan = :personalLoan) AND " +
            "(:studentLoan IS NULL OR c.studentLoan = :studentLoan) AND " +
            "(:paydayLoan IS NULL OR c.paydayLoan = :paydayLoan)")
    List<ClientDetails> findClientsWithMatchingLoans(
            @Param("autoLoan") Long autoLoan,
            @Param("creditBuilderLoan") Long creditBuilderLoan,
            @Param("debtConsolidationLoan") Long debtConsolidationLoan,
            @Param("homeEquityLoan") Long homeEquityLoan,
            @Param("mortgageLoan") Long mortgageLoan,
            @Param("personalLoan") Long personalLoan,
            @Param("studentLoan") Long studentLoan,
            @Param("paydayLoan") Long paydayLoan
    );

}
