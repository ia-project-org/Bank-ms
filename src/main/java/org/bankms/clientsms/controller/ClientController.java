package org.bankms.clientsms.controller;

import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.config.ApplicationPropertiesConfiguration;
import org.bankms.clientsms.dto.ClientDto;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.model.ClientDetails;
import org.bankms.clientsms.service.ClientService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import static org.bankms.batch_processing.clientsconfig.ClientsImportBatchConfig.file_path;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final JobLauncher jobLauncher;

    private final Job importClientJob;

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<Client>> getPaginatedClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Page<Client> clientPage = clientService.getClients(page, size);
        return ResponseEntity.ok(clientPage);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importClients() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(importClientJob,new JobParametersBuilder()
                .addString("timestamp", String.valueOf(System.currentTimeMillis())) // Unique parameter
                .toJobParameters());
        return ResponseEntity.ok().body("imported successfully");
    }

    @PostMapping("/import-csv")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file) throws Exception {
        file.transferTo(new File(file_path));
        JobExecution jobExecution = jobLauncher.run(importClientJob, new JobParametersBuilder()
                        .addString("fileName", file.getOriginalFilename())
                        .addString("timsmap", String.valueOf(System.currentTimeMillis()))
                        .toJobParameters());
        String msg = jobExecution.getStatus().isUnsuccessful()?"Job echoue !!!":"CSV imported successfully.";
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/number")
    public ResponseEntity<Long> getClientsNumber(){
        long numberOfClients = clientService.getNumberOfClients();
        return ResponseEntity.ok(numberOfClients);
    }

//    @GetMapping("/growth-percentage")
//    public ResponseEntity<Double> getClientsGrowthPercentage(){
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        LocalDateTime lastDayOfPreviousMonth = currentDateTime.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
//
//        long numberOfClientsThisMonth = clientService.getNumberOfClients();
//        System.out.println(numberOfClientsThisMonth);
//        long numberOfClientsLastMonth = clientService.getNumberOfClients(lastDayOfPreviousMonth);
//        System.out.println(numberOfClientsLastMonth);
//
//        double growthPercentage = ((double) (numberOfClientsThisMonth - numberOfClientsLastMonth) / numberOfClientsLastMonth) * 100;
//        return ResponseEntity.ok(growthPercentage);
//    }


    @GetMapping("/number-previous-month")
    public ResponseEntity<Long> getClientsNumberPreviousMonth(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime lastDayOfPreviousMonth = currentDateTime.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        return ResponseEntity.ok(clientService.getNumberOfClients(lastDayOfPreviousMonth));
    }
}
