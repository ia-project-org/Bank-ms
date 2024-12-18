package org.bankms.clientsms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCsvRecord {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String cin;

    @JsonProperty("customer_id")
    
    private String customerId;

    @JsonProperty("ssn")
    
    private String ssn;

    @JsonProperty("month")
    
    private Long month; // Updated to int64

    @JsonProperty("age")
    
    private Long age; // Updated to int64

    @JsonProperty("annual_income")
    
    private Double annualIncome; // Updated to float64

    @JsonProperty("monthly_inhand_salary")
    
    private Double monthlyInhandSalary; // Updated to float64

    @JsonProperty("total_emi_per_month")
    
    private Double totalEmiPerMonth; // Updated to float64

    @JsonProperty("num_bank_accounts")
    
    private Long numBankAccounts; // Updated to int64

    @JsonProperty("num_credit_card")
    
    private Long numCreditCard; // Updated to int64

    @JsonProperty("interest_rate")
    
    private Long interestRate; // Updated to int64

    @JsonProperty("num_of_loan")
    
    private Long numOfLoan; // Updated to int64

    @JsonProperty("delay_from_due_date")
    
    private Long delayFromDueDate; // Updated to int64

    @JsonProperty("num_of_delayed_payment")
    
    private Long numOfDelayedPayment; // Updated to int64

    @JsonProperty("changed_credit_limit")
    
    private Double changedCreditLimit; // Updated to float64

    @JsonProperty("num_credit_inquiries")
    
    private Long numCreditInquiries; // Updated to int64

    @JsonProperty("credit_mix")
    
    private Long creditMix; // Updated to int64

    @JsonProperty("outstanding_debt")
    
    private Double outstandingDebt; // Updated to float64

    @JsonProperty("credit_utilization_ratio")
    
    private Double creditUtilizationRatio; // Updated to float64

    @JsonProperty("credit_history_age")
    
    private Long creditHistoryAge; // Updated to int64

    @JsonProperty("payment_of_min_amount")
    
    private Long paymentOfMinAmount; // Updated to int64

    @JsonProperty("amount_invested_monthly")
    
    private Double amountInvestedMonthly; // Updated to float64

    @JsonProperty("payment_behaviour")
    
    private Long paymentBehaviour; // Updated to int64

    @JsonProperty("monthly_balance")
    
    private Double monthlyBalance; // Updated to float64

    @JsonProperty("auto_loan")
    private Long autoLoan; // Updated to bool

    @JsonProperty("credit_builder_loan")
    private Long creditBuilderLoan; // Updated to int64

    @JsonProperty("debt_consolidation_loan")
    private Long debtConsolidationLoan; // Updated to int64

    @JsonProperty("home_equity_loan")
    private Long homeEquityLoan; // Updated to bool

    @JsonProperty("mortgage_loan")
    private Long mortgageLoan; // Updated to int64

    @JsonProperty("no_loan")
    private Integer noLoan; // Updated to bool

    @JsonProperty("not_specified")
    private Long notSpecified; // Updated to int64

    @JsonProperty("payday_loan")
    private Long paydayLoan; // Updated to int64

    @JsonProperty("personal_loan")
    private Long personalLoan; // Updated to int64

    @JsonProperty("student_loan")
    private Long studentLoan; // Updated to int64

    @JsonProperty("occupation_Accountant")
    private Integer occupationAccountant; // Updated to bool

    @JsonProperty("occupation_Architect")
    private Integer occupationArchitect; // Updated to bool

    @JsonProperty("occupation_Developer")
    private Integer occupationDeveloper; // Updated to bool

    @JsonProperty("occupation_Doctor")
    private Integer occupationDoctor; // Updated to bool

    @JsonProperty("occupation_Engineer")
    private Integer occupationEngineer; // Updated to bool

    @JsonProperty("occupation_Entrepreneur")
    private Integer occupationEntrepreneur; // Updated to bool

    @JsonProperty("occupation_Journalist")
    private Integer occupationJournalist; // Updated to bool

    @JsonProperty("occupation_Lawyer")
    private Integer occupationLawyer; // Updated to bool

    @JsonProperty("occupation_Manager")
    private Integer occupationManager; // Updated to bool

    @JsonProperty("occupation_Mechanic")
    private Integer occupationMechanic; // Updated to bool

    @JsonProperty("occupation_Media_Manager")
    private Integer occupationMediaManager; // Updated to bool

    @JsonProperty("occupation_Musician")
    private Integer occupationMusician; // Updated to bool

    @JsonProperty("occupation_Scientist")
    private Integer occupationScientist; // Updated to bool

    @JsonProperty("occupation_Teacher")
    private Integer occupationTeacher; // Updated to bool

    @JsonProperty("occupation_Writer")
    private Integer occupationWriter; // Updated to bool

    @JsonProperty("credit_score")
    
    private String creditScore; // Updated to int64


}
