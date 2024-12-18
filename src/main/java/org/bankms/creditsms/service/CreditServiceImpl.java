package org.bankms.creditsms.service;

import lombok.RequiredArgsConstructor;
import org.bankms.creditsms.model.Credit;
import org.bankms.creditsms.repository.CreditRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService{

    private final CreditRepository creditRepository;

    @Override
    public Page<Credit> getCredits(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Credit> credits = creditRepository.findAll(pageable);
        return credits;
    }

    @Override
    public Credit saveCredit(Credit credit) {
        return creditRepository.save(credit);
    }
}
