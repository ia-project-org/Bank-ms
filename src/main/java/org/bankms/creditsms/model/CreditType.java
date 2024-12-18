package org.bankms.creditsms.model;

import lombok.Getter;

@Getter
public enum CreditType {
    AUTO_LOAN("Auto Loan"),
    CREDIT_BUILDER_LOAN("Credit-Builder Loan"),
    DEBT_CONSOLIDATION_LOAN("Debt Consolidation Loan"),
    HOME_EQUITY_LOAN("Home Equity Loan"),
    MORTGAGE_LOAN("Mortgage Loan"),
    PAYDAY_LOAN("Payday Loan"),
    STUDENT_LOAN("Student Loan");

    private final String displayName;

    CreditType(String displayName) {
        this.displayName = displayName;
    }

}
