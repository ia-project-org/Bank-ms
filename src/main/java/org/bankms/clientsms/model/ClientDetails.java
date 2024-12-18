package org.bankms.clientsms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientDetails {

    @Id
    @JsonProperty("clientId")
    private Long clientId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;
    private double score;
    private String name;
    private String email;
    @JsonProperty("customer_id")
    @Column(nullable = false)
    private String customerId;

    @JsonProperty("ssn")
    @Column(nullable = false)
    private String ssn;

    @JsonProperty("month")
    @Column(nullable = false)
    private Long month; // Updated to int64

    @JsonProperty("age")
    @Column(nullable = false)
    private Long age; // Updated to int64

    @JsonProperty("annual_income")
    @Column(nullable = false)
    private Double annualIncome; // Updated to float64

    @JsonProperty("monthly_inhand_salary")
    @Column(nullable = false)
    private Double monthlyInhandSalary; // Updated to float64

    @JsonProperty("total_emi_per_month")
    @Column(nullable = false)
    private Double totalEmiPerMonth; // Updated to float64

    @JsonProperty("num_bank_accounts")
    @Column(nullable = false)
    private Long numBankAccounts; // Updated to int64

    @JsonProperty("num_credit_card")
    @Column(nullable = false)
    private Long numCreditCard; // Updated to int64

    @JsonProperty("interest_rate")
    @Column(nullable = false)
    private Long interestRate; // Updated to int64

    @JsonProperty("num_of_loan")
    @Column(nullable = false)
    private Long numOfLoan; // Updated to int64

    @JsonProperty("delay_from_due_date")
    @Column(nullable = false)
    private Long delayFromDueDate; // Updated to int64

    @JsonProperty("num_of_delayed_payment")
    @Column(nullable = false)
    private Long numOfDelayedPayment; // Updated to int64

    @JsonProperty("changed_credit_limit")
    @Column(nullable = false)
    private Double changedCreditLimit; // Updated to float64

    @JsonProperty("num_credit_inquiries")
    @Column(nullable = false)
    private Long numCreditInquiries; // Updated to int64

    @JsonProperty("credit_mix")
    @Column(nullable = false)
    private Long creditMix; // Updated to int64

    @JsonProperty("outstanding_debt")
    @Column(nullable = false)
    private Double outstandingDebt; // Updated to float64

    @JsonProperty("credit_utilization_ratio")
    @Column(nullable = false)
    private Double creditUtilizationRatio; // Updated to float64

    @JsonProperty("credit_history_age")
    @Column(nullable = false)
    private Long creditHistoryAge; // Updated to int64

    @JsonProperty("payment_of_min_amount")
    @Column(nullable = false)
    private Long paymentOfMinAmount; // Updated to int64

    @JsonProperty("amount_invested_monthly")
    @Column(nullable = false)
    private Double amountInvestedMonthly; // Updated to float64

    @JsonProperty("payment_behaviour")
    @Column(nullable = false)
    private Long paymentBehaviour; // Updated to int64

    @JsonProperty("monthly_balance")
    @Column(nullable = false)
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
    @Column(nullable = false)
    private String creditScore; // Updated to int64


    public ClientDetails(){

    }
}
