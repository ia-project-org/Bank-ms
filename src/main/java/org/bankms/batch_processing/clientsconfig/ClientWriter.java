package org.bankms.batch_processing.clientsconfig;

import org.bankms.clientsms.model.Client1;
import org.bankms.clientsms.model.ClientDetails;
import org.bankms.clientsms.repository.ClientDetailsRepository;
import org.bankms.clientsms.repository.ClientRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

public class ClientWriter implements ItemWriter<Client1> {
    private final ClientRepository clientRepository;
    private final ClientDetailsRepository clientDetailsRepository;

    public ClientWriter(ClientRepository clientRepository,
                        ClientDetailsRepository clientDetailsRepository) {
        this.clientRepository = clientRepository;
        this.clientDetailsRepository = clientDetailsRepository;
    }


    @Override
    public void write(Chunk<? extends Client1> clients) throws Exception {
        // Extracting the ClientDetails from the Client1 entities
        List<ClientDetails> clientDetailsList = new ArrayList<>();
        List<Client1> cliensList = new ArrayList<>();

        for (Client1 client : clients) {
            ClientDetails clientDetails = client.getDetails();
            if(!clientRepository.existsByCin(client.getCin())
                    && !clientRepository.existsByEmail(client.getEmail())
                    && countSelectedOccupations(clientDetails)==1){
                clientDetailsList.add(clientDetails);
                cliensList.add(client);
                System.out.println(clientDetails);
            }
        }
        // Save ClientDetails first
        clientDetailsRepository.saveAll(clientDetailsList);
        // Save the Client1 entities
        clientRepository.saveAll(cliensList);
    }

    private int countSelectedOccupations(ClientDetails clientDetails) {
        return clientDetails.getOccupationAccountant() +
                clientDetails.getOccupationArchitect() +
                clientDetails.getOccupationDeveloper() +
                clientDetails.getOccupationDoctor() +
                clientDetails.getOccupationEngineer() +
                clientDetails.getOccupationEntrepreneur() +
                clientDetails.getOccupationJournalist() +
                clientDetails.getOccupationLawyer() +
                clientDetails.getOccupationManager() +
                clientDetails.getOccupationMechanic() +
                clientDetails.getOccupationMediaManager() +
                clientDetails.getOccupationMusician() +
                clientDetails.getOccupationScientist() +
                clientDetails.getOccupationTeacher() +
                clientDetails.getOccupationWriter();
    }

}