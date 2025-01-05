package org.bankms.batch_processing.clientsconfig;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ClientImportJobListener implements JobExecutionListener {

    @Value("${app.file.input-path}")
    public String file_path;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Le job commence...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus().isUnsuccessful()) {
            System.out.println("Le job a échoué !");
        } else {
            System.out.println("Le job s'est terminé avec succès !");
            executePostAction();
        }
    }

    private void executePostAction() {
        File file = new File(file_path);
        if (file.exists() && file.delete()) {
            System.out.println("Fichier supprimé");
        }
    }
}
