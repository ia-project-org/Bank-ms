package org.bankms.batch_processing.clientsconfig;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.model.ClientDetails;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static org.bankms.batch_processing.utlis.Utils.createFlatFileItemWriter;
import static org.bankms.batch_processing.utlis.Utils.createJpaPagingItemReader;

@Configuration
@RequiredArgsConstructor
public class ClientDetailsExportBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public JpaPagingItemReader<ClientDetails> ClientDetailsDatabaseReader(EntityManagerFactory entityManagerFactory) {
        return createJpaPagingItemReader(entityManagerFactory, "SELECT s FROM ClientDetails s", "ClientDetailsDatabaseReader");
    }

    @Bean
    public FlatFileItemWriter<ClientDetails> ClientDetailsCsvWriter() {
        return createFlatFileItemWriter("src/main/resources/clientsDetails.csv",
                new String[]{"id", "firstname", "lastname", "age"},
                new String[]{"id", "firstname", "lastname", "age"});

    }

    @Bean
    public Step exportClientDetailssStep(EntityManagerFactory entityManagerFactory) {
        return new StepBuilder("exportClientDetailssStep", jobRepository)
                .<ClientDetails, ClientDetails>chunk(10, platformTransactionManager)
                .reader(ClientDetailsDatabaseReader(entityManagerFactory))
                .writer(ClientDetailsCsvWriter())
                .build();
    }

    @Bean(name = "exportClientDetailssJob")
    public Job exportClientDetailssJob(EntityManagerFactory entityManagerFactory) {
        return new JobBuilder("exportClientDetailssJob", jobRepository)
                .start(exportClientDetailssStep(entityManagerFactory))
                .build();
    }
}
