package org.bankms.batch_processing.clientsconfig;


import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.dto.ClientCsvRecord;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.repository.ClientDetailsRepository;
import org.bankms.clientsms.repository.ClientRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static org.bankms.batch_processing.utlis.Utils.createItemReader;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class ClientsImportBatchConfig {

    private final ClientRepository clientRepository;

    private final ClientDetailsRepository clientDetailsRepository;

    private final PlatformTransactionManager platformTransactionManager;

    private final JobRepository jobRepository;

    public final static String file_path = "C:/Users/HP/backup/IaProject/Bank-ms/src/main/resources/static/clientsDetails.csv";

    @Bean
    public FlatFileItemReader<ClientCsvRecord> ClientItemReader() {
        FlatFileItemReader<ClientCsvRecord> itemReader = createItemReader(file_path,"ClientItemReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    public ClientWriter Clientwriter() {
        return new ClientWriter(clientRepository, clientDetailsRepository);
    }

    @Bean
    public ClientProcessor Clientprocessor() {
        return new ClientProcessor();
    }

    @Bean
    public Step ClientimportStep() {
        return new StepBuilder("importClientsStep", jobRepository)
                .<ClientCsvRecord, Client>chunk(50, platformTransactionManager)
                .reader(ClientItemReader())
                .processor(Clientprocessor())
                .writer(Clientwriter())
                .build();
    }

    @Bean(name = "importClientJob")
    public Job ClientsrubJob(ClientImportJobListener listener) {
        return new JobBuilder("importClientJob", jobRepository)
                .listener(listener)
                .start(ClientimportStep())
                .build();
    }

    private LineMapper<ClientCsvRecord> lineMapper() {
        DefaultLineMapper<ClientCsvRecord> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);

        lineTokenizer.setNames(
                "firstName","lastName","email","phoneNumber","cin","customer_id","ssn","month", "age", "annual_income", "monthly_inhand_salary", "total_emi_per_month",
                "num_bank_accounts", "num_credit_card", "interest_rate", "num_of_loan",
                "delay_from_due_date", "num_of_delayed_payment", "changed_credit_limit",
                "num_credit_inquiries", "credit_mix", "outstanding_debt",
                "credit_utilization_ratio", "credit_history_age", "payment_of_min_amount",
                "amount_invested_monthly", "payment_behaviour", "monthly_balance",
                "auto_loan", "credit_builder_loan", "debt_consolidation_loan",
                "home_equity_loan", "mortgage_loan", "no_loan", "not_specified",
                "payday_loan", "personal_loan", "student_loan",
                "occupation_Accountant", "occupation_Architect", "occupation_Developer",
                "occupation_Doctor", "occupation_Engineer", "occupation_Entrepreneur",
                "occupation_Journalist", "occupation_Lawyer", "occupation_Manager",
                "occupation_Mechanic", "occupation_Media_Manager", "occupation_Musician",
                "occupation_Scientist", "occupation_Teacher", "occupation_Writer","credit_score"
        );

        lineTokenizer.setStrict(false);
        BeanWrapperFieldSetMapper<ClientCsvRecord> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(ClientCsvRecord.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

}
