package org.bankms.creditsms.service;

import org.bankms.creditsms.model.Credit;
import org.springframework.data.domain.Page;

public interface CreditService {

    Page<Credit> getCredits(int page,int size);

    Credit saveCredit(Credit credit);

}
